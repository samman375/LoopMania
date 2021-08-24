package unsw.loopmania.model.Enemies;

import unsw.loopmania.model.MovingEntity;
import unsw.loopmania.model.PathPosition;

import java.util.Random;

/**
 * a basic form of enemy in the backend world
 */
public abstract class Enemy extends MovingEntity {

    private String type;
    private int damage;
    private boolean stuckOnGlacier;
    private int frozenTicks = 0;
    private boolean unfreeze = false;


    /**
     * Constructor for Basic Enemy
     * @param position - position on map
     * @param health
     * @param damage
     */
    public Enemy(PathPosition position, int health, int damage, int speed) {
        super(position, health, speed);
        this.damage = damage;
    }

    /**
     * move the enemy
     */
    public void move(){
        // this basic enemy moves in a random direction... 25% chance up or down, 50% chance not at all...
        if (frozenTicks > 0) {
            frozenTicks--;
        } else {
            unfreeze = false;
            // this basic enemy moves in a random direction... 25% chance up or down, 50% chance not at all...
            int directionChoice = (new Random()).nextInt(4);
            if (directionChoice == 0){
                moveUpPath();
            }
            else if (directionChoice == 1){
                moveDownPath();
            }
        }
    }

    public String getType() {
        return type;
    }

    /**
     * Getter for damage
     * @return damage
     */
    public int getDamage() {
        return this.damage;
    }

    public abstract int getExpReward();
    public abstract int getGoldReward();
    public abstract int getSupportRadius();
    public abstract int getBattleRadius();

    public boolean getStuckOnGlacier() {
        return stuckOnGlacier;
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
            frozenTicks = 3 * getSpeed();
        }
    }

    public void setUnfreeze(boolean unfreeze) {
        this.unfreeze = unfreeze;
    }
}
