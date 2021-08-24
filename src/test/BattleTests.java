package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.Buildings.Building;
import unsw.loopmania.model.Buildings.CampfireBuilding;
import unsw.loopmania.model.Buildings.TowerBuilding;
import unsw.loopmania.model.Enemies.*;
import unsw.loopmania.model.AlliedSoldier;
import unsw.loopmania.model.Battle;
import unsw.loopmania.model.Character;

/**
 * Tests for battles in backend
 */
public class BattleTests {
    /**
     * Test isLost()
     */
    @Test
    public void isLostTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        Character character = new Character(dummyPosition);
        List<Building> towers = new ArrayList<>();
        List<AlliedSoldier> allies = new ArrayList<>();
        List<Enemy> enemies = new ArrayList<>();
        List<Building> campfires = new ArrayList<>();
        String gameMode = "Standard";
        Battle battle = new Battle(character, towers, allies, enemies, campfires, gameMode);
        assert(!battle.isLost());
        character.setHealth(0);
        assert(battle.isLost());
        character.setHealth(-12);
        assert(battle.isLost());
    }

    @Test
    public void BattleIntegrationTest() {
        String gameMode = "Standard";
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        // Create a square loop
        orderedPath.add(new Pair<>(0,0));
        orderedPath.add(new Pair<>(1,0));
        orderedPath.add(new Pair<>(2,0));
        orderedPath.add(new Pair<>(3,0));
        orderedPath.add(new Pair<>(4,0));
        orderedPath.add(new Pair<>(5,0));
        orderedPath.add(new Pair<>(6,0));
        orderedPath.add(new Pair<>(7,0));
        orderedPath.add(new Pair<>(8,0));
        orderedPath.add(new Pair<>(8,1));
        orderedPath.add(new Pair<>(8,2));
        orderedPath.add(new Pair<>(8,3));
        orderedPath.add(new Pair<>(8,4));
        orderedPath.add(new Pair<>(8,5));
        orderedPath.add(new Pair<>(7,5));
        orderedPath.add(new Pair<>(6,5));
        orderedPath.add(new Pair<>(5,5));
        orderedPath.add(new Pair<>(4,5));
        orderedPath.add(new Pair<>(3,5));
        orderedPath.add(new Pair<>(2,5));
        orderedPath.add(new Pair<>(1,5));
        orderedPath.add(new Pair<>(0,5));
        orderedPath.add(new Pair<>(0,4));
        orderedPath.add(new Pair<>(0,3));
        orderedPath.add(new Pair<>(0,2));
        orderedPath.add(new Pair<>(0,1));
        
        // Initialise character on 5,0
        PathPosition characterPosition = new PathPosition(5, orderedPath);
        Character character = new Character(characterPosition);
        
        // Initialise 2 towers
        List<Building> towers = new ArrayList<>();
        TowerBuilding tower1 = new TowerBuilding(new SimpleIntegerProperty(4), new SimpleIntegerProperty(1));
        TowerBuilding tower2 = new TowerBuilding(new SimpleIntegerProperty(7), new SimpleIntegerProperty(1));
        towers.add(tower1);
        towers.add(tower2);

        // Initialise 2 allies
        List<AlliedSoldier> allies = new ArrayList<>();
        PathPosition ally1Postion = new PathPosition(5, orderedPath);
        AlliedSoldier ally1 = new AlliedSoldier(ally1Postion);
        PathPosition ally2Postion = new PathPosition(5, orderedPath);
        AlliedSoldier ally2 = new AlliedSoldier(ally2Postion);
        allies.add(ally1);
        allies.add(ally2);

        // Initalise 2 slugs, 1 zombie, 1 vampire
        List<Enemy> enemies = new ArrayList<>();
        PathPosition slug1Position = new PathPosition(4, orderedPath);
        Slug slug1 = new Slug(slug1Position);
        PathPosition slug2Position = new PathPosition(6, orderedPath);
        Slug slug2 = new Slug(slug2Position);
        PathPosition zombiePosition = new PathPosition(7, orderedPath);
        Zombie zombie = new Zombie(zombiePosition);
        PathPosition vampirePostion = new PathPosition(3, orderedPath);
        Vampire vampire = new Vampire(vampirePostion);
        //Intialise bosses
        PathPosition elanPosition = new PathPosition(4, orderedPath);
        Elan elan = new Elan(elanPosition);
        PathPosition doggiePosition = new PathPosition(4, orderedPath);
        Doggie doggie = new Doggie(doggiePosition);

        enemies.add(slug1);
        enemies.add(slug2);
        enemies.add(zombie);
        enemies.add(vampire);
        enemies.add(elan);
        enemies.add(doggie);
        

        // Initalise campfire
        List<Building> campfires = new ArrayList<>();
        CampfireBuilding campfire = new CampfireBuilding(new SimpleIntegerProperty(6), new SimpleIntegerProperty(1));
        campfires.add(campfire);

        // Initalise battle
        Battle battle = new Battle(character, towers, allies, enemies, campfires, gameMode);

        battle.fight();

        if (!battle.isLost()) {
            // Check all enemies dead
            assert(battle.getKilledEnemies().size() >= enemies.size());
            // Check item rewards (account for possible TheOneRing)
            assert(battle.getBattleItems().size() >= enemies.size());
            Random random = new Random(12);
            if (random.nextInt(99) < 5) {
                assert(battle.getBattleItems().contains("TheOneRing"));
            }

            // Check card rewards
            assertEquals(battle.getBattleCards().size(), enemies.size());
        } else {
            assert(battle.getKilledEnemies().size() < enemies.size());
        }
        // Check ally took damage
        assertNotEquals(ally1.getHealth(), 50);

        
    }
}
