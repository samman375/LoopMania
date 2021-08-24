package unsw.loopmania.model.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.AlliedSoldier;
import unsw.loopmania.model.PathPosition;

public class BarracksBuilding extends Building {

    private PathPosition pathPosition;

    public BarracksBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public BarracksBuilding (PathPosition pathPosition) {
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
     * Creates an allied soldier when Character passes over barracks
     * @param x x coordinate of allied soldier
     * @param y y coordinate of allied soldier
     * @return allied soldier
     */
    public AlliedSoldier spawnAlliedSoldier(PathPosition pathPosition) {
        return new AlliedSoldier(pathPosition);
    }
}
