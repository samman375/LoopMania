package unsw.loopmania.model.AttackStrategy;

import unsw.loopmania.model.Entity;
import unsw.loopmania.model.MovingEntity;
import unsw.loopmania.model.AttackEffects;
import unsw.loopmania.model.Character;

/**
* Implements basic attack for a character/alliedSoldier/tower on a target
*/
public class BasicAttack extends AttackObserver implements AttackStrategy {
    /**
     * Execute basic attack on an enemy target
     * @param attacker
     * @param target - enemy target
     * @param scalarDef - target scalar defences
     * @param fixedDef - target fixed defences
     * @param campfire - is campfire in range?
     * @return apply special effects
     */
    @Override
    public Enum<AttackEffects> execute(Entity attacker, MovingEntity target, int scalarDef, int fixedDef, Boolean campfire, int critReduction) {
        int damage = attacker.getDamage();
        if (campfire && attacker.getClass().equals(Character.class)) {
            damage *= super.campfireBuff();
        }
        target.setHealth(target.getHealth() - damage);
        return AttackEffects.NO_EFFECT;
    }

}
