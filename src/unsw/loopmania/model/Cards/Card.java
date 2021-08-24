package unsw.loopmania.model.Cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.StaticEntity;
import unsw.loopmania.model.Cards.PositionStrategy.PositionStrategy;
import unsw.loopmania.model.RewardStrategy.ItemRewardBehaviour;
import unsw.loopmania.model.RewardStrategy.RewardStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

/**
 * a Card in the world which doesn't move
 */
public abstract class Card extends StaticEntity {

    private int goldReward = 20;
    private int expReward = 100;
    private List<String> itemReward = new ArrayList<>();
    private RewardStrategy rewardStrategy = new ItemRewardBehaviour();

    public Card(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public abstract PositionStrategy getPositionStrategy();

    public int getGoldReward() {
        return this.goldReward;
    }

    public int getExpReward() {
        return this.expReward;
    }

    public RewardStrategy getRewardStrategy() {
        return this.rewardStrategy;
    }

    public List<String> getItemRewardList() {
        return this.itemReward;
    }

    public String getRandomItemReward() {
        return getRewardStrategy().randomReward();
    }
    
    /**
     * Add a random number of random items to item rewards list
     */
    public void setItemReward() {
        int itemCount = new Random().nextInt(3) + 1;

        for (int i = 0; i < itemCount; i++) {
            getItemRewardList().add(getRandomItemReward());
        }
    }
    
    /**
     * Check if the card can be placed at the given coordinates to be converted
     * into its corresponding building
     * @param buildingNodeX x coordinate for potential building placement
     * @param buildingNodeY y coordinate for potential building placement
     * @param orderedPath list of path tiles
     * @return true if the card can be placed, otherwise false
     */
    public boolean validPosition(int buildingNodeX, int buildingNodeY, List<Pair<Integer, Integer>> orderedPath) {
        return getPositionStrategy().validPosition(buildingNodeX, buildingNodeY, orderedPath);
    }

}