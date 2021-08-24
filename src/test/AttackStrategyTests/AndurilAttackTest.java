package test.AttackStrategyTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.AttackStrategy.AttackStrategy;
import unsw.loopmania.model.AttackStrategy.AndurilAttack;
import unsw.loopmania.model.Enemies.*;
import unsw.loopmania.model.Character;


public class AndurilAttackTest {
    /**
     * Test AndurilAttack strategy damage on basic enemies
     */
    @Test
    public void andurilExecuteTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        Character attacker = new Character(dummyPosition);
        Slug slug = new Slug(dummyPosition);
        Zombie zombie = new Zombie(dummyPosition);
        AttackStrategy andurilAttack = new AndurilAttack();
        // Test simple anduril attack
        andurilAttack.execute(attacker, slug, 0, 0, false, 0);
        andurilAttack.execute(attacker, zombie, 0, 0, false, 0);
        assertEquals(slug.getHealth(), -6);
        assertEquals(zombie.getHealth(), 4);
        // Check campfire doubles character damage
        slug.setHealth(10);
        zombie.setHealth(20);
        andurilAttack.execute(attacker, slug, 0, 0, true, 0);
        andurilAttack.execute(attacker, zombie, 0, 0, true, 0);
        assertEquals(slug.getHealth(), -22);
        assertEquals(zombie.getHealth(), -12);
    }

    /**
     * Test AndurilAttack strategy bonus damage on bosses
     */
    @Test
    public void andurilCritExecuteTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        Character attacker = new Character(dummyPosition);
        Doggie doggie = new Doggie(dummyPosition);
        Elan elon = new Elan(dummyPosition);
        doggie.setHealth(100);
        elon.setHealth(100);
        AttackStrategy andurilAttack = new AndurilAttack();
        // Test simple anduril attack
        andurilAttack.execute(attacker, doggie, 0, 0, false, 0);
        assertEquals(doggie.getHealth(), 64);
        andurilAttack.execute(attacker, elon, 0, 0, false, 0);
        assertEquals(elon.getHealth(), 64);
        // Check campfire doubles character damage
        doggie.setHealth(100);
        elon.setHealth(100);
        andurilAttack.execute(attacker, doggie, 0, 0, true, 0);
        assertEquals(doggie.getHealth(), 28);
        andurilAttack.execute(attacker, elon, 0, 0, true, 0);
        assertEquals(elon.getHealth(), 28);
    }
}
