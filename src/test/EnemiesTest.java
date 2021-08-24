package test;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import unsw.loopmania.model.PathPosition;
import unsw.loopmania.model.Enemies.*;

import java.util.ArrayList;
import java.util.List;

public class EnemiesTest { 

    @Test
    public void SlugTest() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        orderedPath.add(new Pair<>(1,2));
        orderedPath.add(new Pair<>(1,3));
        PathPosition pathPosition = new PathPosition(1, orderedPath);
        
        Slug slug = new Slug(pathPosition);
        Pair<Integer, Integer> positionBefore = new Pair<>(slug.getX(), slug.getY());

        slug.move();
        Pair<Integer, Integer> positionAfter = new Pair<>(slug.getX(), slug.getY());

        assert(!positionBefore.equals(positionAfter));
    }

    @Test
    void ZombieTest() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        orderedPath.add(new Pair<>(1,2));
        orderedPath.add(new Pair<>(1,3));
        PathPosition pathPosition = new PathPosition(1, orderedPath);

        Zombie zombie = new Zombie(pathPosition);
        Pair<Integer, Integer> positionBefore = new Pair<>(zombie.getX(), zombie.getY());

        zombie.move();
        Pair<Integer, Integer> positionAfter = new Pair<>(zombie.getX(), zombie.getY());

        assert(!positionBefore.equals(positionAfter));
    }

    @Test
    void VampireTest() {

        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        orderedPath.add(new Pair<>(1,2));
        orderedPath.add(new Pair<>(1,3));
        PathPosition pathPosition = new PathPosition(1, orderedPath);

        Vampire vampire = new Vampire(pathPosition);
        Pair<Integer, Integer> positionBefore = new Pair<>(vampire.getX(), vampire.getY());

        vampire.move();
        Pair<Integer, Integer> positionAfter = new Pair<>(vampire.getX(), vampire.getY());

        assert(!positionBefore.equals(positionAfter));

    }

    @Test
    void ElanTest() {

        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        orderedPath.add(new Pair<>(1,2));
        orderedPath.add(new Pair<>(1,3));
        PathPosition pathPosition = new PathPosition(1, orderedPath);

        Elan elan = new Elan(pathPosition);
        Pair<Integer, Integer> positionBefore = new Pair<>(elan.getX(), elan.getY());

        elan.move();
        Pair<Integer, Integer> positionAfter = new Pair<>(elan.getX(), elan.getY());

        assert(!positionBefore.equals(positionAfter));

    }

    @Test
    void DoggieTest() {

        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1,1));
        orderedPath.add(new Pair<>(1,2));
        orderedPath.add(new Pair<>(1,3));
        PathPosition pathPosition = new PathPosition(1, orderedPath);

        Doggie doggie = new Doggie(pathPosition);
        Pair<Integer, Integer> positionBefore = new Pair<>(doggie.getX(), doggie.getY());

        doggie.move();
        Pair<Integer, Integer> positionAfter = new Pair<>(doggie.getX(), doggie.getY());

        assert(!positionBefore.equals(positionAfter));

    }
}
