package unsw.loopmania.model;

import unsw.loopmania.model.AttackStrategy.*;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {

    private static AttackStrategy strategy = new BasicAttack();
    private static int health = 100;
    private static int baseDamage = 5;
    private static int speed = 2; // Ticks per tile
    private boolean stuckOnGlacier;
    private int frozenTicks = 0;
    private boolean unfreeze = false;
    private boolean inCloakingTowerRange;
    private int cloakTicks = 0;
    private int level = 1;
    private int remainingStunnedCycles;
    private static int stunnedCycle = 1;

    public Character(PathPosition position) {
        super(position, health, speed);
        this.remainingStunnedCycles = 0;
    }

    public void move(){
        if (frozenTicks > 0) {
            frozenTicks--;
        } else {
            unfreeze = false;
            moveDownPath();
        }
    }
    /**
     * Getter for damage of character (base + weapons).
     * Gain 1 bonus damage per level (total 5 + 1 per level)
     * @return damage
     */
    public int getDamage() {
        return baseDamage + level;
    }

    /**
     * Getter for level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Levels up character by 1
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Getter for attack strategy
     */
    public AttackStrategy getAttackStrategy() {
        return strategy;
    }

    public boolean getStuckOnGlacier() {
        return stuckOnGlacier;
    }

    public int getFrozenTicks() {
        return frozenTicks;
    }

    public boolean getUnfreeze() {
        return unfreeze;
    }

    @Override
    public void setStuckOnGlacier(boolean stuckOnGlacier) {
        if (frozenTicks > 0) {
            this.stuckOnGlacier = stuckOnGlacier;
        } else if (stuckOnGlacier && unfreeze == false) {
            this.stuckOnGlacier = stuckOnGlacier;
            frozenTicks = 3;
        }
    }

    public void setUnfreeze(boolean unfreeze) {
        this.unfreeze = unfreeze;
    }

    public boolean getInCloakingTowerRange() {
        return inCloakingTowerRange;
    }

    public void setInCloakingTowerRange(boolean inCloakingTowerRange) {
        if (cloakTicks > 0) {
            this.inCloakingTowerRange = inCloakingTowerRange;
        } else if (inCloakingTowerRange) {
            this.inCloakingTowerRange = inCloakingTowerRange;
            cloakTicks = 3;
        }
    }
    
        /**
     * Getter for remaining stunned cycles
     * @return remaining stunned cycles
     */
    public int getStunnedCycle() {
        return remainingStunnedCycles;
    }

    /**
     * Setter for stunned cycles, default 1
     */
    public void setStunnedCycles() {
        this.remainingStunnedCycles = stunnedCycle;
    }


    public void reduceStunnedCycle() {
        this.remainingStunnedCycles -= 1;
    }
}
