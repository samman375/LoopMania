package unsw.loopmania.model.AttackStrategy;

import unsw.loopmania.model.AttackEffects;
import unsw.loopmania.model.Entity;
import unsw.loopmania.model.MovingEntity;

/**
* Implements sword attack for a character on an target
*/
public class SwordAttack extends AttackObserver implements AttackStrategy  {
    private int swordDamage = 10;
    
    /**
     * Execute sword attack on an enemy target
     * @param attacker - character
     * @param target - enemy target
     * @param scalarDef - target scalar defences
     * @param fixedDef - target fixed defences
     * @param campfire - is campfire in range?
     * @return apply special effects
     */
    @Override
    public Enum<AttackEffects> execute(Entity attacker, MovingEntity target, int scalarDef, int fixedDef, Boolean campfire, int critReduction) {
        int damage = attacker.getDamage() + swordDamage;
        if (campfire) {
            damage *= super.campfireBuff();
        }
        target.setHealth(target.getHealth() - damage);
        return AttackEffects.NO_EFFECT;
    }
}
