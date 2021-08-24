package test.AttackStrategyTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.AttackStrategy.AttackStrategy;
import unsw.loopmania.model.AttackStrategy.ZombieAttack;
import unsw.loopmania.model.Enemies.Zombie;
import unsw.loopmania.model.AlliedSoldier;
import unsw.loopmania.model.AttackEffects;
import unsw.loopmania.model.Character;


public class ZombieAttackTest {
    /**
     * Test ZombieAttack strategy damage for an attack on a character
     */
    @Test
    public void attackCharacterExecuteTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        Character character = new Character(dummyPosition);
        Zombie attacker = new Zombie(dummyPosition);
        AttackStrategy zombieAttack = new ZombieAttack();
        // Test attack with no defences
        zombieAttack.execute(attacker, character, 0, 0, false, 0);
        assertEquals(character.getHealth(), 86);
        // Check campfire does not affect damage
        character.setHealth(100);
        zombieAttack.execute(attacker, character, 0, 0, true, 0);
        assertEquals(character.getHealth(), 86);
        // Test attack with fixedDefences
        character.setHealth(100);
        zombieAttack.execute(attacker, character, 0, 5, false, 0);
        assertEquals(character.getHealth(), 91);
        // Test attack with scalarDefences
        character.setHealth(100);
        zombieAttack.execute(attacker, character, 30, 0, false, 0);
        assertEquals(character.getHealth(), 90);
        // Test attack with both fixed and scalarDefences
        character.setHealth(100);
        zombieAttack.execute(attacker, character, 50, 2, false, 0);
        assertEquals(character.getHealth(), 95);
    }

    /**
     * Test ZombieAttack strategy for an attack on an alliedSoldier
     */
    @Test
    public void attackAllyExecuteTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        AlliedSoldier ally = new AlliedSoldier(dummyPosition);
        Zombie attacker = new Zombie(dummyPosition);
        AttackStrategy zombieAttack = new ZombieAttack();
        // Test attack with no defences
        zombieAttack.execute(attacker, ally, 0, 0, false, 0);
        assertEquals(ally.getHealth(), 36);
        // Check campfire does not affect damage
        ally.setHealth(50);
        zombieAttack.execute(attacker, ally, 0, 0, true, 0);
        assertEquals(ally.getHealth(), 36);
        // Test attack with defences does not affect damage
        ally.setHealth(50);
        zombieAttack.execute(attacker, ally, 20, 2, false, 0);
        assertEquals(ally.getHealth(), 36);
    }

    /**
     * Test randomness of infect chance 10 times with random seed of 4
     */
    @Test
    public void infectChanceExecuteTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        AlliedSoldier ally = new AlliedSoldier(dummyPosition);
        Zombie attacker = new Zombie(dummyPosition);
        AttackStrategy zombieAttack = new ZombieAttack();
        Random random = new Random(4);
        Boolean expectedInfect = random.nextInt(99) < 15;
        Enum<AttackEffects> infect = zombieAttack.execute(attacker, ally, 0, 0, false, 0);
        assertEquals(infect, expectedInfect);
        expectedInfect = random.nextInt(99) < 15;
        infect = zombieAttack.execute(attacker, ally, 0, 0, false, 0);
        assertEquals(infect, expectedInfect);
        expectedInfect = random.nextInt(99) < 15;
        infect = zombieAttack.execute(attacker, ally, 0, 0, false, 0);
        assertEquals(infect, expectedInfect);
        expectedInfect = random.nextInt(99) < 15;
        infect = zombieAttack.execute(attacker, ally, 0, 0, false, 0);
        assertEquals(infect, expectedInfect);
        expectedInfect = random.nextInt(99) < 15;
        infect = zombieAttack.execute(attacker, ally, 0, 0, false, 0);
        assertEquals(infect, expectedInfect);
        expectedInfect = random.nextInt(99) < 15;
        infect = zombieAttack.execute(attacker, ally, 0, 0, false, 0);
        assertEquals(infect, expectedInfect);
        expectedInfect = random.nextInt(99) < 15;
        infect = zombieAttack.execute(attacker, ally, 0, 0, false, 0);
        assertEquals(infect, expectedInfect);
        expectedInfect = random.nextInt(99) < 15;
        infect = zombieAttack.execute(attacker, ally, 0, 0, false, 0);
        assertEquals(infect, expectedInfect);
        expectedInfect = random.nextInt(99) < 15;
        infect = zombieAttack.execute(attacker, ally, 0, 0, false, 0);
        assertEquals(infect, expectedInfect);
        expectedInfect = random.nextInt(99) < 15;
        infect = zombieAttack.execute(attacker, ally, 0, 0, false, 0);
    }
}

