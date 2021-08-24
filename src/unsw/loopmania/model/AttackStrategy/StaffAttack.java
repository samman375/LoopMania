package unsw.loopmania.model.AttackStrategy;

import java.util.Random;

import unsw.loopmania.model.MovingEntity;
import unsw.loopmania.model.AttackEffects;
import unsw.loopmania.model.Entity;

/**
* Implements staff attack for a character on an target
*/
public class StaffAttack extends AttackObserver implements AttackStrategy  {
    private int staffDamage = 3;
    private int tranceChance = 40;
    
    /**
     * Execute staff attack on an enemy target
     * @param attacker - character
     * @param target - enemy target
     * @param scalarDef - target scalar defences
     * @param fixedDef - target fixed defences
     * @param campfire - is campfire in range?
     * @return apply special effects
     */
    @Override
    public Enum<AttackEffects> execute(Entity attacker, MovingEntity target, int scalarDef, int fixedDef, Boolean campfire, int critReduction) {
        int damage = attacker.getDamage() + staffDamage;
        if (campfire) {
            damage *= super.campfireBuff();
        }
        target.setHealth(target.getHealth() - damage);
        // Trance effect
        Random random = new Random(7);
        int randomInt = random.nextInt(99);
        if (randomInt < tranceChance) {
            return AttackEffects.TRANCE_EFFECT;
        } else {
            return AttackEffects.NO_EFFECT;
        }
        
    }
}
