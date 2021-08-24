package unsw.loopmania.model.Items.BasicItems;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.AttackStrategy.*;

/**
 * Equipped or unequipped sword in the backend world
 */
public class Sword extends BasicItem {
    
    private static int buyPrice = 40;
    private static String type = "Weapon";
    private static AttackStrategy strategy = new SwordAttack();

    /**
     * Constructor for Sword
     * @param x - x-coordinate for Sword
     * @param y - y-coordinate for Sword
     */
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, buyPrice, type);
    }

    /**
     * Getter for attack strategy
     */
    public AttackStrategy getAttackStrategy() {
        return strategy;
    }
}
