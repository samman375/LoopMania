package unsw.loopmania.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.Buildings.HerosCastleBuilding;
import unsw.loopmania.model.Goal.*;
import unsw.loopmania.model.Character;
import unsw.loopmania.model.Entity;
import unsw.loopmania.model.LoopManiaWorld;
import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.PathTile;

import java.util.List;
import java.util.Random;

/**
 * Loads a world from a .json file.
 * 
 * By extending this class, a subclass can hook into entity creation.
 * This is useful for creating UI elements with corresponding entities.
 * 
 * this class is used to load the world.
 * it loads non-spawning entities from the configuration files.
 * spawning of enemies/cards must be handled by the controller.
 */
public abstract class LoopManiaWorldLoader {
    private JSONObject json;

    public LoopManiaWorldLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("worlds/" + filename)));
    }

    /**
     * Parses the JSON to create a world.
     */
    public LoopManiaWorld load() {
        int width = json.getInt("width");
        int height = json.getInt("height");
        JSONArray jsonRareItems = json.getJSONArray("rare_items");
        List<String> rareItems = new ArrayList<>();
        if (jsonRareItems != null) {
            for (Object item : jsonRareItems) {
                rareItems.add(item.toString());
            }
        }

        
        // path variable is collection of coordinates with directions of path taken...
        List<Pair<Integer, Integer>> orderedPath = loadPathTiles(json.getJSONObject("path"), width, height);

        Random random = new Random(1);
        LoopManiaWorld world = new LoopManiaWorld(width, height, orderedPath, rareItems, random);

        JSONArray jsonEntities = json.getJSONArray("entities");

        // load non-path entities later so that they're shown on-top
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(world, jsonEntities.getJSONObject(i), orderedPath);
        }

        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        Goal goals = loadGoals(jsonGoals);
        world.setGoals(goals);

        return world;
    }

    /**
     * load an entity into the world
     * @param world backend world object
     * @param json a JSON object to parse (different from the )
     * @param orderedPath list of pairs of x, y cell coordinates representing game path
     */
    private void loadEntity(LoopManiaWorld world, JSONObject currentJson, List<Pair<Integer, Integer>> orderedPath) {
        String type = currentJson.getString("type");
        int x = currentJson.getInt("x");
        int y = currentJson.getInt("y");
        int indexInPath = orderedPath.indexOf(new Pair<Integer, Integer>(x, y));
        assert indexInPath != -1;

        Entity entity = null;
        switch (type) {
        case "hero_castle":
            // Add hero castle to the world
            HerosCastleBuilding herosCastleBuilding = new HerosCastleBuilding(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            world.setHerosCastleBuilding(herosCastleBuilding);
            onLoad(herosCastleBuilding);

            Character character = new Character(new PathPosition(indexInPath, orderedPath));
            world.setCharacter(character);
            onLoad(character);
            entity = character;
            break;
        case "path_tile":
            throw new RuntimeException("path_tile's aren't valid entities, define the path externally.");
        }
        world.addEntity(entity);
    }

    /**
     * load path tiles
     * @param path json data loaded from file containing path information
     * @param width width in number of cells
     * @param height height in number of cells
     * @return list of x, y cell coordinate pairs representing game path
     */
    private List<Pair<Integer, Integer>> loadPathTiles(JSONObject path, int width, int height) {
        if (!path.getString("type").equals("path_tile")) {
            // ... possible extension
            throw new RuntimeException(
                    "Path object requires path_tile type.  No other path types supported at this moment.");
        }
        PathTile starting = new PathTile(new SimpleIntegerProperty(path.getInt("x")), new SimpleIntegerProperty(path.getInt("y")));
        if (starting.getY() >= height || starting.getY() < 0 || starting.getX() >= width || starting.getX() < 0) {
            throw new IllegalArgumentException("Starting point of path is out of bounds");
        }
        // load connected path tiles
        List<PathTile.Direction> connections = new ArrayList<>();
        for (Object dir: path.getJSONArray("path").toList()){
            connections.add(Enum.valueOf(PathTile.Direction.class, dir.toString()));
        }

        if (connections.size() == 0) {
            throw new IllegalArgumentException(
                "This path just consists of a single tile, it needs to consist of multiple to form a loop.");
        }

        // load the first position into the orderedPath
        PathTile.Direction first = connections.get(0);
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(starting.getX(), starting.getY()));

        int x = starting.getX() + first.getXOffset();
        int y = starting.getY() + first.getYOffset();

        // add all coordinates of the path into the orderedPath
        for (int i = 1; i < connections.size(); i++) {
            orderedPath.add(Pair.with(x, y));
            
            if (y >= height || y < 0 || x >= width || x < 0) {
                throw new IllegalArgumentException("Path goes out of bounds at direction index " + (i - 1) + " (" + connections.get(i - 1) + ")");
            }
            
            PathTile.Direction dir = connections.get(i);
            PathTile tile = new PathTile(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            x += dir.getXOffset();
            y += dir.getYOffset();
            if (orderedPath.contains(Pair.with(x, y)) && !(x == starting.getX() && y == starting.getY())) {
                throw new IllegalArgumentException("Path crosses itself at direction index " + i + " (" + dir + ")");
            }
            onLoad(tile, connections.get(i - 1), dir);
        }
        // we should connect back to the starting point
        if (x != starting.getX() || y != starting.getY()) {
            throw new IllegalArgumentException(String.format(
                    "Path must loop back around on itself, this path doesn't finish where it began, it finishes at %d, %d.",
                    x, y));
        }
        onLoad(starting, connections.get(connections.size() - 1), connections.get(0));
        return orderedPath;
    }

    private Goal loadGoals(JSONObject goal) {
        String goalType = goal.getString("goal");

        switch (goalType) {
            case "AND":
                JSONArray andGoals = goal.getJSONArray("subgoals");
                Goal firstAndGoal = loadGoals(andGoals.getJSONObject(0));
                Goal secondAndGoal = loadGoals(andGoals.getJSONObject(1));
                AndGoal andGoal = new AndGoal(firstAndGoal, secondAndGoal);
                return andGoal;
            case "OR":
                JSONArray orGoals = goal.getJSONArray("subgoals");
                Goal firstOrGoal = loadGoals(orGoals.getJSONObject(0));
                Goal secondOrGoal = loadGoals(orGoals.getJSONObject(1));
                OrGoal orGoal = new OrGoal(firstOrGoal, secondOrGoal);
                return orGoal;
            case "experience":
                int expQuantity = goal.getInt("quantity");
                ExperienceGoal experienceGoal = new ExperienceGoal(expQuantity);
                return experienceGoal;
            case "gold":
                int goldQuantity = goal.getInt("quantity");
                GoldGoal goldGoal = new GoldGoal(goldQuantity);
                return goldGoal;
            case "cycles":
                int cyclesQuantity = goal.getInt("quantity");
                CycleGoal cycleGoal = new CycleGoal(cyclesQuantity);
                return cycleGoal;
            case "bosses":
                BossGoal bossGoal = new BossGoal();
                return bossGoal;
        }

        return null;
    }

    public abstract void onLoad(Character character);
    public abstract void onLoad(HerosCastleBuilding herosCastleBuidling);
    public abstract void onLoad(PathTile pathTile, PathTile.Direction into, PathTile.Direction out);

}