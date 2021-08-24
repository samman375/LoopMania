package test;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.Test;

import unsw.loopmania.model.Buildings.HerosCastleBuilding;
import unsw.loopmania.model.Items.BasicItems.*;
import unsw.loopmania.model.Items.Item;

public class HerosCastleMenuTest {

    HerosCastleBuilding herosCastleBuilding = new HerosCastleBuilding(new SimpleIntegerProperty(),
                                                                      new SimpleIntegerProperty());
    List<Item> unequippedInventory = new ArrayList<Item>();

    @Test
    public void BuySellSwordTest() {

        Sword sword = new Sword(new SimpleIntegerProperty(),
                                new SimpleIntegerProperty());

        // Buy item
        herosCastleBuilding.buyItem(sword, unequippedInventory);
        assert(unequippedInventory.get(0) == sword);

        // Sell item
        herosCastleBuilding.sellItem(sword, unequippedInventory);
        assert(unequippedInventory.isEmpty());
    }

    @Test
    public void BuySellStakeTest() {

        Stake stake = new Stake(new SimpleIntegerProperty(),
                                new SimpleIntegerProperty());

        // Buy item
        herosCastleBuilding.buyItem(stake, unequippedInventory);
        assert(unequippedInventory.get(0) == stake);

        // Sell item
        herosCastleBuilding.sellItem(stake, unequippedInventory);
        assert(unequippedInventory.isEmpty());
    }

    @Test
    public void BuySellStaffTest() {

        Staff staff = new Staff(new SimpleIntegerProperty(),
                                new SimpleIntegerProperty());

        // Buy item
        herosCastleBuilding.buyItem(staff, unequippedInventory);
        assert(unequippedInventory.get(0) == staff);

        // Sell item
        herosCastleBuilding.sellItem(staff, unequippedInventory);
        assert(unequippedInventory.isEmpty());
    }

    @Test
    public void BuySellArmourTest() {

        Armour armour = new Armour(new SimpleIntegerProperty(),
                                   new SimpleIntegerProperty());

        // Buy item
        herosCastleBuilding.buyItem(armour, unequippedInventory);
        assert(unequippedInventory.get(0) == armour);

        // Sell item
        herosCastleBuilding.sellItem(armour, unequippedInventory);
        assert(unequippedInventory.isEmpty());
    }

    @Test
    public void BuySellHelmetTest() {

        Helmet helmet = new Helmet(new SimpleIntegerProperty(),
                                   new SimpleIntegerProperty());

        // Buy item
        herosCastleBuilding.buyItem(helmet, unequippedInventory);
        assert(unequippedInventory.get(0) == helmet);

        // Sell item
        herosCastleBuilding.sellItem(helmet, unequippedInventory);
        assert(unequippedInventory.isEmpty());
    }

    @Test
    public void BuySellShieldTest() {

        Shield shield = new Shield(new SimpleIntegerProperty(),
                                   new SimpleIntegerProperty());

        // Buy item
        herosCastleBuilding.buyItem(shield, unequippedInventory);
        assert(unequippedInventory.get(0) == shield);

        // Sell item
        herosCastleBuilding.sellItem(shield, unequippedInventory);
        assert(unequippedInventory.isEmpty());
    }

    @Test
    public void failToBuyHPTest() {

        HealthPotion healthPotion = new HealthPotion(new SimpleIntegerProperty(),
                                                     new SimpleIntegerProperty());

        // Test when the character did not finish a cycle
        boolean success = herosCastleBuilding.isValidPurchase("Survival", healthPotion, 0);
        assert(!success);
    }

    @Test
    public void successToBuyHPTest() {

        herosCastleBuilding = new HerosCastleBuilding(new SimpleIntegerProperty(),
        new SimpleIntegerProperty());

        HealthPotion healthPotion = new HealthPotion(new SimpleIntegerProperty(),
                                                     new SimpleIntegerProperty());

        // Test when the character did not finish a cycle
        boolean success = herosCastleBuilding.isValidPurchase("Survival", healthPotion, 5);
        assert(success);

         // Buy item
         herosCastleBuilding.buyItem(healthPotion, unequippedInventory);
         assert(unequippedInventory.get(0) == healthPotion);

         // Sell item
         herosCastleBuilding.sellItem(healthPotion, unequippedInventory);
         assert(unequippedInventory.isEmpty());
    }

    @Test
    public void failToBuyPGTest() {

        Armour armour = new Armour(new SimpleIntegerProperty(),
                                                     new SimpleIntegerProperty());

        // Test when the character did not finish a cycle
        boolean success = herosCastleBuilding.isValidPurchase("Berserker", armour, 0);
        assert(!success);
    }

    @Test
    public void successToBuyPGTest() {

        herosCastleBuilding = new HerosCastleBuilding(new SimpleIntegerProperty(),
        new SimpleIntegerProperty());

        Armour armour = new Armour(new SimpleIntegerProperty(),
                                   new SimpleIntegerProperty());

        // Test when the character did not finish a cycle
        boolean success = herosCastleBuilding.isValidPurchase("Berserker", armour, 2);
        assert(success);

        // Buy item
        herosCastleBuilding.buyItem(armour, unequippedInventory);
        assert(unequippedInventory.get(0) == armour);

        // Sell item
        herosCastleBuilding.sellItem(armour, unequippedInventory);
        assert(unequippedInventory.isEmpty());

    }

    @Test
    public void buyTwoPG() {
        herosCastleBuilding = new HerosCastleBuilding(new SimpleIntegerProperty(),
                                                      new SimpleIntegerProperty());

        Armour armour = new Armour(new SimpleIntegerProperty(),
                                   new SimpleIntegerProperty());

        // Test when the character did not finish a cycle
        boolean success = herosCastleBuilding.isValidPurchase("Berserker", armour, 2);
        assert(success);

        // Buy item
        herosCastleBuilding.buyItem(armour, unequippedInventory);
        assert(unequippedInventory.get(0) == armour);

        // Test when the character tries to buy two protective gears in the same cycle
        boolean fail = herosCastleBuilding.isValidPurchase("Berserker", armour, 2);
        assert(!fail);
    }

    @Test
    public void buyTwoPotionsTest() {
        herosCastleBuilding = new HerosCastleBuilding(new SimpleIntegerProperty(),
                                                      new SimpleIntegerProperty());

        HealthPotion healthPotion = new HealthPotion(new SimpleIntegerProperty(),
                                                     new SimpleIntegerProperty());

        // Test when the character did not finish a cycle
        boolean success = herosCastleBuilding.isValidPurchase("Survival", healthPotion, 5);
        assert(success);

        // Buy item
        herosCastleBuilding.buyItem(healthPotion, unequippedInventory);
        assert(unequippedInventory.get(0) == healthPotion);

        // Test when the character tries to buy a second potion
        boolean fail = herosCastleBuilding.isValidPurchase("Survival", healthPotion, 5);
        assert(!fail);

    }
}

