package unsw.loopmania.model.Enemies;

import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.AttackStrategy.AttackStrategy;
import unsw.loopmania.model.AttackStrategy.ZombieAttack;

public class Zombie extends Enemy {

    private static int expReward = 300;
    private static int goldReward = 20;
    private static int battleRadius = 2;
    private static int supportRadius = 2;
    private static int speed = 1; // Tiles per tick
    private static AttackStrategy strategy = new ZombieAttack();
    private static int critChance = 15;
    private static int damage = 14;
    private static int health = 20;

    /**
     * Constructor for Zombie
     * @param position - current position on map
     */
    public Zombie(PathPosition position) {
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

    @Override
    public int getCritChance() {
        return critChance;
    }

}
