package unsw.loopmania.model.Items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.Character;
import unsw.loopmania.model.StaticEntity;
import unsw.loopmania.model.AttackStrategy.AttackStrategy;

public abstract class Item extends StaticEntity {
    
    private int sellPrice;
    private int discardGold = this.getSellPrice()/2;
    private static int discardExp = 100;
    private String type;

    /**
     * Constructor for Item
     * @param x - x-coordinate of item
     * @param y - y-coordinate of item
     * @param sellPrice - sellPrice of item
     */
    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y, int sellPrice, String type) {
        super(x, y);
        this.sellPrice = sellPrice;
        this.type = type;
    }

    /**
     * Getter for sellPrice
     * @return sellPrice
     */
    public int getSellPrice() {
        return this.sellPrice;
    }

    /**
     * Getter for type corresponding to inventory slot (Weapon, Shield, Armour, etc.)
     */
    public String getType() {
        return this.type;
    }

    /**
     * Getter for discard item Gold
     */
    public int getDiscardGold() {
        return this.discardGold;
    }

    /**
     * Getter for discard item Exp
     */
    public int getDiscardExp() {
        return discardExp;
    }

    /**
     * Does nothing unless item is HealthPotion/TheOneRing
     */
    public void usePotion(Character character) {
        return;
    }

    /**
     * Getter for attack strategy. Null by default.
     */
    public AttackStrategy getAttackStrategy() {
        return null;
    }

    /**
     * Getter for flat damage. Set to 0 by defaut.
     */
    public int getFlatDamageReduction() {
        return 0;
    }

    /**
     * Getter for scalar damage. Set to 0 by defaut.
     */
    public int getScalarDamageReduction() {
        return 0;
    }

    /**
     * Getter for reduction in chance of a vampire critical strike. Set to 0 by default
     */
    public int getVampireCritChanceReduction() {
        return 0;
    }

    /**
     * Getter for flat damage reduction in damage recieved against a boss.
     * Set to 0 by default
     */
    public int getBossFlatDamageReduction() {
        return 0;
    }
}
