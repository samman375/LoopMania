package unsw.loopmania.model.Cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.Cards.PositionStrategy.*;

/**
 * represents a vampire castle card in the backend game world
 */
public class VampireCastleCard extends Card {

    private PositionStrategy positionStrategy = new AdjacentPathBehaviour();

    // DONE = add more types of card
    public VampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    

    public PositionStrategy getPositionStrategy() {
        return this.positionStrategy;
    }

}
