package unsw.loopmania.model.Items.BasicItems;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Equipped or unequipped Helmet in the backend world
 */
public class Helmet extends BasicItem {
    
    private static int buyPrice = 50;
    private int damageDealtReduction = 5;
    private int scalarDamageReduction = 20;
    private static String type = "Helmet";

    /**
     * Constructor for Helmet
     * @param x - x-coordinate for Helmet
     * @param y - y-coordinate for Helmet
     */
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, buyPrice, type);
    }

    /**
     * Getter for reduction in damage dealt by character
     * @return damageDealtReduction
     */
    @Override
    public int getScalarDamageReduction() {
        return damageDealtReduction;
    }

    /**
     * Getter for scalar damage reduction in damage recieved
     * @return scalarDamageReduction (%)
     */
    public int getVampireCritChanceReduction() {
        return scalarDamageReduction;
    }
}