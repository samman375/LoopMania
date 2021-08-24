package unsw.loopmania.model.Cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.Cards.PositionStrategy.*;

public class TowerCard extends Card {

    private PositionStrategy positionStrategy = new AdjacentPathBehaviour();

    public TowerCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public PositionStrategy getPositionStrategy() {
        return this.positionStrategy;
    }
    
}
