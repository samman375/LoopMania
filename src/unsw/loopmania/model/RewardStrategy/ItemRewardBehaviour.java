package unsw.loopmania.model.RewardStrategy;

import java.util.Random;

/**
 * the items that can be obtained as reward If more item types are added, add an
 * enum value here.
 */
enum ITEM {
    Sword, Stake, Staff, Armour, Shield, Helmet, HealthPotion;

    /**
     * Pick a random value of the ITEM enum.
     * @return a random ITEM.
     */
    public static ITEM getRandomItem() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}


public class ItemRewardBehaviour implements RewardStrategy {

    /**
     * Add a random number of random items to item rewards list
     */
    @Override
    public String randomReward() {
        return ITEM.getRandomItem().name();
    }

}