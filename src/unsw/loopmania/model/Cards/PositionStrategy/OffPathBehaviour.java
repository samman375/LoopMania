package unsw.loopmania.model.Cards.PositionStrategy;

import java.util.List;

import org.javatuples.Pair;

public class OffPathBehaviour implements PositionStrategy {

    @Override
    public boolean validPosition(int buildingNodeX, int buildingNodeY, List<Pair<Integer, Integer>> orderedPath) {
        
        Integer x = Integer.valueOf(buildingNodeX);
        Integer y = Integer.valueOf(buildingNodeY);
        Pair<Integer, Integer> tile = new Pair<Integer, Integer>(x, y);

        for (Pair<Integer, Integer> coordinate : orderedPath) {
            if (tile.equals(coordinate)) return false;
        }
        
        return true;
    }

}