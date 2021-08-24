package unsw.loopmania.model.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.AttackStrategy.AttackStrategy;
import unsw.loopmania.model.AttackStrategy.BasicAttack;


public class TowerBuilding extends Building {

    private int damage = 3;
    private int battleRadius = 3;
    AttackStrategy attackStrategy = new BasicAttack();

    public TowerBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public int getDamage() {
        return this.damage;
    }

    @Override
    public int getBattleRadius() {
        return this.battleRadius;
    }

    public AttackStrategy getAttackStrategy() {
        return this.attackStrategy;
    }

}
