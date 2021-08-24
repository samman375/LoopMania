package unsw.loopmania.model.Goal;

import unsw.loopmania.model.LoopManiaWorld;

public class OrGoal extends Goal {

    private Goal goalA;
    private Goal goalB;

    public OrGoal(Goal goalA, Goal goalB) {
        this.goalA = goalA;
        this.goalB = goalB;
    }

    /**
     * to check if any of the two goals is satisfied or not
     * @param world the current Loop Mania World
     * @return true if the player completed one of the two goals else false
     */
    @Override
    public boolean isGoalComplete(LoopManiaWorld world) {
        return goalA.isGoalComplete(world) || goalB.isGoalComplete(world);
    }
}
