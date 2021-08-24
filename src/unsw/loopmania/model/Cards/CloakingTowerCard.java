package unsw.loopmania.model.Cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.Cards.PositionStrategy.*;

/**
 * represents a vampire castle card in the backend game world
 */
public class CloakingTowerCard extends Card {

    private PositionStrategy positionStrategy = new OffPathBehaviour();

    // DONE = add more types of card
    public CloakingTowerCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    

    public PositionStrategy getPositionStrategy() {
        return this.positionStrategy;
    }

}
