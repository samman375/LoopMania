package unsw.loopmania.model.Items.BasicItems;

import javafx.beans.property.SimpleIntegerProperty;

public class DoggieCoin extends BasicItem {
    
    public static int sellValue = 0;
    private static int buyPrice = 0;
    private static String type = "DoggieCoin";
    
     /**
     * Constructor for Gold
     *
     * @param x        - x-coordinate of gold
     * @param y        - y-coordinate of gold
     */
    public DoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, buyPrice, type);
    }

    public int sellValue() {
        return sellValue;
    }

    public static void updateSellValue(int newValue) {
        sellValue = newValue;
    }

}
