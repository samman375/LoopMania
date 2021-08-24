package unsw.loopmania.model.Items.RareItems;

import javafx.beans.property.SimpleIntegerProperty;

public class TreeStump extends RareItem {
    
    private int flatDamageReduction = 5;
    private int bossFlatDamageReduction = 20;

    /**
     * Constructor for TreeStump
     * @param x - x-coordinate for TreeStump
     * @param y - y-coordinate for TreeStump
     */
    public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Getter for flat damage reduction in damage recieved against a basic enemy
     */
    @Override
    public int getFlatDamageReduction() {
        return flatDamageReduction;
    }

    /**
     * Getter for flat damage reduction in damage recieved against a boss
     */
    @Override
    public int getBossFlatDamageReduction() {
        return bossFlatDamageReduction;
    }

}
