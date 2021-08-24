package unsw.loopmania.model.Buildings;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.Items.Item;
import unsw.loopmania.model.Items.BasicItems.*;

public class HerosCastleBuilding extends Building {

    private int lastPurchasedHP = 0;
    private int lastPurchasedPG = 0;

    public HerosCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public int buyItem(BasicItem item, List<Item> unequippedInventory) {
        unequippedInventory.add(item);
        return item.getBuyPrice();
    }

    public int sellItem(BasicItem item, List<Item> unequippedInventory) {
        unequippedInventory.remove(item);
        int price = item.getSellPrice();
        item.destroy();
        return price;
    }

    // Check if item isPurchaseable
    public static boolean isPurchaseable (int gold, Item item) {
        if (item instanceof Helmet) {
            Helmet helmet = (Helmet) item;
            return (gold >= helmet.getBuyPrice());
        }
        if (item instanceof Armour) {
            Armour armour = (Armour) item;
            return (gold >= armour.getBuyPrice());
        }
        if (item instanceof Shield) {
            Shield shield = (Shield) item;
            return (gold >= shield.getBuyPrice());
        }
        if (item instanceof Sword) {
            Sword sword = (Sword) item;
            return (gold >= sword.getBuyPrice());
        }
        if (item instanceof Staff) {
            Staff staff = (Staff) item;
            return (gold >= staff.getBuyPrice());
        }
        if (item instanceof Stake) {
            Stake stake = (Stake) item;
            return (gold >= stake.getBuyPrice());
        }
        if (item instanceof HealthPotion) {
            HealthPotion healthPotion = (HealthPotion) item;
            return (gold >= healthPotion.getBuyPrice());
        }
        return false;
    }

    /**
     * Check if mode allows for given item to be bought
     * @param item item player wants to purchase
     * @return true if purchase is valid, otherwise false
     */
    public boolean isValidPurchase(String gameMode, Item item, int cycles) {
        if (gameMode.equals("Survival")) {
            if (item instanceof HealthPotion) { ;
                // check when is the last time the character purchased a health potion
                if (lastPurchasedHP == cycles) return false;
                else {
                    lastPurchasedHP = cycles;
                    return true;
                }
            }
        } else if (gameMode.equals("Berserker")) {
            if (item instanceof Armour || item instanceof Helmet || item instanceof Shield) {
                // check when is the last time the character purchased a protective gear
                if (lastPurchasedPG == cycles) return false;
                else {
                    lastPurchasedPG = cycles;
                    return true;
                }
            }
        }
        return true;
    }

}
