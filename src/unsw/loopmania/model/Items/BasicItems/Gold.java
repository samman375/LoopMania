package unsw.loopmania.model.Items.BasicItems;

import javafx.beans.property.SimpleIntegerProperty;

public class Gold extends BasicItem {

    private int goldFromGround = 20;
    private static int buyPrice = 0;
    private static String type = "Gold";

    /**
     * Constructor for Gold
     *
     * @param x        - x-coordinate of gold
     * @param y        - y-coordinate of gold
     */
    public Gold(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, buyPrice, type);
    }

    public int getGoldFromGround() {
        return goldFromGround;
    }
}
