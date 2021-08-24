package unsw.loopmania.model.Buildings;

import unsw.loopmania.model.PathPosition;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.Character;

public class VillageBuilding extends Building {

    private PathPosition pathPosition;
    
    public VillageBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    
    public VillageBuilding (PathPosition pathPosition) {
        super(pathPosition.getX(), pathPosition.getY());
        this.pathPosition = pathPosition;
    }

    public PathPosition getPathPosition() {
        return pathPosition;
    }

    public void setPathPosition(PathPosition pathPosition) {
        this.pathPosition = pathPosition;
    }

    /**
     * Refill the Character's health when the Character passes over the village
     * @param character Character
     */
    public void gainHealth(Character character) {
        int healthBonus = 50;
        character.gainHealth(healthBonus);
    }

}
