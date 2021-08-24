package unsw.loopmania.model.Items.BasicItems;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.Items.Item;

/**
 * Abstract class for equipped or unequipped basic items in the backend world
 */
public abstract class BasicItem extends Item {
    
    private int buyPrice;
    private static int sellPrice = 20;

    /**
     * Constructor for BasicItem
     * @param x - x-coordinate of item
     * @param y - y-coordinate of item
     * @param buyPrice - buyPrice of item
     */
    public BasicItem(SimpleIntegerProperty x, SimpleIntegerProperty y, int buyPrice, String type) {
        super(x, y, sellPrice, type);
        this.buyPrice = buyPrice;
    }

    /**
     * Getter for buyPrice
     * @return buyPrice
     */
    public int getBuyPrice() {
        return this.buyPrice;
    }
}
