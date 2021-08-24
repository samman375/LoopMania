package test;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import unsw.loopmania.model.*;
import unsw.loopmania.model.Character;
import unsw.loopmania.model.Buildings.*;
import unsw.loopmania.model.Enemies.*;

public class BuildingsTests {

    /**
     * Testing spawnAlliedSoldier method for BarracksBuilding
     */
    @Test
    void BarracksTest() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        orderedPath.add(new Pair<>(1,2));
        orderedPath.add(new Pair<>(1,3));
        PathPosition position = new PathPosition(1, orderedPath);
        
        BarracksBuilding barracksBuilding1 = new BarracksBuilding(position);
        assertNotNull(barracksBuilding1.spawnAlliedSoldier(new PathPosition(1, orderedPath)));

        // Testing super class instantiation
        BarracksBuilding barracksBuilding2 = new BarracksBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        barracksBuilding2.setPathPosition(position);
        assertEquals(barracksBuilding2.getPathPosition(), position);
        assertNotNull(barracksBuilding2.spawnAlliedSoldier(new PathPosition(1, orderedPath)));

    }

    /**
     * Test damageEnemy method for TrapBuilding
     */
    @Test
    void TrapTest() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        orderedPath.add(new Pair<>(1,2));
        orderedPath.add(new Pair<>(1,3));
        PathPosition position = new PathPosition(1, orderedPath);
        
        // Test killing enemy with trap
        TrapBuilding trapBuilding1 = new TrapBuilding(position);
        Enemy slug = new Slug(position);
        assertEquals(slug.getHealth(), 10);
        assertEquals(trapBuilding1.damageEnemy(slug), 0);
        assert(slug.isDead());
        
        // Test damaging enemy with trap and super class instantiation
        TrapBuilding trapBuilding2 = new TrapBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        trapBuilding2.setPathPosition(position);
        assertEquals(trapBuilding2.getPathPosition(), position);
        Enemy vampire = new Vampire(position);
        assertEquals(vampire.getHealth(), 50);
        assertEquals(trapBuilding2.damageEnemy(vampire), 40);
        assert(!vampire.isDead());

    }

    /**
     * Testing gainHealth method for VillageBuilding
     */
    @Test
    void VillageTest() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        orderedPath.add(new Pair<>(1,2));
        orderedPath.add(new Pair<>(1,3));
        PathPosition position = new PathPosition(1, orderedPath);

        Character character = new Character(position);
        character.reduceHealth(50);
        assertEquals(character.getHealth(), 50);

        VillageBuilding villageBuilding1 = new VillageBuilding(position);
        villageBuilding1.gainHealth(character);
        assertEquals(character.getHealth(), 100);

        // Testing super class instantiation
        VillageBuilding villageBuilding2 = new VillageBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        villageBuilding2.setPathPosition(position);
        assertEquals(villageBuilding2.getPathPosition(), position);
        villageBuilding2.gainHealth(character);
        assertEquals(character.getHealth(), 100);
    }

    /**
     * Testing spawnVampire method for VampireCastleBuilding
     */
    @Test
    void VampireCastleTest() {

        VampireCastleBuilding vampireCastleBuilding = new VampireCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        orderedPath.add(new Pair<>(1,2));
        orderedPath.add(new Pair<>(1,3));

        PathPosition position = new PathPosition(0, orderedPath);

        // Testing failed spawn on 1st cycle
        boolean completedACycle = true;
        int cycles = 1;
        assertNull(vampireCastleBuilding.spawnVampire(completedACycle, cycles, position));

        // // Testing failed spawn without complete cycle
        completedACycle = false;
        cycles = 5;
        assertNull(vampireCastleBuilding.spawnVampire(completedACycle, cycles, position));

        // Testing successful spawn on 5th cycle
        completedACycle = true;
        cycles = 5;
        Vampire vampire = vampireCastleBuilding.spawnVampire(completedACycle, cycles, position);
        assertNotNull(vampire);
        assertEquals(vampire.getX(), 1);
        assertEquals(vampire.getY(), 1);
    }

/**
     * Testing spawnZombie method for ZombiePitBuilding
     */
    @Test
    void ZombiePitTest() {

        ZombiePitBuilding zombiePitBuilding = new ZombiePitBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        orderedPath.add(new Pair<>(1,2));
        orderedPath.add(new Pair<>(1,3));
        PathPosition position = new PathPosition(0, orderedPath);

        // Testing failed spawn without complete cycle
        boolean completedACycle = false;
        int cycles = 1;
        assertNull(zombiePitBuilding.spawnZombie(completedACycle, cycles, position));

        // Testing failed spawn on 0th cycle
        completedACycle = true;
        cycles = 0;
        assertNull(zombiePitBuilding.spawnZombie(completedACycle, cycles, position));

        // Testing successful spawn on 1st cycle
        completedACycle = true;
        cycles = 1;
        Zombie zombie = zombiePitBuilding.spawnZombie(completedACycle, cycles, position);
        assertNotNull(zombie);
        assertEquals(zombie.getX(), 1);
        assertEquals(zombie.getY(), 1);

    }

}
