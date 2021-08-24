package unsw.loopmania.model.Items.BasicItems;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.AttackStrategy.*;

/**
 * Equipped or unequipped Staff in the backend world
 */
public class Staff extends BasicItem {
    
    private static int buyPrice = 50;
    private static String type = "Weapon";
    private static AttackStrategy strategy = new StaffAttack();

    /**
     * Constructor for Staff
     * @param x - x-coordinate for Staff
     * @param y - y-coordinate for Staff
     */
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, buyPrice, type);
    }

     /**
     * Getter for attack strategy
     */
    public AttackStrategy getAttackStrategy() {
        return strategy;
    }
}
