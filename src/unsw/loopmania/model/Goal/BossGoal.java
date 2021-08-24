package unsw.loopmania.model.Goal;

import unsw.loopmania.model.EnemyStatus;
import unsw.loopmania.model.LoopManiaWorld;

public class BossGoal extends Goal {

    /**
     * check whether the character killed all the bosses or not.
     * @param world the current Loop Mania World
     * @return true if the character killed all the bosses else false
     */
    @Override
    public boolean isGoalComplete(LoopManiaWorld world) {
        if (world.getElanStatus() == EnemyStatus.SLAIN_STATUS && world.getDoggieStatus() == EnemyStatus.SLAIN_STATUS) {
            return true;
        } else {
            return false;
        }
    }
}
