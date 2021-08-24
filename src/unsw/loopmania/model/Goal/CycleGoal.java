package unsw.loopmania.model.Goal;

import unsw.loopmania.model.LoopManiaWorld;

public class CycleGoal extends Goal {

    private int cycleGoal;

    public CycleGoal(int cycleGoal) {
        this.cycleGoal = cycleGoal;
    }


    /**
     * check if the cycle goal is satisfied or not
     * @param world the current Loop Mania World
     * @return true if the player reached the cycle goal else false
     */
    @Override
    public boolean isGoalComplete(LoopManiaWorld world) {
        return world.getCycles() > cycleGoal;
    }
}
