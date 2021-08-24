package unsw.loopmania.model.Goal;

import unsw.loopmania.model.LoopManiaWorld;

public abstract class Goal {

    /**
     * check if a certain goal is satisfied or not
     * @param world the current Loop Mania World
     * @return true if it is satisfied else false
     */
    public abstract boolean isGoalComplete(LoopManiaWorld world);

}