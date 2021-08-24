package test.AttackStrategyTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.AttackStrategy.AttackStrategy;
import unsw.loopmania.model.AttackStrategy.SlugAttack;
import unsw.loopmania.model.Enemies.*;
import unsw.loopmania.model.AlliedSoldier;
import unsw.loopmania.model.Character;


public class SlugAttackTest {
    /**
     * Test SlugAttack strategy for an attack on a character
     */
    @Test
    public void attackCharacterExecuteTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        Character character = new Character(dummyPosition);
        Slug attacker = new Slug(dummyPosition);
        AttackStrategy slugAttack = new SlugAttack();
        // Test attack with no defences
        slugAttack.execute(attacker, character, 0, 0, false, 0);
        assertEquals(character.getHealth(), 93);
        // Check campfire does not affect damage
        character.setHealth(100);
        slugAttack.execute(attacker, character, 0, 0, true, 0);
        assertEquals(character.getHealth(), 93);
        // Test attack with fixedDefences
        character.setHealth(100);
        slugAttack.execute(attacker, character, 0, 2, false, 0);
        assertEquals(character.getHealth(), 95);
        // Test attack with scalarDefences
        character.setHealth(100);
        slugAttack.execute(attacker, character, 30, 0, false, 0);
        assertEquals(character.getHealth(), 95);
        // Test attack with both fixed and scalarDefences
        character.setHealth(100);
        slugAttack.execute(attacker, character, 50, 2, false, 0);
        assertEquals(character.getHealth(), 98);
    }

    /**
     * Test BasicAttack strategy for an attack on an alliedSoldier
     */
    @Test
    public void attackAllyExecuteTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        AlliedSoldier ally = new AlliedSoldier(dummyPosition);
        Slug attacker = new Slug(dummyPosition);
        AttackStrategy slugAttack = new SlugAttack();
        // Test attack with no defences
        slugAttack.execute(attacker, ally, 0, 0, false, 0);
        assertEquals(ally.getHealth(), 43);
        // Check campfire does not affect damage
        ally.setHealth(50);
        slugAttack.execute(attacker, ally, 0, 0, true, 0);
        assertEquals(ally.getHealth(), 43);
        // Test attack with defences does not affect damage
        ally.setHealth(50);
        slugAttack.execute(attacker, ally, 20, 2, false, 0);
        assertEquals(ally.getHealth(), 43);
    }
}
