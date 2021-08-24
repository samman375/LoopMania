package unsw.loopmania.model.Goal;

import unsw.loopmania.model.LoopManiaWorld;

public class GoldGoal extends Goal {

    private int goldGoal;

    public GoldGoal(int goldGoal) {
        this.goldGoal = goldGoal;
    }

    /**
     * check if the gold goal is satisfied or not
     * @param world the current Loop Mania World
     * @return true if the player reached the gold goal else false
     */
    @Override
    public boolean isGoalComplete(LoopManiaWorld world) {
        return world.getGold() >= goldGoal;
    }
}
