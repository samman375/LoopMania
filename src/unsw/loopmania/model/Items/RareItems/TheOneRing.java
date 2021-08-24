package unsw.loopmania.model.Items.RareItems;

import javafx.beans.property.SimpleIntegerProperty;

import unsw.loopmania.model.Character;

/**
 * Equipped or unequipped The One Ring in the backend world
 */
public class TheOneRing extends RareItem {

    /**
     * Constructor for TheOneRing
     * @param x - x-coordinate for TheOneRing
     * @param y - y-coordinate for TheOneRing
     */
    public TheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    /**
     * Does nothing unless item is health potion
     */
    public void usePotion(Character character) {
        if (character.isDead()) {
            character.setHealth(100);
        }
    }

}