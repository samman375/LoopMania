package test.AttackStrategyTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.AttackStrategy.AttackStrategy;
import unsw.loopmania.model.AttackStrategy.StaffAttack;
import unsw.loopmania.model.Enemies.*;
import unsw.loopmania.model.AttackEffects;
import unsw.loopmania.model.Character;


public class StaffAttackTest {
    /**
     * Test StaffAttack strategy damage on enemies
     */
    @Test
    public void staffExecuteTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        Character attacker = new Character(dummyPosition);
        Slug slug = new Slug(dummyPosition);
        Vampire vampire = new Vampire(dummyPosition);
        Zombie zombie = new Zombie(dummyPosition);
        AttackStrategy staffAttack = new StaffAttack();
        // Test simple staff attack
        staffAttack.execute(attacker, slug, 0, 0, false, 0);
        staffAttack.execute(attacker, vampire, 0, 0, false, 0);
        staffAttack.execute(attacker, zombie, 0, 0, false, 0);
        assertEquals(slug.getHealth(), 1);
        assertEquals(vampire.getHealth(), 41);
        assertEquals(zombie.getHealth(), 11);
        // Check campfire doubles character damage
        slug.setHealth(10);
        vampire.setHealth(50);
        zombie.setHealth(20);
        staffAttack.execute(attacker, slug, 0, 0, true, 0);
        staffAttack.execute(attacker, vampire, 0, 0, true, 0);
        staffAttack.execute(attacker, zombie, 0, 0, true, 0);
        assertEquals(slug.getHealth(), -8);
        assertEquals(vampire.getHealth(), 32);
        assertEquals(zombie.getHealth(), 2);
    }

    /**
     * Test randomness of trance chance 10 times with random seed of 7
     */
    @Test
    public void tranceChanceExecuteTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        Character attacker = new Character(dummyPosition);
        Slug slug = new Slug(dummyPosition);
        AttackStrategy staffAttack = new StaffAttack();
        Random random = new Random(7);
        Boolean expectedTrance = random.nextInt(99) < 40;
        Enum<AttackEffects> trance = staffAttack.execute(attacker, slug, 0, 0, false, 0);
        assertEquals(trance, expectedTrance);
        expectedTrance = random.nextInt(99) < 40;
        trance = staffAttack.execute(attacker, slug, 0, 0, false, 0);
        assertEquals(trance, expectedTrance);
        expectedTrance = random.nextInt(99) < 40;
        trance = staffAttack.execute(attacker, slug, 0, 0, false, 0);
        assertEquals(trance, expectedTrance);
        expectedTrance = random.nextInt(99) < 40;
        trance = staffAttack.execute(attacker, slug, 0, 0, false, 0);
        assertEquals(trance, expectedTrance);
        expectedTrance = random.nextInt(99) < 40;
        trance = staffAttack.execute(attacker, slug, 0, 0, false, 0);
        assertEquals(trance, expectedTrance);
        expectedTrance = random.nextInt(99) < 40;
        trance = staffAttack.execute(attacker, slug, 0, 0, false, 0);
        assertEquals(trance, expectedTrance);
        expectedTrance = random.nextInt(99) < 40;
        trance = staffAttack.execute(attacker, slug, 0, 0, false, 0);
        assertEquals(trance, expectedTrance);
        expectedTrance = random.nextInt(99) < 40;
        trance = staffAttack.execute(attacker, slug, 0, 0, false, 0);
        assertEquals(trance, expectedTrance);
        expectedTrance = random.nextInt(99) < 40;
        trance = staffAttack.execute(attacker, slug, 0, 0, false, 0);
        assertEquals(trance, expectedTrance);
        expectedTrance = random.nextInt(99) < 40;
        trance = staffAttack.execute(attacker, slug, 0, 0, false, 0);
        assertEquals(trance, expectedTrance);
    }
}