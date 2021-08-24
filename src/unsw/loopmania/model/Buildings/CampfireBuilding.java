package unsw.loopmania.model.Buildings;

import javafx.beans.property.SimpleIntegerProperty;

public class CampfireBuilding extends Building {

    private static int battleRadius = 2;
    private static int damageMultiplier = 2;
    private static int scareRadius = 3;

    public CampfireBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public int getBattleRadius() {
        return battleRadius;
    }

    @Override
    public int getDamageMultiplier() {
        return damageMultiplier;
    }

    public int getScareRadius() {
        return scareRadius;
    }

}
