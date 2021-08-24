package unsw.loopmania.model.Items.RareItems;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.AttackStrategy.*;

/**
 * Equipped or unequipped Anduril - The Flame of The West in the backend world
 */
public class Anduril extends RareItem {
    
    private AttackStrategy strategy = new AndurilAttack();

    /**
     * Constructor for Anduril
     * @param x - x-coordinate for Anduril
     * @param y - y-coordinate for Anduril
     */
    public Anduril(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Getter for attack strategy
     */
    public AttackStrategy getAttackStrategy() {
        return strategy;
    }
}
