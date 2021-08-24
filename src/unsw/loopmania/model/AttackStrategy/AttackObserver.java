package unsw.loopmania.model.AttackStrategy;

public abstract class AttackObserver {
    private static int getDamageMultiplier = 2;

    /**
     * Getter for campfire damage multiplier
     * @return damage multiplier
     */
    public int campfireBuff() {
        return getDamageMultiplier;
    }
}
