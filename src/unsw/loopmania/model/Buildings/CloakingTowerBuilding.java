package unsw.loopmania.model.Buildings;

import javafx.beans.property.SimpleIntegerProperty;

public class CloakingTowerBuilding extends Building {

    public int cloakingRadius = 3;

    public CloakingTowerBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public int getCloakingRadius() {
        return cloakingRadius;
    }

}
