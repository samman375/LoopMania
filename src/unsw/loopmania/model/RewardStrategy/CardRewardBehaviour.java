package unsw.loopmania.model.RewardStrategy;

import java.util.Random;

/**
 * the items that can be obtained as reward If more item types are added, add an
 * enum value here.
 */
enum CARD {
    BarracksCard, CampfireCard, TowerCard, TrapCard, VampireCastleCard, VillageCard, ZombiePitCard, GlacierCard, CloakingTowerCard;

    /**
     * Pick a random value of the CARD enum.
     * @return a random CARD.
     */
    public static CARD getRandomCard() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}

public class CardRewardBehaviour implements RewardStrategy {
    /**
     * Add a random number of random items to item rewards list
     */
    @Override
    public String randomReward() {
        return CARD.getRandomCard().name();
    }

}