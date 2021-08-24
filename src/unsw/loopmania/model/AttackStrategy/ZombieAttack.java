package unsw.loopmania.model.AttackStrategy;

import java.util.Random;

import unsw.loopmania.model.AlliedSoldier;
import unsw.loopmania.model.AttackEffects;
import unsw.loopmania.model.Entity;
import unsw.loopmania.model.MovingEntity;
import unsw.loopmania.model.Character;

/**
 * Implements a zombie attack on a target
 */
public class ZombieAttack implements AttackStrategy {

    /**
     * Execute zombie attack on a target.
     * Applies defences if target is a Character.
     * @param attacker - character
     * @param target - enemy target
     * @param scalarDef - target scalar defences
     * @param fixedDef - target fixed defences
     * @param campfire - is campfire in range?
     * @return apply special effects
     */
    @Override
    public Enum<AttackEffects> execute(Entity attacker, MovingEntity target, int scalarDef, int fixedDef, Boolean campfire, int critReduction) {
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
        Random random = new Random(4);
        Boolean crit = random.nextInt(99) < attacker.getCritChance();
        if (crit && target.getClass().equals(AlliedSoldier.class)) {
            return AttackEffects.INFECT_EFFECT;
        } else {
            return AttackEffects.NO_EFFECT;
        }
    }
}
