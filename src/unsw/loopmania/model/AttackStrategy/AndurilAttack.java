package unsw.loopmania.model.AttackStrategy;

import unsw.loopmania.model.AttackEffects;
import unsw.loopmania.model.Entity;
import unsw.loopmania.model.MovingEntity;

public class AndurilAttack extends AttackObserver implements AttackStrategy {

    private int andurilDamage = 10;
    private int critDamage = 30;

    @Override
    public Enum<AttackEffects> execute(Entity attacker, MovingEntity target, int scalarDef, int fixedDef, Boolean campfire,
            int critReduction) {
        int damage = attacker.getDamage();
        if (target.isBoss()) {
            damage += critDamage;
        } else {
            damage += andurilDamage;
        }
        if (campfire) {
            damage *= super.campfireBuff();
        }
        target.setHealth(target.getHealth() - damage);
        return AttackEffects.NO_EFFECT;
    }
    
}
