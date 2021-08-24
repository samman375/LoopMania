package test.AttackStrategyTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.AttackStrategy.AttackStrategy;
import unsw.loopmania.model.AttackStrategy.VampireAttack;
import unsw.loopmania.model.Enemies.Vampire;
// import unsw.loopmania.model.AlliedSoldier;
import unsw.loopmania.model.Character;


public class VampireAttackTest {
    /**
     * Test VampireAttack strategy for a attacks on a character
     */
    @Test
    public void attackCharacterExecuteTest() {
        List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
        dummyPath.add(new Pair<>(0,0));
        PathPosition dummyPosition = new PathPosition(0, dummyPath);
        Character character = new Character(dummyPosition);
        Vampire attacker = new Vampire(dummyPosition);
        AttackStrategy vampireAttack = new VampireAttack();
        Random random = new Random(9);
        
        // Test attack with no defences 5 times
        vampireAttack.execute(attacker, character, 0, 0, false, 0);
        Boolean expectedCrit = random.nextInt(99) < 40;
        int baseDamage = 25;
        if (expectedCrit) {
            baseDamage += random.nextInt(9) + 1;
        }
        assertEquals(character.getHealth(), 100 - baseDamage);
        character.setHealth(100);
        
        vampireAttack.execute(attacker, character, 0, 0, false, 0);
        expectedCrit = random.nextInt(99) < 40;
        baseDamage = 25;
        if (expectedCrit) {
            baseDamage += random.nextInt(9) + 1;
        }
        assertEquals(character.getHealth(), 100 - baseDamage);
        character.setHealth(100);

        vampireAttack.execute(attacker, character, 0, 0, false, 0);
        expectedCrit = random.nextInt(99) < 40;
        baseDamage = 25;
        if (expectedCrit) {
            baseDamage += random.nextInt(9) + 1;
        }
        assertEquals(character.getHealth(), 100 - baseDamage);
        character.setHealth(100);
        
        vampireAttack.execute(attacker, character, 0, 0, false, 0);
        expectedCrit = random.nextInt(99) < 40;
        baseDamage = 25;
        if (expectedCrit) {
            baseDamage += random.nextInt(9) + 1;
        }
        assertEquals(character.getHealth(), 100 - baseDamage);
        character.setHealth(100);

        vampireAttack.execute(attacker, character, 0, 0, false, 0);
        expectedCrit = random.nextInt(99) < 40;
        baseDamage = 25;
        if (expectedCrit) {
            baseDamage += random.nextInt(9) + 1;
        }
        assertEquals(character.getHealth(), 100 - baseDamage);
        character.setHealth(100);

        // Check campfire does not affect damage
        vampireAttack.execute(attacker, character, 0, 0, true, 0);
        expectedCrit = random.nextInt(99) < 40;
        baseDamage = 25;
        if (expectedCrit) {
            baseDamage += random.nextInt(9) + 1;
        }
        assertEquals(character.getHealth(), 100 - baseDamage);
        character.setHealth(100);

        // Test attack with fixedDefences
        vampireAttack.execute(attacker, character, 0, 5, false, 0);
        expectedCrit = random.nextInt(99) < 40;
        baseDamage = 25 - 5;
        if (expectedCrit) {
            baseDamage += random.nextInt(9) + 1;
        }
        assertEquals(character.getHealth(), 100 - baseDamage);
        character.setHealth(100);

        // Test attack with scalarDefences
        vampireAttack.execute(attacker, character, 30, 0, false, 0);
        expectedCrit = random.nextInt(99) < 40;
        baseDamage = 25;
        if (expectedCrit) {
            baseDamage += random.nextInt(9) + 1;
        }
        double scaledDamage = baseDamage * 0.7;
        scaledDamage -= 1; // Account for rounding up damage
        assertEquals(character.getHealth(), 100 - (int)scaledDamage);
        character.setHealth(100);

        // Test attack with both fixed and scalarDefences
        // vampireAttack.execute(attacker, character, 60, 3, false, 0);
        // expectedCrit = random.nextInt(99) < 40;
        // baseDamage = 25;
        // if (expectedCrit) {
        //     baseDamage += random.nextInt(9) + 1;
        // }
        // scaledDamage = baseDamage * 0.4;
        // scaledDamage -= 3; // Account for fixedDefences
        // scaledDamage -= 1; // Account for rounding up damage
        // assertEquals(character.getHealth(), 100 - (int)scaledDamage);

        // Test crit reduction
    }

    /**
     * Test BasicAttack strategy for an attack on an alliedSoldier
     */
    // @Test
    // public void attackAllyExecuteTest() {
    //     List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
    //     dummyPath.add(new Pair<>(0,0));
    //     PathPosition dummyPosition = new PathPosition(0, dummyPath);
    //     AlliedSoldier ally = new AlliedSoldier(dummyPosition);
    //     Slug attacker = new Slug(dummyPosition);
    //     AttackStrategy slugAttack = new SlugAttack();
    //     // Test attack with no defences
    //     slugAttack.execute(attacker, ally, 0, 0, false, 0);
    //     assertEquals(ally.getHealth(), 43);
    //     // Check campfire does not affect damage
    //     ally.setHealth(50);
    //     slugAttack.execute(attacker, ally, 0, 0, true, 0);
    //     assertEquals(ally.getHealth(), 43);
    //     // Test attack with defences does not affect damage
    //     ally.setHealth(50);
    //     slugAttack.execute(attacker, ally, 20, 2, false, 0);
    //     assertEquals(ally.getHealth(), 43);
    // }
}

