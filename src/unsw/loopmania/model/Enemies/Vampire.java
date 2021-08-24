package unsw.loopmania.model.Enemies;

import java.util.Random;

import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.AttackStrategy.AttackStrategy;
import unsw.loopmania.model.AttackStrategy.VampireAttack;

public class Vampire extends Enemy {

    private static int expReward = 800;
    private static int goldReward = 50;
    private static int battleRadius = 2;
    private static int supportRadius = 3;
    private static int speed = 3; // Tiles per tick
    private static AttackStrategy strategy = new VampireAttack();
    private static int damage = 25;
    private static int health = 50;
    private static int critChance = 40;
    // for running away from campfire
    private boolean inCampfireRange;
    private int runAwayTicks = 0;

    /**
     * Constructor for Vampire
     * @param position - current position on map
     * @param health
     * @param type
     */
    public Vampire(PathPosition position) {
        super(position, health, damage, speed);
    }

    /**
     * Movement for vampire is the same as other basic enemies until
     * it has the runAway debuff from reaching a campfire
     */
    @Override
    public void move() {
        if (runAwayTicks > 0) {
            for(int i = 0; i < speed; i++)
                moveUpPath();
            runAwayTicks--;
        } else {
            int directionChoice = (new Random()).nextInt(4);
            if (directionChoice == 0){
                for(int i = 0; i < speed; i++)
                    moveUpPath();
            } else if (directionChoice == 1) {
                for(int i = 0; i < speed; i++)
                    moveDownPath();
            }
        }
    }

     /**
     * Getter for EXP reward when killed
     * @return EXP reward
     */
    public int getExpReward() {
        return expReward;
    }

    /**
     * Getter for gold reward when killed
     * @return gold reward
     */
    public int getGoldReward() {
        return goldReward;
    }

    /**
     * Getter for battle radius of slug
     * @return battle radius (tiles)
     */
    public int getBattleRadius() {
        return battleRadius;
    }

    /**
     * Getter for support radius of slug
     * @return support radius (tiles)
     */
    public int getSupportRadius() {
        return supportRadius;
    }

    public int getCritChance() {
        return critChance;
    }

    public boolean getInCampfireRange() {
        return inCampfireRange;
    }

    /**
     * Getter for attack strategy
     */
    @Override
    public AttackStrategy getAttackStrategy() {
        return strategy;
    }

    /**
     * Gives the vampire a running away debuff for a set number of ticks
     * which will decrease in the move function before resuming normal
     * movement
     * @param inCampfireRange
     */
    public void setInCampfireRange(boolean inCampfireRange) {
        if (runAwayTicks > 0) {
            this.inCampfireRange = inCampfireRange;
        } else if (inCampfireRange) {
            this.inCampfireRange = inCampfireRange;
            runAwayTicks = 3;
        }
    }
}
