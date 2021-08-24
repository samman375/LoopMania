package unsw.loopmania.model.Goal;

import unsw.loopmania.model.LoopManiaWorld;

public class AndGoal extends Goal {

    private Goal goalA;
    private Goal goalB;

    public AndGoal(Goal goalA, Goal goalB) {
        this.goalA = goalA;
        this.goalB = goalB;
    }

    /**
     * to check if two goals are satisfied or not
     * @param world the current Loop Mania World
     * @return true if the player completed both goals else false
     */
    @Override
    public boolean isGoalComplete(LoopManiaWorld world) {
        return goalA.isGoalComplete(world) && goalB.isGoalComplete(world);
    }
}
