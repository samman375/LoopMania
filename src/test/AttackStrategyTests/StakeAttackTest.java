package test.AttackStrategyTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.AttackStrategy.AttackStrategy;
import unsw.loopmania.model.AttackStrategy.StakeAttack;
import unsw.loopmania.model.Enemies.*;
import unsw.loopmania.model.Character;


public class StakeAttackTest {
    /**
     * Test StakeAttack strategy damage on slugs, zombies
     */
    @Test
    public void stakeExecuteTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        Character attacker = new Character(dummyPosition);
        Slug slug = new Slug(dummyPosition);
        Zombie zombie = new Zombie(dummyPosition);
        AttackStrategy stakeAttack = new StakeAttack();
        // Test simple stake attack
        stakeAttack.execute(attacker, slug, 0, 0, false, 0);
        stakeAttack.execute(attacker, zombie, 0, 0, false, 0);
        assertEquals(slug.getHealth(), -1);
        assertEquals(zombie.getHealth(), 9);
        // Check campfire doubles character damage
        slug.setHealth(10);
        zombie.setHealth(20);
        stakeAttack.execute(attacker, slug, 0, 0, true, 0);
        stakeAttack.execute(attacker, zombie, 0, 0, true, 0);
        assertEquals(slug.getHealth(), -12);
        assertEquals(zombie.getHealth(), -2);
    }

    /**
     * Test StakeAttack strategy bonus damage on vampires
     */
    @Test
    public void stakeCritExecuteTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        Character attacker = new Character(dummyPosition);
        Vampire vampire = new Vampire(dummyPosition);
        AttackStrategy stakeAttack = new StakeAttack();
        // Test simple stake attack
        stakeAttack.execute(attacker, vampire, 0, 0, false, 0);
        assertEquals(vampire.getHealth(), 19);
        // Check campfire doubles character damage
        vampire.setHealth(50);
        stakeAttack.execute(attacker, vampire, 0, 0, true, 0);
        assertEquals(vampire.getHealth(), -12);
    }
}