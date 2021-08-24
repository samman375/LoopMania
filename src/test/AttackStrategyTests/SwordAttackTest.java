package test.AttackStrategyTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.AttackStrategy.AttackStrategy;
import unsw.loopmania.model.AttackStrategy.SwordAttack;
import unsw.loopmania.model.Enemies.*;
import unsw.loopmania.model.Character;


public class SwordAttackTest {
    /**
     * Test SwordAttack strategy damage on enemies
     */
    @Test
    public void swordExecuteTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        Character attacker = new Character(dummyPosition);
        Slug slug = new Slug(dummyPosition);
        Vampire vampire = new Vampire(dummyPosition);
        Zombie zombie = new Zombie(dummyPosition);
        AttackStrategy swordAttack = new SwordAttack();
        // Test simple sword attack
        swordAttack.execute(attacker, slug, 0, 0, false, 0);
        swordAttack.execute(attacker, vampire, 0, 0, false, 0);
        swordAttack.execute(attacker, zombie, 0, 0, false, 0);
        assertEquals(slug.getHealth(), -6);
        assertEquals(vampire.getHealth(), 34);
        assertEquals(zombie.getHealth(), 4);
        // Check campfire doubles character damage
        slug.setHealth(10);
        vampire.setHealth(50);
        zombie.setHealth(20);
        swordAttack.execute(attacker, slug, 0, 0, true, 0);
        swordAttack.execute(attacker, vampire, 0, 0, true, 0);
        swordAttack.execute(attacker, zombie, 0, 0, true, 0);
        assertEquals(slug.getHealth(), -22);
        assertEquals(vampire.getHealth(), 18);
        assertEquals(zombie.getHealth(), -12);
    }
}
