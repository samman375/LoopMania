package unsw.loopmania.model.AttackStrategy;

import unsw.loopmania.model.AttackEffects;
import unsw.loopmania.model.Entity;
import unsw.loopmania.model.MovingEntity;
import unsw.loopmania.model.Enemies.Vampire;

/**
* Implements stake attack for a character on an target
*/
public class StakeAttack extends AttackObserver implements AttackStrategy  {
    private int stakeDamage = 5;
    private int critDamage = 25;
    
    /**
     * Execute stake attack on an enemy target
     * @param attacker - character
     * @param target - enemy target
     * @param scalarDef - target scalar defences
     * @param fixedDef - target fixed defences
     * @param campfire - is campfire in range?
     * @return apply special effects
     */
    @Override
    public Enum<AttackEffects> execute(Entity attacker, MovingEntity target, int scalarDef, int fixedDef, Boolean campfire, int critReductions) {
        int damage = attacker.getDamage();
        if (target.getClass().equals(Vampire.class)) {
            damage += critDamage;
        } else {
            damage += stakeDamage;
        }
        if (campfire) {
            damage *= super.campfireBuff();
        }
        target.setHealth(target.getHealth() - damage);
        return AttackEffects.NO_EFFECT;
    }
}
