package unsw.loopmania.model.Items.BasicItems;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Equipped or unequipped Armour in the backend world
 */
public class Armour extends BasicItem {
    
    private static int buyPrice = 150;
    private int scalarDamageReduction = 50;
    private static String type = "Armour";

    /**
     * Constructor for Armour
     * @param x - x-coordinate for Armour
     * @param y - y-coordinate for Armour
     */
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, buyPrice, type);
    }

    /**
     * Getter for scalar damage reduction in damage recieved
     * @return scalarDamageReduction (%)
     */
    @Override
    public int getScalarDamageReduction() {
        return scalarDamageReduction;
    }
}
