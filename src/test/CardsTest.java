package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.model.Cards.*;
import unsw.loopmania.model.Cards.PositionStrategy.*;

public class CardsTest {
    
    /**
     * Test: Player can only drop barracks card on path tile
     */
    @Test
    public void validPositionOnPathTest() { 
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        Card barracks = new BarracksCard(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        assert(barracks.getPositionStrategy() instanceof OnPathBehaviour);
        assertEquals(barracks.validPosition(0, 0, orderedPath), false); 
        assertEquals(barracks.validPosition(1, 1, orderedPath), true); 
    }

    /**
     * Test: Player can only drop campfire card on non-path tile
     */
    @Test
    public void validPositionOffPathTest() { 
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        Card campfire = new CampfireCard(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        assert(campfire.getPositionStrategy() instanceof OffPathBehaviour);
        assertEquals(campfire.validPosition(1, 1, orderedPath), false); 
        assertEquals(campfire.validPosition(5, 5, orderedPath), true); 
    }

    /**
     * Test: Player can only drop vampire card on non-path tile adjacent to path
     */
    @Test
    public void validPositionAdjacentPathTest() { 
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        Card vampireCastle = new VampireCastleCard(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        assert(vampireCastle.getPositionStrategy() instanceof AdjacentPathBehaviour);
        assertEquals(vampireCastle.validPosition(1, 1, orderedPath), false);
        assertEquals(vampireCastle.validPosition(5, 5, orderedPath), false);
        assertEquals(vampireCastle.validPosition(0, 0, orderedPath), true); 
    }

    /**
     * Test: Player can only drop village card on path tile
     */
    @Test
    public void villageCardPlacementTest() { 
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        Card village = new VillageCard(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        assert(village.getPositionStrategy() instanceof OnPathBehaviour);
        assertEquals(village.validPosition(0, 0, orderedPath), false); 
        assertEquals(village.validPosition(1, 1, orderedPath), true); 
    }


    /**
     * Test: Player can only drop tower card on non-path tiles adjacent to path
     */
    @Test
    public void towerCardPlacementTest() { 
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        Card tower = new TowerCard(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        assert(tower.getPositionStrategy() instanceof AdjacentPathBehaviour);
        assertEquals(tower.validPosition(1, 1, orderedPath), false);
        assertEquals(tower.validPosition(5, 5, orderedPath), false);
        assertEquals(tower.validPosition(0, 0, orderedPath), true); 
    }

    
        /**
     * Test: Character obtains rewards after discarding cards
     */
    @Test
    public void setItemRewardTest() {
        Card barracks = new BarracksCard(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        barracks.setItemReward();
        assertNotNull(barracks.getItemRewardList());
        System.out.println(barracks.getItemRewardList());
    }
}
