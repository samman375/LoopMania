package unsw.loopmania.model.Cards.PositionStrategy;

import java.util.List;

import org.javatuples.Pair;

public class AdjacentPathBehaviour implements PositionStrategy {

    @Override
    public boolean validPosition(int buildingNodeX, int buildingNodeY, List<Pair<Integer, Integer>> orderedPath) {
        
        Integer x = Integer.valueOf(buildingNodeX);
        Integer y = Integer.valueOf(buildingNodeY);
        Pair<Integer, Integer> tile = new Pair<>(x, y);

        if (!orderedPath.contains(tile)) {
            for ( Integer i = tile.getValue0() - 1; i <= tile.getValue0() + 1; i++) {
                for ( Integer j = tile.getValue1() - 1; j <= tile.getValue1() + 1; j++) {
                    Pair<Integer, Integer> adjacentTile = new Pair<>(i, j);
                    if (orderedPath.contains(adjacentTile))
                        return true;
                }
            }
        }
        return false;

    }

}