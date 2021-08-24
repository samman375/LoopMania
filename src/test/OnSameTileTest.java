package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import unsw.loopmania.model.AlliedSoldier;
import unsw.loopmania.model.Buildings.CampfireBuilding;
import unsw.loopmania.model.Buildings.VampireCastleBuilding;
import unsw.loopmania.model.Cards.ZombiePitCard;
import unsw.loopmania.model.Character;
import unsw.loopmania.model.Enemies.Slug;
import unsw.loopmania.model.Enemies.Zombie;
import unsw.loopmania.model.Items.BasicItems.Armour;
import unsw.loopmania.model.Items.BasicItems.Gold;
import unsw.loopmania.model.Items.RareItems.TheOneRing;
import unsw.loopmania.model.LoopManiaWorld;
import unsw.loopmania.model.PathPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OnSameTileTest {

    List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer, Integer>>();

    LoopManiaWorld world = new LoopManiaWorld(10, 10, orderedPath, new ArrayList<>(), new Random(1));

    /**
     * Test to make sure character and enemy are on the same location
     */
    @Test
    public void CharacterEnemyTest() {
        Pair<Integer, Integer> pathTile = new Pair<Integer, Integer>(1, 1);
        orderedPath.add(pathTile);
        
        PathPosition position = new PathPosition(orderedPath.indexOf(pathTile), orderedPath);
        Character character = new Character(position);
        Slug slug = new Slug(position);

        assert(world.isOnSameTile(character, slug));
    }

    /**
     * Test to make sure character and allied soldier are on the same location
     */
    @Test
    public void CharacterAlliedSoldierTest() {
        Pair<Integer, Integer> pathTile = new Pair<Integer, Integer>(1, 1);
        orderedPath.add(pathTile);

        PathPosition position = new PathPosition(orderedPath.indexOf(pathTile), orderedPath);
        Character character = new Character(position);
        AlliedSoldier alliedSoldier = new AlliedSoldier(position);

        assert(world.isOnSameTile(character, alliedSoldier));
    }

    /**
     * Test to make sure character and building are on the same location
     */
    @Test
    public void CharacterBuildingTest() {
        Pair<Integer, Integer> pathTile = new Pair<Integer, Integer>(1, 1);
        orderedPath.add(pathTile);

        PathPosition position = new PathPosition(orderedPath.indexOf(pathTile), orderedPath);
        Character character = new Character(position);
        CampfireBuilding campfireBuilding = new CampfireBuilding(new SimpleIntegerProperty(1),
                                                                 new SimpleIntegerProperty(1));

        assert(world.isOnSameTile(character, campfireBuilding));
    }

    /**
     * Test to make sure character and basic item are on the same location
     */
    @Test
    public void CharacterBasicItem() {
        Pair<Integer, Integer> pathTile = new Pair<Integer, Integer>(1, 1);
        orderedPath.add(pathTile);

        PathPosition position = new PathPosition(orderedPath.indexOf(pathTile), orderedPath);
        Character character = new Character(position);
        Armour armour = new Armour(new SimpleIntegerProperty(1),
                                   new SimpleIntegerProperty(1));

        assert(world.isOnSameTile(character, armour));
    }

    /**
     * Test to make sure character and rare item are on the same location
     */
    @Test
    public void CharacterRareItem() {
        Pair<Integer, Integer> pathTile = new Pair<Integer, Integer>(1, 1);
        orderedPath.add(pathTile);

        PathPosition position = new PathPosition(orderedPath.indexOf(pathTile), orderedPath);
        Character character = new Character(position);
        TheOneRing theOneRing = new TheOneRing(new SimpleIntegerProperty(1),
                                       new SimpleIntegerProperty(1));

        assert(world.isOnSameTile(character, theOneRing));
    }

    /**
     * Test to make sure character and card are on the same location
     */
    @Test
    public void CharacterCardTest() {
        Pair<Integer, Integer> pathTile = new Pair<Integer, Integer>(1, 1);
        orderedPath.add(pathTile);

        PathPosition position = new PathPosition(orderedPath.indexOf(pathTile), orderedPath);
        Character character = new Character(position);
        ZombiePitCard zombiePitCard = new ZombiePitCard(new SimpleIntegerProperty(1),
                                                        new SimpleIntegerProperty(1));

        assert(world.isOnSameTile(character, zombiePitCard));
    }

    /**
     * Test to make sure building and item are on the same location
     */
    @Test
    public void BuildingItem() {

        VampireCastleBuilding vampireCastleBuilding = new VampireCastleBuilding(new SimpleIntegerProperty(1),
                                                                                new SimpleIntegerProperty(1));
        Gold gold = new Gold(new SimpleIntegerProperty(1),
                             new SimpleIntegerProperty(1));

        assert(world.isOnSameTile(vampireCastleBuilding, gold));
    }

    /**
     * Test to make sure building and enemy are on the same location
     */
    @Test
    public void BuildingEnemy() {

        Pair<Integer, Integer> pathTile = new Pair<Integer, Integer>(1, 1);
        orderedPath.add(pathTile);

        PathPosition position = new PathPosition(orderedPath.indexOf(pathTile), orderedPath);
        Zombie zombie = new Zombie(position);

        CampfireBuilding campfireBuilding = new CampfireBuilding(new SimpleIntegerProperty(1),
                                                                 new SimpleIntegerProperty(1));

        assert(world.isOnSameTile(zombie, campfireBuilding));
    }

}
