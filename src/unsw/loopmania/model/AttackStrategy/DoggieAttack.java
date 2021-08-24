package unsw.loopmania.model.AttackStrategy;

import java.util.Random;

import unsw.loopmania.model.AttackEffects;
import unsw.loopmania.model.Character;
import unsw.loopmania.model.MovingEntity;
import unsw.loopmania.model.Entity;

/**
 * Implements slug attack on a target
 */
public class DoggieAttack implements AttackStrategy {
    private int stunChance = 30; //percentage chance to stun the character
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
        Random random = new Random(7);
        int randomInt = random.nextInt(99);
        if (randomInt < stunChance) {
            return AttackEffects.STUN_EFFECT;
        } else {
            return AttackEffects.NO_EFFECT;
        }
        
    }
}