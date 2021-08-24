package unsw.loopmania.model.Cards.PositionStrategy;

import java.util.List;

import org.javatuples.Pair;

public interface PositionStrategy {

    public boolean validPosition(int buildingNodeX, int buildingNodeY, List<Pair<Integer, Integer>> orderedPath);

}