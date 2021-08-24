package unsw.loopmania.model.AttackStrategy;

import unsw.loopmania.model.AttackEffects;
import unsw.loopmania.model.Entity;
import unsw.loopmania.model.MovingEntity;

public interface AttackStrategy {
    public Enum<AttackEffects> execute(
        Entity attacker,
        MovingEntity target,
        int scalarDef,
        int fixedDef,
        Boolean campfire,
        int critReduction
    );
}
