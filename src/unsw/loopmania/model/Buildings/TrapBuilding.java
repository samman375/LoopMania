package unsw.loopmania.model.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.Enemies.Enemy;

public class TrapBuilding extends Building {

    private PathPosition pathPosition;

    public TrapBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public TrapBuilding(PathPosition pathPosition) {
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
     * Damage an enemy's health when the enemy passes over the trap
     * Deals 10 damage
     * @param enemy Enemy
     * @return enemy's new health after damage has been dealt
     */
    public int damageEnemy(Enemy enemy) {
        int damage = 10;
        enemy.reduceHealth(damage);
        return enemy.getHealth();
    }
}
