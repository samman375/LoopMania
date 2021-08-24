package unsw.loopmania.model.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.Enemies.Vampire;
import unsw.loopmania.model.PathPosition;

/**
 * a basic form of building in the world
 */
public class VampireCastleBuilding extends Building {

    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Spawn vampires every 5 cycles of the path completed by the Character
     * @param cycle number of path cycles the Character had completed
     * @param pathPosition position where vampire is spawned
     * @return vampire
     */
    public Vampire spawnVampire(boolean completedACycle, int cycles, PathPosition pathPosition) {
        if (completedACycle && cycles > 0 && cycles % 5 == 0) {
            return new Vampire(pathPosition);
        }
        return null;
    }

}
