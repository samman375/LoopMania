package unsw.loopmania.model;

import unsw.loopmania.model.AttackStrategy.AttackStrategy;
import unsw.loopmania.model.AttackStrategy.BasicAttack;

public class AlliedSoldier extends MovingEntity {

    private static int health = 50;
    private int max;
    private int remainingLifeCycles;
    private static int trancedEnemyLifeCycle = 3;
    private int trancedEnemyIndex;
    private static int damage = 6;
    private static int speed = 0;
    private static AttackStrategy strategy = new BasicAttack();

    public AlliedSoldier(PathPosition position) {
        super(position, health, speed);
        // Default -1 indicates not a tranced enemy
        this.remainingLifeCycles = -1;
        this.trancedEnemyIndex = -1;
    }

    /**
     * Getter for health
     */
    public int getHealth() {
        return health;
    }

    public int getMax() {
        return max;
    }

    /**
     * Setter for health
     */
    public void setHealth(int newHealth) {
        health = newHealth;
    }

    /**
     * Checks if allied soldier is dead
     */
    public boolean isDead() {
        return getHealth() == 0;
    }

    /**
     * Getter for allied soldier damage
     */
    @Override
    public int getDamage() {
        return damage;
    }

    /**
     * Setter for damage. Used when zombie is tranced.
     * @param newDamage
     */
    public void setDamage(int newDamage) {
        damage = newDamage;
    }

    /**
     * Sets life cycle of a tranced enemy.
     */
    public void setTrancedLifeCycle() {
        this.remainingLifeCycles = trancedEnemyLifeCycle;
    }

    public void reduceTrancedLifeCycle() {
        this.remainingLifeCycles -= 1;
    }

    /**
     * Getter for tranced enemy life cycle
     * @return
     */
    public int getTrancedLifeCycle() {
        return remainingLifeCycles;
    }

    /**
     * Sets index of original enemy in enemies array of battle
     * @param index - index of original enemy
     */
    public void setTrancedEnemyIndex(int index) {
        this.trancedEnemyIndex = index;
    }

    /**
     * Returns index of tranced enemy in battle enemies array
     */
    public int getTrancedEnemyIndex() {
        return trancedEnemyIndex;
    }

    /**
     * Getter for attack strategy
     */
    @Override
    public AttackStrategy getAttackStrategy() {
        return strategy;
    }

    @Override
    public void setStuckOnGlacier(boolean stuckOnGlacier) {
        return;
    }

    @Override
    public boolean getUnfreeze() {
        return true;

    }

    @Override
    public void setUnfreeze(boolean unfreeze) {
        return;

    }

}
