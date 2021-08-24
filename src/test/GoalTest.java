package test;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import unsw.loopmania.model.Character;
import unsw.loopmania.model.EnemyStatus;
import unsw.loopmania.model.Goal.*;
import unsw.loopmania.model.LoopManiaWorld;
import unsw.loopmania.model.PathPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoalTest {

    private LoopManiaWorld getWorld() {
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(0,0));
        orderedPath.add(new Pair<>(1,1));
        orderedPath.add(new Pair<>(1,2));
        orderedPath.add(new Pair<>(1,3));

        PathPosition position = new PathPosition(0, orderedPath);

        LoopManiaWorld world = new LoopManiaWorld(5, 5, orderedPath, new ArrayList<String>(), new Random(1));

        Character character = new Character(position);
        world.setCharacter(character);

        return world;
    }

    @Test
    public void cycleGoalTest() {

        LoopManiaWorld world = getWorld();

        CycleGoal cycleGoal = new CycleGoal(2);
        world.setGoals(cycleGoal);
        assert(!world.isGoalCompleted());

        for (int cycle = 0; cycle < 3; cycle++) {
            world.incrementCycles();
        }

        assert(world.isGoalCompleted());
    }

    @Test
    public void experienceGoalTest() {
        LoopManiaWorld world = getWorld();

        ExperienceGoal experienceGoal = new ExperienceGoal(1000);
        world.setGoals(experienceGoal);
        assert(!world.isGoalCompleted());

        world.setExperience(1000);
        assert(world.isGoalCompleted());
    }

    @Test
    public void goldGoalTest() {
        LoopManiaWorld world = getWorld();

        GoldGoal goldGoal = new GoldGoal(10000);
        world.setGoals(goldGoal);
        assert(!world.isGoalCompleted());

        world.setGold(10000);
        assert(world.isGoalCompleted());
    }

    @Test
    public void bossGoalTest() {
        LoopManiaWorld world = getWorld();

        BossGoal bossGoal = new BossGoal();
        world.setGoals(bossGoal);

        // should be false since no bosses been spawned yet
        assert(!world.isGoalCompleted());

        for (int cycle = 0; cycle < 17; cycle++) {
            world.incrementCycles();
        }

        world.spawnDoggie();
        // test when Doggie is still alive
        assert(!world.isGoalCompleted());

        world.setDoggieStatus(EnemyStatus.SLAIN_STATUS);

        for (int cycle = 0; cycle < 13; cycle++) {
            world.incrementCycles();
        }
        world.setExperience(10000);
        world.spawnElan();


        // test when Elan is alive
        assert(!world.isGoalCompleted());

        world.setElanStatus(EnemyStatus.SLAIN_STATUS);

        // test when the all bosses have been spawned and is killed
        assert(world.isGoalCompleted());
    }


    @Test
    public void andGoalTest() {
        LoopManiaWorld world = getWorld();

        ExperienceGoal experienceGoal = new ExperienceGoal(10);
        GoldGoal goldGoal = new GoldGoal(10);
        AndGoal andGoal = new AndGoal(experienceGoal, goldGoal);
        world.setGoals(andGoal);
        assert(!world.isGoalCompleted());

        world.setExperience(10);
        world.setGold(10);
        assert(world.isGoalCompleted());
    }

    @Test
    public void orGoalTest() {
        LoopManiaWorld world = getWorld();

        ExperienceGoal experienceGoal = new ExperienceGoal(10);
        GoldGoal goldGoal = new GoldGoal(10);
        OrGoal orGoal = new OrGoal(experienceGoal, goldGoal);
        world.setGoals(orGoal);
        assert(!world.isGoalCompleted());

        world.setExperience(10);
        assert(world.isGoalCompleted());
    }
}
