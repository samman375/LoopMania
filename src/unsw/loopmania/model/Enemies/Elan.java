package unsw.loopmania.model.Enemies;

import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.AttackStrategy.AttackStrategy;
import unsw.loopmania.model.AttackStrategy.ElanAttack;

public class Elan extends Enemy {

    private static int expReward = 1500;
    private static int goldReward = 80;
    private static int battleRadius = 1;
    private static int supportRadius = 1;
    private static int speed = 1; // Ticks per tile
    private static AttackStrategy strategy = new ElanAttack();
    private static int damage = 30;
    private static int health = 100;

    /**
     * Constructor for slug
     * @param position - current position on map
     */
    public Elan(PathPosition position) {
        super(position, health, damage, speed);
    } 

    @Override
    public void move() {
        for(int i = 0; i < speed; i++)
            super.move();
    }
    
    /**
     * Getter for EXP reward when killed
     * @return EXP reward
     */
    public int getExpReward() {
        return expReward;
    }
    
    @Override
    public Boolean isBoss() {
        return true;
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

    /**
     * Getter for attack strategy
     */
    @Override
    public AttackStrategy getAttackStrategy() {
        return strategy;
    }
}
