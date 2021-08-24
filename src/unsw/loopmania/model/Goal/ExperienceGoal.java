package unsw.loopmania.model.Goal;

import unsw.loopmania.model.LoopManiaWorld;

public class ExperienceGoal extends Goal {

    private int expPointGoal;

    public ExperienceGoal(int expPointGoal) {
        this.expPointGoal = expPointGoal;
    }

    /**
     * check if the experience points goal is satisfied or not
     * @param world the current Loop Mania World
     * @return true if the player reached the experience point goal else false
     */
    @Override
    public boolean isGoalComplete(LoopManiaWorld world) {
        return world.getExperience() >= expPointGoal;
    }
}
