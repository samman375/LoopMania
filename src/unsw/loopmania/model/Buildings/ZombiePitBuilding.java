package unsw.loopmania.model.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.Enemies.Zombie;
import unsw.loopmania.model.PathPosition;


public class ZombiePitBuilding extends Building {

    public ZombiePitBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * Spawn zombie every cycle of the path completed by the Character
     * @param cycle number of path cycles the Character had completed
     * @param position position where zombie is spawned
     * @return zombie
     */
    public Zombie spawnZombie(boolean completedACycle, int cycles, PathPosition pathPosition) {
        if (completedACycle && cycles > 0) {
            return new Zombie(pathPosition);
        }
        return null;
    }

}
