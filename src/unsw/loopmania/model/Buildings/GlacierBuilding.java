package unsw.loopmania.model.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.PathPosition;

public class GlacierBuilding extends Building {

    private PathPosition pathPosition;

    public GlacierBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public GlacierBuilding (PathPosition pathPosition) {
        super(pathPosition.getX(), pathPosition.getY());
        this.pathPosition = pathPosition;
    }

    public PathPosition getPathPosition() {
        return pathPosition;
    }

    public void setPathPosition(PathPosition pathPosition) {
        this.pathPosition = pathPosition;
    }

}
