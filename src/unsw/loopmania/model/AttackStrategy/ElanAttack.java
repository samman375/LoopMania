package unsw.loopmania.model.AttackStrategy;

import unsw.loopmania.model.AttackEffects;
import unsw.loopmania.model.Character;
import unsw.loopmania.model.MovingEntity;
import unsw.loopmania.model.Entity;

/**
 * Implements slug attack on a target
 */
public class ElanAttack implements AttackStrategy {
    /**
     * Execute slug attack on a target.
     * Applies defences if target is a Character.
     * @param attacker - character
     * @param target - enemy target
     * @param scalarDef - target scalar defences
     * @param fixedDef - target fixed defences
     * @param campfire - is campfire in range?
     * @return apply special effects
     */
    @Override
    public Enum<AttackEffects> execute(
        Entity attacker,
        MovingEntity target,
        int scalarDef,
        int fixedDef,
        Boolean campfire, 
        int critReduction
    ) {
        double damage = attacker.getDamage();
        if (target.getClass().equals(Character.class)) {
            double scalarDecimal = 100 - scalarDef;
            scalarDecimal /= 100;
            damage *= scalarDecimal;
            damage -= fixedDef;
        }
        if (damage > 0) {
            target.setHealth((int)(target.getHealth() - damage));
        }
        return AttackEffects.NO_EFFECT;
    }
}