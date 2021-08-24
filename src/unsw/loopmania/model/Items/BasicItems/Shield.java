package unsw.loopmania.model.Items.BasicItems;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Equipped or unequipped Shield in the backend world
 */
public class Shield extends BasicItem {
    
    private static int buyPrice = 50;
    private int flatDamageReduction = 5;
    private int vampireCritChanceReduction = 60;
    private static String type = "Shield";

    /**
     * Constructor for Shield
     * @param x - x-coordinate for Shield
     * @param y - y-coordinate for Shield
     */
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, buyPrice, type);
    }

    /**
     * Getter for flat damage reduction in damage recieved
     * @return flatDamageReduction
     */
    @Override
    public int getFlatDamageReduction() {
        return flatDamageReduction;
    }

    /**
     * Getter for reduction in chance of a vampire critical strike
     * @return vampireCritChanceReduction (%)
     */
    @Override
    public int getVampireCritChanceReduction() {
        return vampireCritChanceReduction;
    }
}