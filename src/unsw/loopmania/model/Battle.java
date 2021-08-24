package unsw.loopmania.model;

import org.javatuples.Pair;

// import jdk.javadoc.internal.tool.resources.javadoc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import unsw.loopmania.model.AttackStrategy.*;
import unsw.loopmania.model.Buildings.*;
import unsw.loopmania.model.Enemies.*;
import unsw.loopmania.model.Items.Item;
import unsw.loopmania.model.Items.RareItems.Anduril;
import unsw.loopmania.model.Items.RareItems.TreeStump;
import unsw.loopmania.model.RewardStrategy.*;


public class Battle {

    private Character character;
    private List<Building> towers;
    private List<AlliedSoldier> allies;
    private List<Building> campfires;
    private List<Enemy> enemies;
    private List<Enemy> killedEnemies = new ArrayList<>();
    private Item weapon = null;
    private Item armour = null;
    private Item shield = null;
    private Item helmet = null;
    private Item rareItem = null;
    private RewardStrategy itemRewardStrategy = new ItemRewardBehaviour();
    private RewardStrategy cardRewardStrategy = new CardRewardBehaviour();
    private String gameMode;

    /**
     * Constructor for a battle
     * @param character
     * @param towers - list of towers to join battle
     * @param allies - list of allied soldiers to join battle
     * @param enemies - list of enemies to join battle
     * @param campfires - list of campfires in battle range
     */
    public Battle (
        Character character,
        List<Building> towers,
        List<AlliedSoldier> allies,
        List<Enemy> enemies,
        List<Building> campfires,
        String gameMode
    ) {
        this.character = character;
        this.towers = towers;
        this.allies = allies;
        this.enemies = enemies;
        this.campfires = campfires;
        this.gameMode = gameMode;
    }

    /**
     * Given an enemy adds to the list of enemies for the battle
     * @param enemy - enemy to add
     */
    private void addEnemyToBattle(Enemy enemy) {
        if (enemy.getHealth() > 0) {
            this.enemies.add(enemy);
        }
    }

    /**
     * Setter for equipped weapon
     * @param weapon
     */
    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    /**
     * Setter for equipped armour
     * @param armour
     */
    public void setArmour(Item armour) {
        this.armour = armour;
    }

    /**
     * Setter for equipped Shield
     * @param shield
     */
    public void setShield(Item shield) {
        this.shield = shield;
    }

    /**
     * Setter for equipped helmet
     * @param helmet
     */
    public void setHelmet(Item helmet) {
        this.helmet = helmet;
    }

    /**
     * Setter for equipped Rare Item
     * @param rareItem
     */
    public void setRareItem(Item rareItem) {
        this.rareItem = rareItem;
    }

    /**
     * Removes given enemy from list of alive enemies.
     * Adds enemy to list of killed enemies
     * @param enemy - enemy to kill
     */
    private void killEnemy(Enemy enemy) {
        this.killedEnemies.add(enemy);
    }

    /**
     * Getter for list of killed enemies
     */
    public List<Enemy> getKilledEnemies() {
        return this.killedEnemies;
    }

    /**
     * Getter for list of killed allies
     * @return list of dead allies
     */
    public List<AlliedSoldier> getKilledAllies() {
        List<AlliedSoldier> deadAllies = new ArrayList<AlliedSoldier>();
        for (AlliedSoldier ally : allies) {
            if (ally.isDead()) {
                deadAllies.add(ally);
            }
        }
        return deadAllies;
    }

    /**
     * Sorts list of enemies by hp
     */
    private void sortEnemiesByCurrentHp() {
        enemies.sort(Comparator.comparing(Enemy::getHealth));
    }

    /**
     * Sorts list of allies by hp
     */
    private void sortAlliesByCurrentHp() {
        allies.sort(Comparator.comparing(AlliedSoldier::getHealth));
    }

    /**
     * Executes fight mechanic
     * Check assumptions for battle order
     */
    public void fight() {
        // Order: Character -> Tower1 → Allied Soldier 1 -> Enemy 1 → character → Tower2 → Allied Soldier 2 → Enemy2
        // Characters and enemies will attack the entity with the lowest current health every turn
        // Enemies will attack allied soldiers first
        int scalarDef = getScalarDef();
        int flatDef = getFlatDef();
        int towerTurn = 0;
        int allyTurn = 0;
        int enemyTurn = 0;
        while (!areEnemiesDead() && !isLost()) {
            sortEnemiesByCurrentHp();
            sortAlliesByCurrentHp();
            // skips turn if enemy is stunned by Elan
            if (character.getStunnedCycle() == 0) {
                attackLiveEnemy(character);
            } else {
                character.reduceStunnedCycle();
            }
            
            if (areEnemiesDead()) {
                break;
            }
            if (towers.size() > 0) {
                attackLiveEnemy(towers.get(towerTurn % towers.size()));
                towerTurn += 1;
                towerTurn %= towers.size();
                if (areEnemiesDead()) {
                    break;
                }
            }
            if (alliesAlive()) {
                // Check allies & alive
                attackLiveEnemy(allies.get(allyTurn));
                allyTurn += 1;
                allyTurn %= allies.size();
                while (allies.get(allyTurn).isDead()) {
                    // Skip if ally is dead
                    allyTurn += 1;
                    allyTurn %= allies.size();
                }
                if (areEnemiesDead()) {
                    break;
                }
            }
            enemyAttack(enemies.get(enemyTurn), scalarDef, flatDef);

            // Elan heals enemies on his turn
            if (enemies.get(enemyTurn) instanceof Elan) {
                //heal all enemies that aren't dead
                for (Enemy e : enemies) {
                    if (!e.isDead()) {
                        // heal enemy incl himself by 5 hitpoints
                        e.gainHealth(5);
                    }
                }
            }
            enemyTurn += 1;
            enemyTurn %= enemies.size();
            while (enemies.get(enemyTurn).isDead()) {
                // Skip if enemy is dead
                enemyTurn += 1;
                enemyTurn %= enemies.size();
            }
            // Reduce life cycle of tranced enemies + revert enemies at end of their life cycle
            reduceLifeCycleTrancedZombies();
        }
        // Kill remaining tranced enemies
        for (AlliedSoldier ally : allies) {
            if (ally.getTrancedLifeCycle() != -1) {
                ally.setHealth(0);
            }
        }
        // Kill all dead enemies
        for (Enemy enemy : enemies) {
            if (enemy.isDead()) {
                killEnemy(enemy);
            }
        }
    }

    /**
     * Decreases life cycle of tranced zombies by 1. Reverts zombies if end of life cycle.
     */
    private void reduceLifeCycleTrancedZombies() {
        for (AlliedSoldier ally : allies) {
            if (ally.getTrancedLifeCycle() > 0) {
                ally.reduceTrancedLifeCycle();
            }
            if (ally.getTrancedLifeCycle() == 0) {
                revertTrancedEnemy(ally);
            }
        }
    }

    /**
     * Entrances lowest hp zombie -> allied soldier.
     * Lowest hp zombie would be tranced due to battle order.
     * @param enemy
     * @param index - index of original zombie in liveEnemies
     */
    private void entranceEnemy(Enemy enemy, int index) {
        if (!enemy.isDead()) {
            List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
            dummyPath.add(new Pair<>(0,0));
            PathPosition dummyPosition = new PathPosition(0, dummyPath);
            AlliedSoldier trancedEnemy = new AlliedSoldier(dummyPosition);
            // Set ally hp and damage to zombie's remaining hp
            trancedEnemy.setHealth(enemy.getHealth());
            trancedEnemy.setDamage(enemy.getDamage());
            trancedEnemy.setTrancedLifeCycle();
            trancedEnemy.setTrancedEnemyIndex(index);
            allies.add(trancedEnemy);
            // Temporarily kill original enemy
            enemy.setHealth(0);
        }
    }

    /**
     * Transfers health of tranced enemy back to original enemy. Kills tranced enemy.
     * @param trancedEnemy
     */
    private void revertTrancedEnemy(AlliedSoldier trancedEnemy) {
        Enemy originalEnemy = enemies.get(trancedEnemy.getTrancedEnemyIndex());
        originalEnemy.setHealth(trancedEnemy.getHealth());
        trancedEnemy.setHealth(0);
    }

    /**
     * Turns an ally into a zombie permanently
     * @param ally
     */
    private void infectAlly(AlliedSoldier ally) {
        if (!ally.isDead()) {
            List<Pair<Integer, Integer>> dummyPath = new ArrayList<>();
            dummyPath.add(new Pair<>(0,0));
            PathPosition dummyPosition = new PathPosition(0, dummyPath);
            Zombie infectedAlly = new Zombie(dummyPosition);
            // Set zombie hp to ally's remaining hp
            infectedAlly.setHealth(ally.getHealth());
            addEnemyToBattle(infectedAlly);
            // Kill original ally
            ally.setHealth(0);
        }
    }

    /**
     * Stuns character, preventing them from attacking for default 1 turn(s). 
     * @param character - character instance to stun
     */
    private void stunCharacter(Character character) {
        character.setStunnedCycles();
    }

    /**
     * Checks if any allies are still alive
     * @return
     */
    private Boolean alliesAlive() {
        if (allies.size() == 0) {
            return false;
        } else {
            for (AlliedSoldier ally : allies) {
                if (!ally.isDead()) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Attack first live enemy
     * @param attacker
     * @return staff trance?
     */
    private void attackLiveEnemy(Entity attacker) {
        AttackStrategy attack = attacker.getAttackStrategy();
        if (attacker.getClass().equals(Character.class)) {
            attack = getItemAttackStrategy();
        }
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (!enemy.isDead()) {
                Enum<AttackEffects> attackEffect = attack.execute(character, enemy, 0, 0, campfires.size() > 0, 0);
                if (attackEffect == AttackEffects.TRANCE_EFFECT) {
                    entranceEnemy(enemy, i);
                }
                return;
            }
        }
    }

    /**
     * For a given enemy attacks lowest ally else character
     * @param attacker - enemy
     * @param scalarDef - character scalar damage reduction
     * @param flatDef - character flat damage reduction
     * @return zombie crit?
     */
    private void enemyAttack(Entity attacker, int scalarDef, int flatDef) {
        AttackStrategy attack = attacker.getAttackStrategy();
        // Prioritise lowest hp alive ally
        for (int i = 0; i < allies.size(); i++) {
            AlliedSoldier ally = allies.get(i);
            if (!ally.isDead()) {
                Enum<AttackEffects> attackEffect = attack.execute(attacker, ally, 0, 0, campfires.size() > 0, 0);
                if (attackEffect == AttackEffects.INFECT_EFFECT) {
                    infectAlly(ally);
                }
                return;
            }
        }
        // Otherwise attack character

        // Set damage reduction if treeStump equipped
        if (rareItem != null && (rareItem.getClass().equals(TreeStump.class) || gameMode.equals("Confusing"))) {
            if (attacker.isBoss()) {
                flatDef += rareItem.getBossFlatDamageReduction();
            } else {
                flatDef += rareItem.getFlatDamageReduction();
            }
        }
        Enum<AttackEffects> attackEffect = attack.execute(attacker, character, scalarDef, flatDef, campfires.size() > 0, getCritReduction());
        if (attackEffect == AttackEffects.STUN_EFFECT) {
            stunCharacter(character);
        }
    }

    /**
     * Gets attack strategy for character
     * @return attack strategy instance
     */
    private AttackStrategy getItemAttackStrategy() {
        if (rareItem != null && (rareItem.getClass().equals(Anduril.class) || gameMode.equals("Confusing"))) {
            return rareItem.getAttackStrategy();
        }
        if (weapon == null) {
            return new BasicAttack();
        } else {
            return weapon.getAttackStrategy();
        }
    }

    /**
     * Gets scalar damage reduction for character
     * @return percentage of damage reduction
     */
    private int getScalarDef() {
        int scalarDef = 0;
        if (armour != null) {
            scalarDef += armour.getScalarDamageReduction();
        }
        if (helmet != null) {
            scalarDef += helmet.getScalarDamageReduction();
        }
        return scalarDef;
    }

    /**
     * Gets flat damage reduction for character
     * @return damage reduction
     */
    private int getFlatDef() {
        int flatDef = 0;
        if (shield != null) {
            flatDef += shield.getFlatDamageReduction();
        }
        return flatDef;
    }

    /**
     * Gets crit chance reduction for character
     * @return crit chance reduction
     */
    private int getCritReduction() {
        int critReduction = 0;
        if (shield != null) {
            critReduction += shield.getVampireCritChanceReduction();
        }
        return critReduction;
    }

    /**
     * Checks if all enemies are dead
     */
    private boolean areEnemiesDead() {
        if (enemies.size() == 0) {
            return true;
        }
        for (Enemy enemy : enemies) {
            if (!enemy.isDead()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if battle was lost
     * @return loss status
     */
    public Boolean isLost() {
        return character.isDead();
    }

    /**
     * Gets total EXP reward for the battle
     * @return total experience gained
     */
    public int getBattleExp() {
        int xp = 0;
        for (Enemy enemy : killedEnemies) {
            xp += enemy.getExpReward();
        }
        return xp;
    }

    /**
     * Gets total gold reward for the battle
     * @return total gold gained
     */
    public int getBattleGold() {
        int gold = 0;
        for (Enemy enemy : killedEnemies) {
            gold += enemy.getGoldReward();
        }
        return gold;
    }

    public RewardStrategy getCardRewardStrategy() {
        return this.cardRewardStrategy;
    }

    /**
     * Gets card rewards for battle
     * @return list of cards gained
     */
    public List<String> getBattleCards() {
        List<String> cards = new ArrayList<String>();
        for (int i = 0; i < killedEnemies.size(); i++) {
            cards.add(getCardRewardStrategy().randomReward());
        }
        return cards;
    }

    public RewardStrategy getItemRewardStrategy() {
        return this.itemRewardStrategy;
    }

    /**
     * Gets item rewards for battle. 5% chance of extra TheOneRing granted.
     * @return list of items gained
     */
    public List<String> getBattleItems() {
        List<String> items = new ArrayList<String>();
        for (int i = 0; i < killedEnemies.size(); i++) {
            items.add(getItemRewardStrategy().randomReward());
        }
        Random random = new Random(12);
        if (random.nextInt(99) < 5) {
            int itemInt = random.nextInt(2);
            if (itemInt == 0) {
                items.add("TheOneRing");
            } else if (itemInt == 1) {
                items.add("Anduril");
            } else if (itemInt == 2) {
                items.add("TreeStump");
            }
        }
        return items;
    }

}
