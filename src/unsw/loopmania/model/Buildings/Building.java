package unsw.loopmania.model.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.StaticEntity;

public abstract class Building extends StaticEntity {

    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    // Overridden getter for battle radius
    public int getBattleRadius() {
        return 0;
    };

    // Overridden getter for damage multiplier
    public int getDamageMultiplier() {
        return 0;
    }
}
