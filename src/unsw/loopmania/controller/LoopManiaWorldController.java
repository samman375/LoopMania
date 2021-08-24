package unsw.loopmania.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.codefx.libfx.listener.handle.ListenerHandle;
import org.codefx.libfx.listener.handle.ListenerHandles;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import unsw.loopmania.model.Items.*;
import unsw.loopmania.model.Items.BasicItems.*;
import unsw.loopmania.model.Items.RareItems.*;
import unsw.loopmania.view.DragIcon;
import unsw.loopmania.view.MusicPlayer;
import unsw.loopmania.model.Entity;
import unsw.loopmania.model.LoopManiaWorld;
import unsw.loopmania.model.Buildings.*;
import unsw.loopmania.model.Cards.*;
import unsw.loopmania.model.Enemies.*;

import java.util.EnumMap;

import java.io.File;
import java.io.IOException;

/**
 * the draggable types.
 * If you add more draggable types, add an enum value here.
 * This is so we can see what type is being dragged.
 */
enum DRAGGABLE_TYPE{
    CARD,
    ITEM
}

/**
 * A JavaFX controller for the world.
 * 
 * All event handlers and the timeline in JavaFX run on the JavaFX application thread:
 *     https://examples.javacodegeeks.com/desktop-java/javafx/javafx-concurrency-example/
 *     Note in https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Application.html under heading "Threading", it specifies animation timelines are run in the application thread.
 * This means that the starter code does not need locks (mutexes) for resources shared between the timeline KeyFrame, and all of the  event handlers (including between different event handlers).
 * This will make the game easier for you to implement. However, if you add time-consuming processes to this, the game may lag or become choppy.
 * 
 * If you need to implement time-consuming processes, we recommend:
 *     using Task https://openjfx.io/javadoc/11/javafx.graphics/javafx/concurrent/Task.html by itself or within a Service https://openjfx.io/javadoc/11/javafx.graphics/javafx/concurrent/Service.html
 * 
 *     Tasks ensure that any changes to public properties, change notifications for errors or cancellation, event handlers, and states occur on the JavaFX Application thread,
 *         so is a better alternative to using a basic Java Thread: https://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
 *     The Service class is used for executing/reusing tasks. You can run tasks without Service, however, if you don't need to reuse it.
 *
 * If you implement time-consuming processes in a Task or thread, you may need to implement locks on resources shared with the application thread (i.e. Timeline KeyFrame and drag Event handlers).
 * You can check whether code is running on the JavaFX application thread by running the helper method printThreadingNotes in this class.
 * 
 * NOTE: http://tutorials.jenkov.com/javafx/concurrency.html and https://www.developer.com/design/multithreading-in-javafx/#:~:text=JavaFX%20has%20a%20unique%20set,in%20the%20JavaFX%20Application%20Thread.
 * 
 * If you need to delay some code but it is not long-running, consider using Platform.runLater https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Platform.html#runLater(java.lang.Runnable)
 *     This is run on the JavaFX application thread when it has enough time.
 */
public class LoopManiaWorldController {

    /**
     * squares gridpane includes path images, enemies, character, empty grass, buildings
     */
    @FXML
    private GridPane squares;

    /**
     * cards gridpane includes cards and the ground underneath the cards
     */
    @FXML
    private GridPane cards;

    /**
     * anchorPaneRoot is the "background". It is useful since anchorPaneRoot stretches over the entire game world,
     * so we can detect dragging of cards/items over this and accordingly update DragIcon coordinates
     */
    @FXML
    private AnchorPane anchorPaneRoot;

    /**
     * equippedItems gridpane is for equipped items (e.g. swords, shield, axe)
     */
    @FXML
    private GridPane equippedItems;

    @FXML
    private GridPane unequippedInventory;

    // all image views including tiles, character, enemies, cards... even though cards in separate gridpane...
    private List<ImageView> entityImages;

    /**
     * when we drag a card/item, the picture for whatever we're dragging is set here and we actually drag this node
     */
    private DragIcon draggedEntity;

    private boolean isPaused;
    private LoopManiaWorld world;

    /**
     * runs the periodic game logic - second-by-second moving of character through maze, as well as enemies, and running of battles
     */
    private Timeline timeline;

    // Card Images
    private Image barracksCardImage;
    private Image campfireCardImage;
    private Image herosCastleCardImage;
    private Image towerCardImage;
    private Image trapCardImage;
    private Image vampireCastleCardImage;
    private Image villageCardImage;
    private Image zombiePitCardImage;
    private Image glacierCardImage;
    private Image cloakingTowerCardImage;

    // Building Images
    private Image barracksBuildingImage;
    private Image campfireBuildingImage;
    private Image towerBuildingImage;
    private Image trapBuildingImage;
    private Image vampireCastleBuildingImage;
    private Image villageBuildingImage;
    private Image zombiePitBuildingImage;
    private Image glacierBuildingImage;
    private Image cloakingTowerBuildingImage;

    // Enemy Images
    private Image slugImage;
    private Image vampireImage;
    private Image zombieImage;
    private Image doggieImage;
    private Image elanImage;

    // Allied Solider Image
    private Image alliedSoldierImage;

    // Item Images
    private Image armourImage;
    private Image goldImage;
    private Image healthPotionImage;
    private Image helmetImage;
    private Image shieldImage;
    private Image staffImage;
    private Image stakeImage;
    private Image swordImage;
    private Image theOneRingImage;
    private Image andurilFlameOfTheWestImage;
    private Image treeStumpImage;
    private Image doggieCoinImage;

    private ImageView equippedRareItemImage;

    /**
     * the image currently being dragged, if there is one, otherwise null.
     * Holding the ImageView being dragged allows us to spawn it again in the drop location if appropriate.
     */
    private ImageView currentlyDraggedImage;

    /**
     * null if nothing being dragged, or the type of item being dragged
     */
    private DRAGGABLE_TYPE currentlyDraggedType;

    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dropped over its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneSetOnDragDropped;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged over the background
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> anchorPaneRootSetOnDragOver;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dropped in the background
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> anchorPaneRootSetOnDragDropped;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged into the boundaries of its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneNodeSetOnDragEntered;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged outside of the boundaries of its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneNodeSetOnDragExited;

    @FXML
    private Label worldExperience;

    @FXML
    private Label worldGold;

    @FXML 
    private Label worldHealth;

    @FXML
    private Label worldLevel;

    @FXML
    private Label numAlliedSoldiers;
    
    /**
     * @param world world object loaded from file
     * @param initialEntities the initial JavaFX nodes (ImageViews) which should be loaded into the GUI
     */
    public LoopManiaWorldController(LoopManiaWorld world, List<ImageView> initialEntities) {
            /* ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────┐ */
            /* │                                  Initializers for LoopManiaWorldController                                 │ */
            /* └────────────────────────────────────────────────────────────────────────────────────────────────────────────┘ */

        this.world = world;
        entityImages = new ArrayList<>(initialEntities);
        // Card Images
        barracksCardImage = new Image((new File("src/images/barracks_card.png")).toURI().toString());
        campfireCardImage = new Image((new File("src/images/campfire_card.png")).toURI().toString());
        towerCardImage = new Image((new File("src/images/tower_card.png")).toURI().toString());
        trapCardImage = new Image((new File("src/images/trap_card.png")).toURI().toString());
        vampireCastleCardImage = new Image((new File("src/images/vampire_castle_card.png")).toURI().toString());
        villageCardImage = new Image((new File("src/images/village_card.png")).toURI().toString());
        zombiePitCardImage = new Image((new File("src/images/zombie_pit_card.png")).toURI().toString());
        glacierCardImage = new Image((new File("src/images/glacier_card.png")).toURI().toString());
        cloakingTowerCardImage = new Image((new File("src/images/cloaking_tower_card.png")).toURI().toString());
        // Building Images
        barracksBuildingImage = new Image((new File("src/images/barracks.png")).toURI().toString());
        campfireBuildingImage = new Image((new File("src/images/campfire.png")).toURI().toString());
        towerBuildingImage = new Image((new File("src/images/tower.png")).toURI().toString());
        trapBuildingImage = new Image((new File("src/images/trap.png")).toURI().toString());
        vampireCastleBuildingImage = new Image((new File("src/images/vampire_castle_building_purple_background.png")).toURI().toString());
        villageBuildingImage = new Image((new File("src/images/village.png")).toURI().toString());
        zombiePitBuildingImage = new Image((new File("src/images/zombie_pit.png")).toURI().toString());
        glacierBuildingImage = new Image((new File("src/images/glacier.png")).toURI().toString());
        cloakingTowerBuildingImage = new Image((new File("src/images/cloaking_tower.png")).toURI().toString());
        // Enemy Images
        slugImage = new Image((new File("src/images/slug.png")).toURI().toString());
        vampireImage = new Image((new File("src/images/vampire.png")).toURI().toString());
        zombieImage = new Image((new File("src/images/zombie.png")).toURI().toString());
        doggieImage = new Image((new File("src/images/doggie.png")).toURI().toString()); 
        elanImage = new Image((new File("src/images/elanMuske.png")).toURI().toString()); 
        
        // Allied Soldier Images
        alliedSoldierImage = new Image((new File("src/images/deep_elf_master_archer.png")).toURI().toString());
        // Item Images
        armourImage = new Image((new File("src/images/armour.png")).toURI().toString());
        goldImage = new Image((new File("src/images/gold_pile.png")).toURI().toString());
        healthPotionImage = new Image((new File("src/images/brilliant_blue_new.png")).toURI().toString());
        helmetImage = new Image((new File("src/images/helmet.png")).toURI().toString());
        shieldImage = new Image((new File("src/images/shield.png")).toURI().toString());
        staffImage = new Image((new File("src/images/staff.png")).toURI().toString());
        stakeImage = new Image((new File("src/images/stake.png")).toURI().toString());
        swordImage = new Image((new File("src/images/basic_sword.png")).toURI().toString());
        theOneRingImage = new Image((new File("src/images/the_one_ring.png")).toURI().toString()); 
        andurilFlameOfTheWestImage = new Image((new File("src/images/anduril_flame_of_the_west.png")).toURI().toString()); 
        treeStumpImage = new Image((new File("src/images/treestump.png")).toURI().toString()); 
        doggieCoinImage = new Image((new File("src/images/doggiecoin.png")).toURI().toString()); 
        
        currentlyDraggedImage = null;
        currentlyDraggedType = null;

        // initialize them all...
        gridPaneSetOnDragDropped = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        anchorPaneRootSetOnDragOver = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        anchorPaneRootSetOnDragDropped = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        gridPaneNodeSetOnDragEntered = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        gridPaneNodeSetOnDragExited = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);

    }

    @FXML
    public void initialize() {
        
        Image pathTilesImage = new Image((new File("src/images/32x32GrassAndDirtPath.png")).toURI().toString());
        Image inventorySlotImage = new Image((new File("src/images/empty_slot.png")).toURI().toString());
        Rectangle2D imagePart = new Rectangle2D(0, 0, 32, 32);

        world.setExperienceLabel(worldExperience);
        world.updateExperience();
        world.setGoldLabel(worldGold);
        world.updateGold();
        world.setHealthLabel(worldHealth);
        world.updateHealth();
        world.setNumAlliedSoldiers(numAlliedSoldiers);
        world.updateNumAlliedSoldiers();
        world.setLevelLabel(worldLevel);
        world.updateLevel();

        // Add the ground first so it is below all other entities (inculding all the twists and turns)
        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                ImageView groundView = new ImageView(pathTilesImage);
                groundView.setViewport(imagePart);
                squares.add(groundView, x, y);
            }
        }

        // load entities loaded from the file in the loader into the squares gridpane
        for (ImageView entity : entityImages){
            squares.getChildren().add(entity);
        }

        // add the ground underneath the cards
        for (int x=0; x<world.getWidth(); x++){
            ImageView groundView = new ImageView(pathTilesImage);
            groundView.setViewport(imagePart);
            cards.add(groundView, x, 0);
        }

        // add the empty slot images for the unequipped inventory
        for (int x=0; x<LoopManiaWorld.unequippedInventoryWidth; x++){
            for (int y=0; y<LoopManiaWorld.unequippedInventoryHeight; y++){
                ImageView emptySlotView = new ImageView(inventorySlotImage);
                unequippedInventory.add(emptySlotView, x, y);
            }
        }

        // create the draggable icon
        draggedEntity = new DragIcon();
        draggedEntity.setVisible(false);
        draggedEntity.setOpacity(0.7);
        anchorPaneRoot.getChildren().add(draggedEntity);
    }

    /**
     * create and run the timer
     */
    public void startTimer() {
        System.out.println("starting timer");
        isPaused = false;
        // trigger adding code to process main game logic to queue. JavaFX will target framerate of 0.3 seconds
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), event -> {
            world.incrementCycles();
            System.out.println("Cycles: " + world.getCycles());
            world.healCharacterInVillage();
            world.spawnAllyFromBarracks();
            world.runTickMoves();
            if (!world.cloakCharacter()) {
                world.runBattles();
            }
            world.updateExperience();
            world.updateGold();
            world.updateHealth();
            world.updateLevel();
            world.updateNumAlliedSoldiers();
            if (world.getUsedEquippedRareItem()) {
                equippedRareItemImage.setImage(null);
                world.setUsedEquippedRareItem(false);
            }
            for (String card: world.getBattleRewardCards())
                loadCard(card);
            world.getBattleRewardCards().clear();
            for (String item: world.getBattleRewardItems())
                loadItem(item);
            world.getBattleRewardItems().clear();
            for (String item: world.getDiscardCardRewardItems())
                loadItem(item);
            world.getDiscardCardRewardItems().clear();
            for (Item item: world.getBoughtItems()) {
                onLoadItem(item);
            }
            world.getBoughtItems().clear();
            List<Enemy> newEnemies = new ArrayList<>();
            newEnemies.addAll(world.SpawnSlugs());
            newEnemies.addAll(world.spawnVampiresFromVampireCastles());
            newEnemies.addAll(world.spawnZombiesFromZombiePits());
            newEnemies.addAll(world.spawnElan());
            newEnemies.addAll(world.spawnDoggie());
            // ADD OTHER SPAWNING THINGS HERE

            for (Enemy newEnemy: newEnemies){
                // onLoad(newEnemy);
                onLoadEnemy(newEnemy);
            }
            // ALL ITEM SPAWNING MECHANICS
            List<Item> spawnedItems = world.possiblySpawnItem();
            for (Item item : spawnedItems) {
                if (item instanceof Gold) {
                    Gold gold = (Gold) item;
                    onLoadGold(gold);
                } else if (item instanceof HealthPotion) {
                    HealthPotion healthPotion = (HealthPotion) item;
                    onLoadHealthPotion(healthPotion);
                }
            }

            pickUpItems();
            despawnItems();

            if (world.getIsLost())
                try {
                    switchToGameOverScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            else if (world.isGoalCompleted()) {
                System.out.println("We WON");
                pause();
                try {
                    switchToWinScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (world.completedACycle() && world.getCycles() >= 0 && isTriangular()) {
                pause();
                try {
                    switchToHerosCastleMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            printThreadingNotes("HANDLED TIMER");
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public boolean isTriangular() {
        int counter = 0;
        int cycles = world.getCycles();
        for (int i = 1; counter <= cycles; i++) {
            counter = counter + i;
            if (counter == cycles) {
                return true;
            }
        }
        return false;
    }

    public void setGameMode(String gameMode) {
        world.setGameMode(gameMode);

        System.out.println(world.getGameMode());
    }

    /**
     * pause the execution of the game loop
     * the human player can still drag and drop items during the game pause
     */
    public void pause(){
        isPaused = true;
        System.out.println("pausing");
        timeline.stop();
    }

    public void terminate(){
        pause();
    }

    /* ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────┐ */
    /* │                                    Loading Methods for Controller                                          │ */
    /* └────────────────────────────────────────────────────────────────────────────────────────────────────────────┘ */

    /**
     * pair the entity an view so that the view copies the movements of the entity.
     * add view to list of entity images
     * @param entity backend entity to be paired with view
     * @param view frontend imageview to be paired with backend entity
     */
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entityImages.add(view);
    }

    /**
     * load a card from the world, and pair it with an image in the GUI
     */
    private void loadCard(String type) {
        Card card = world.loadCard(type);
        onLoadCard(card);
    }

    /**
     * load an item from the world, and pair it with an image in the GUI
     */
    private void loadItem(String type){
        // start by getting first available coordinates
        // addUnequippedItem("Sword");
        Item item = world.addUnequippedItem(type);
        onLoadItem(item);
    }

    /* ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────┐ */
    /* │                                      OnLoad Methods for Controller                                         │ */
    /* └────────────────────────────────────────────────────────────────────────────────────────────────────────────┘ */

    /**
     * load goldPile into the GUI
     * @param gold
     */
    private void onLoadGold(Item gold){
        ImageView view = new ImageView(goldImage);
        addEntity(gold, view);
        squares.getChildren().add(view);
    }

    /**
     * load healthPotion into the GUI
     * @param healthPotion
     */

    private void onLoadHealthPotion(Item healthPotion){
        ImageView view = new ImageView(healthPotionImage);
        addEntity(healthPotion, view);
        squares.getChildren().add(view);
    }

    /**
     * load a given card into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the cards GridPane.
     * @param card
     */
    private void onLoadCard(Card card) {
        ImageView view = onLoadCardView(card);

        // FROM https://stackoverflow.com/questions/41088095/javafx-drag-and-drop-to-gridpane
        // note target setOnDragOver and setOnDragEntered defined in initialize method
        addDragEventHandlers(view, DRAGGABLE_TYPE.CARD, cards, squares, card, null);

        addEntity(card, view);
        cards.getChildren().add(view);
    }


    /**
     * load an item into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the unequippedInventory GridPane.
     * @param item
     */
    private void onLoadItem(Item item) {
        ImageView view = onLoadItemView(item);
        addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedItems, null, item);
        addEntity(item, view);
        unequippedInventory.getChildren().add(view);
    }

    /**
     * load an enemy into the GUI
     * @param enemy
     */
    private void onLoadEnemy(Enemy enemy) {
        ImageView view = onLoadEnemyView(enemy);
        addEntity(enemy, view);
        squares.getChildren().add(view);
    }

    /**
     * load a building into the GUI
     * @param building
     */
    private void onloadBuilding(Building building) {
        ImageView view = onLoadBuildingView(building);
        addEntity(building, view);
        squares.getChildren().add(view);
    }

    /**
     * pick up items on the path and add it to the unequipped inventory
     */
    private void pickUpItems() {
        List<Item> items = world.pickupItems();

        if (!items.isEmpty()) {
            for (Item item : items) {
                onLoadItem(item);
            }
        }
    }

     /**
      * remove despawnned items from on the path
      */
     private void despawnItems() {
         List<Item> items = world.getDespawnItems();

         if (items != null) {
             for (Item item : items) {
                 item.destroy();
             }
         }

         world.restDespawnItems();
     }


    /* ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────┐ */
    /* │                                     Dragging Methods from StarterCode                                      │ */
    /* └────────────────────────────────────────────────────────────────────────────────────────────────────────────┘ */
    /**
     * add drag event handlers for dropping into gridpanes, dragging over the background, dropping over the background.
     * These are not attached to invidual items such as swords/cards.
     * @param draggableType the type being dragged - card or item
     * @param sourceGridPane the gridpane being dragged from
     * @param targetGridPane the gridpane the human player should be dragging to (but we of course cannot guarantee they will do so)
     */
    private void buildNonEntityDragHandlers(DRAGGABLE_TYPE draggableType, GridPane sourceGridPane, GridPane targetGridPane, Card card, Item item){
        // for example, in the specification, villages can only be dropped on path, whilst vampire castles cannot go on the path

        gridPaneSetOnDragDropped.put(draggableType, new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /*
                 *you might want to design the application so dropping at an invalid location drops at the most recent valid location hovered over,
                 * or simply allow the card/item to return to its slot (the latter is easier, as you won't have to store the last valid drop location!)
                 */
                if (currentlyDraggedType == draggableType){
                    // problem = event is drop completed is false when should be true...
                    // https://bugs.openjdk.java.net/browse/JDK-8117019
                    // putting drop completed at start not making complete on VLAB...

                    //Data dropped
                    //If there is an image on the dragboard, read it and use it
                    Dragboard db = event.getDragboard();
                    Node node = event.getPickResult().getIntersectedNode();
                    if(node != targetGridPane && db.hasImage()){

                        Integer cIndex = GridPane.getColumnIndex(node);
                        Integer rIndex = GridPane.getRowIndex(node);
                        int x = cIndex == null ? 0 : cIndex;
                        int y = rIndex == null ? 0 : rIndex;
                        //Places at 0,0 - will need to take coordinates once that is implemented
                        ImageView image = new ImageView(db.getImage());

                        int nodeX = GridPane.getColumnIndex(currentlyDraggedImage);
                        int nodeY = GridPane.getRowIndex(currentlyDraggedImage);
                        switch (draggableType){
                            case CARD:
                                removeDraggableDragEventHandlers(draggableType, targetGridPane);
                                Building newBuilding = convertCardToBuilding(nodeX, nodeY, x, y);
                                if (newBuilding != null)
                                    onloadBuilding(newBuilding);
                                else {
                                    currentlyDraggedImage.setVisible(true);
                                }
                                node.setOpacity(node.getOpacity() + 0.3);
                                break;
                            case ITEM:
                                world.equipItem(item);
                                removeDraggableDragEventHandlers(draggableType, targetGridPane);
                                removeItemByCoordinates(nodeX, nodeY);
                                if (item.getType().equals("Weapon")) {
                                    x = 0;
                                    y = 1;
                                } else if (item.getType().equals("RareItem")) {
                                    x = 2;
                                    y = 0;
                                    if (equippedRareItemImage != null)
                                        equippedRareItemImage.setImage(null);
                                    equippedRareItemImage = image;
                                } else if (item.getType().equals("Helmet")) {
                                    x = 1;
                                    y = 0;
                                } else if (item.getType().equals("Shield")) {
                                    x = 2;
                                    y = 1;
                                } else if (item.getType().equals("Armour")) {
                                    x = 1;
                                    y = 1;
                                }
                                if (item.getClass() != HealthPotion.class) {
                                    targetGridPane.add(image, x, y, 1, 1);
                                }
                                break;
                            default:
                                break;
                        }

                        draggedEntity.setVisible(false);
                        draggedEntity.setMouseTransparent(false);
                        // remove drag event handlers before setting currently dragged image to null
                        currentlyDraggedImage = null;
                        currentlyDraggedType = null;
                        printThreadingNotes("DRAG DROPPED ON GRIDPANE HANDLED");
                    }
                }
                event.setDropCompleted(true);
                // consuming prevents the propagation of the event to the anchorPaneRoot (as a sub-node of anchorPaneRoot, GridPane is prioritized)
                // https://openjfx.io/javadoc/11/javafx.base/javafx/event/Event.html#consume()
                // to understand this in full detail, ask your tutor or read https://docs.oracle.com/javase/8/javafx/events-tutorial/processing.htm
                event.consume();
            }
        });

        // this doesn't fire when we drag over GridPane because in the event handler for dragging over GridPanes, we consume the event
        anchorPaneRootSetOnDragOver.put(draggableType, new EventHandler<DragEvent>(){
            // https://github.com/joelgraff/java_fx_node_link_demo/blob/master/Draggable_Node/DraggableNodeDemo/src/application/RootLayout.java#L110
            @Override
            public void handle(DragEvent event) {
                if (currentlyDraggedType == draggableType){
                    if(event.getGestureSource() != anchorPaneRoot && event.getDragboard().hasImage()){
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                }
                if (currentlyDraggedType != null){
                    draggedEntity.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                }
                event.consume();
            }
        });

        // this doesn't fire when we drop over GridPane because in the event handler for dropping over GridPanes, we consume the event
        anchorPaneRootSetOnDragDropped.put(draggableType, new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (currentlyDraggedType == draggableType){
                    //Data dropped
                    //If there is an image on the dragboard, read it and use it
                    Dragboard db = event.getDragboard();
                    Node node = event.getPickResult().getIntersectedNode();
                    if(node != anchorPaneRoot && db.hasImage()){
                        //Places at 0,0 - will need to take coordinates once that is implemented
                        currentlyDraggedImage.setVisible(true);
                        draggedEntity.setVisible(false);
                        draggedEntity.setMouseTransparent(false);
                        // remove drag event handlers before setting currently dragged image to null
                        removeDraggableDragEventHandlers(draggableType, targetGridPane);

                        currentlyDraggedImage = null;
                        currentlyDraggedType = null;
                    }
                }
                //let the source know whether the image was successfully transferred and used
                event.setDropCompleted(true);
                event.consume();
            }
        });
    }

    /**
     * remove the card from the world, and spawn and return a building instead where the card was dropped
     * @param cardNodeX the x coordinate of the card which was dragged, from 0 to width-1
     * @param cardNodeY the y coordinate of the card which was dragged (in starter code this is 0 as only 1 row of cards)
     * @param buildingNodeX the x coordinate of the drop location for the card, where the building will spawn, from 0 to width-1
     * @param buildingNodeY the y coordinate of the drop location for the card, where the building will spawn, from 0 to height-1
     * @return building entity returned from the world
     */
    private Building convertCardToBuilding(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        return world.convertCardToBuilding(cardNodeX, cardNodeY, buildingNodeX, buildingNodeY);
    }

    /**
     * remove an item from the unequipped inventory by its x and y coordinates in the unequipped inventory gridpane
     * @param nodeX x coordinate from 0 to unequippedInventoryWidth-1
     * @param nodeY y coordinate from 0 to unequippedInventoryHeight-1
     */
    private void removeItemByCoordinates(int nodeX, int nodeY) {
        world.removeUnequippedInventoryItemByCoordinates(nodeX, nodeY);
    }

    /**
     * add drag event handlers to an ImageView
     * @param view the view to attach drag event handlers to
     * @param draggableType the type of item being dragged - card or item
     * @param sourceGridPane the relevant gridpane from which the entity would be dragged
     * @param targetGridPane the relevant gridpane to which the entity would be dragged to
     */
    private void addDragEventHandlers(ImageView view, DRAGGABLE_TYPE draggableType, GridPane sourceGridPane, GridPane targetGridPane, Card card, Item item){
        view.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                currentlyDraggedImage = view; // set image currently being dragged, so squares setOnDragEntered can detect it...
                currentlyDraggedType = draggableType;
                //Drag was detected, start drap-and-drop gesture
                //Allow any transfer node
                Dragboard db = view.startDragAndDrop(TransferMode.MOVE);

                //Put ImageView on dragboard
                ClipboardContent cbContent = new ClipboardContent();
                cbContent.putImage(view.getImage());
                db.setContent(cbContent);
                view.setVisible(false);

                buildNonEntityDragHandlers(draggableType, sourceGridPane, targetGridPane, card, item);

                draggedEntity.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                switch (draggableType){
                    case CARD:
                        setDraggedEntityCardImage(card);
                        //draggedEntity.setImage(vampireCastleCardImage);
                        break;
                    case ITEM:
                        setDraggedEntityItemImage(item);
                        //draggedEntity.setImage(swordImage);
                        break;
                    default:
                        break;
                }

                draggedEntity.setVisible(true);
                draggedEntity.setMouseTransparent(true);
                draggedEntity.toFront();

                // IMPORTANT!!!
                // to be able to remove event handlers, need to use addEventHandler
                // https://stackoverflow.com/a/67283792
                targetGridPane.addEventHandler(DragEvent.DRAG_DROPPED, gridPaneSetOnDragDropped.get(draggableType));
                anchorPaneRoot.addEventHandler(DragEvent.DRAG_OVER, anchorPaneRootSetOnDragOver.get(draggableType));
                anchorPaneRoot.addEventHandler(DragEvent.DRAG_DROPPED, anchorPaneRootSetOnDragDropped.get(draggableType));

                for (Node n: targetGridPane.getChildren()){
                    // events for entering and exiting are attached to squares children because that impacts opacity change
                    // these do not affect visibility of original image...
                    // https://stackoverflow.com/questions/41088095/javafx-drag-and-drop-to-gridpane
                    gridPaneNodeSetOnDragEntered.put(draggableType, new EventHandler<DragEvent>() {
                        public void handle(DragEvent event) {
                            if (currentlyDraggedType == draggableType){
                            //The drag-and-drop gesture entered the target
                            //show the user that it is an actual gesture target
                                if(event.getGestureSource() != n && event.getDragboard().hasImage()){
                                    n.setOpacity(0.7);
                                }
                            }
                            event.consume();
                        }
                    });
                    gridPaneNodeSetOnDragExited.put(draggableType, new EventHandler<DragEvent>() {
                        public void handle(DragEvent event) {
                            if (currentlyDraggedType == draggableType){
                                n.setOpacity(1);
                            }

                            event.consume();
                        }
                    });
                    n.addEventHandler(DragEvent.DRAG_ENTERED, gridPaneNodeSetOnDragEntered.get(draggableType));
                    n.addEventHandler(DragEvent.DRAG_EXITED, gridPaneNodeSetOnDragExited.get(draggableType));
                }
                event.consume();
            }

        });
    }

    /**
     * remove drag event handlers so that we don't process redundant events
     * this is particularly important for slower machines such as over VLAB.
     * @param draggableType either cards, or items in unequipped inventory
     * @param targetGridPane the gridpane to remove the drag event handlers from
     */
    private void removeDraggableDragEventHandlers(DRAGGABLE_TYPE draggableType, GridPane targetGridPane){
        // remove event handlers from nodes in children squares, from anchorPaneRoot, and squares
        targetGridPane.removeEventHandler(DragEvent.DRAG_DROPPED, gridPaneSetOnDragDropped.get(draggableType));

        anchorPaneRoot.removeEventHandler(DragEvent.DRAG_OVER, anchorPaneRootSetOnDragOver.get(draggableType));
        anchorPaneRoot.removeEventHandler(DragEvent.DRAG_DROPPED, anchorPaneRootSetOnDragDropped.get(draggableType));

        for (Node n: targetGridPane.getChildren()){
            n.removeEventHandler(DragEvent.DRAG_ENTERED, gridPaneNodeSetOnDragEntered.get(draggableType));
            n.removeEventHandler(DragEvent.DRAG_EXITED, gridPaneNodeSetOnDragExited.get(draggableType));
        }
    }

    /**
     * handle the pressing of keyboard keys.
     * Specifically, we should pause when pressing SPACE
     * @param event some keyboard key press
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case SPACE:
            if (isPaused){
                startTimer();
            }
            else{
                pause();
            }
            break;
        default:
            break;
        }
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the world.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     *
     * note that this is put in the controller rather than the loader because we need to track positions of spawned entities such as enemy
     * or items which might need to be removed should be tracked here
     *
     * NOTE teardown functions setup here also remove nodes from their GridPane. So it is vital this is handled in this Controller class
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());

        ChangeListener<Number> xListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        };
        ChangeListener<Number> yListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        };

        // if need to remove items from the equipped inventory, add code to remove from equipped inventory gridpane in the .onDetach part
        ListenerHandle handleX = ListenerHandles.createFor(entity.x(), node)
                                                .onAttach((o, l) -> o.addListener(xListener))
                                                .onDetach((o, l) -> {
                                                    o.removeListener(xListener);
                                                    entityImages.remove(node);
                                                    squares.getChildren().remove(node);
                                                    cards.getChildren().remove(node);
                                                    equippedItems.getChildren().remove(node);
                                                    unequippedInventory.getChildren().remove(node);
                                                })
                                                .buildAttached();
        ListenerHandle handleY = ListenerHandles.createFor(entity.y(), node)
                                                .onAttach((o, l) -> o.addListener(yListener))
                                                .onDetach((o, l) -> {
                                                    o.removeListener(yListener);
                                                    entityImages.remove(node);
                                                    squares.getChildren().remove(node);
                                                    cards.getChildren().remove(node);
                                                    equippedItems.getChildren().remove(node);
                                                    unequippedInventory.getChildren().remove(node);
                                                })
                                                .buildAttached();
        handleX.attach();
        handleY.attach();

        // this means that if we change boolean property in an entity tracked from here, position will stop being tracked
        // this wont work on character/path entities loaded from loader classes
        entity.shouldExist().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> obervable, Boolean oldValue, Boolean newValue) {
                handleX.detach();
                handleY.detach();
            }
        });
    }

    /**
     * we added this method to help with debugging so you could check your code is running on the application thread.
     * By running everything on the application thread, you will not need to worry about implementing locks, which is outside the scope of the course.
     * Always writing code running on the application thread will make the project easier, as long as you are not running time-consuming tasks.
     * We recommend only running code on the application thread, by using Timelines when you want to run multiple processes at once.
     * EventHandlers will run on the application thread.
     */
    private void printThreadingNotes(String currentMethodLabel){
        System.out.println("\n###########################################");
        System.out.println("current method = "+currentMethodLabel);
        System.out.println("In application thread? = "+Platform.isFxApplicationThread());
        System.out.println("Current system time = "+java.time.LocalDateTime.now().toString().replace('T', ' '));
    }

    /* ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────┐ */
    /* │                                       setOnActions to switch screen                                        │ */
    /* └────────────────────────────────────────────────────────────────────────────────────────────────────────────┘ */

    @FXML
    public void switchToMainMenu() throws IOException {
        pause();

        Stage primaryStage = (Stage) anchorPaneRoot.getScene().getWindow();
        //Music
        MusicPlayer.stopMusic();
        MusicPlayer.playMusic("src/music/ScapeMain.wav");

        MainMenuController mainMenuController = new MainMenuController();
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/unsw/loopmania/view/MainMenuView.fxml"));
        menuLoader.setController(mainMenuController);
        Parent mainMenuRoot = menuLoader.load();

        Scene mainMenuScreen = new Scene(mainMenuRoot);
        mainMenuRoot.requestFocus();
        mainMenuScreen.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setScene(mainMenuScreen);
        primaryStage.show();
    }

    @FXML
    public void switchToGameOverScreen() throws IOException {
        pause();

        Stage primaryStage = (Stage) anchorPaneRoot.getScene().getWindow();

        //Music
        MusicPlayer.stopMusic();
        MusicPlayer.playMusic("src/music/DeathSound.wav");
        GameOverScreenController gameOverScreenController = new GameOverScreenController();
        FXMLLoader gameOverScreenLoader = new FXMLLoader(getClass().getResource("/unsw/loopmania/view/GameOverScreenView.fxml"));
        gameOverScreenLoader.setController(gameOverScreenController);
        Parent gameOverScreenRoot = gameOverScreenLoader.load();

        Scene gameOverScreen = new Scene(gameOverScreenRoot);
        gameOverScreenRoot.requestFocus();
        gameOverScreen.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setScene(gameOverScreen);
        primaryStage.show();
    }

    private void switchToWinScreen() throws IOException {
        pause();

        Stage primaryStage = (Stage) anchorPaneRoot.getScene().getWindow();
        //Music
        MusicPlayer.stopMusic();
        MusicPlayer.playMusic("src/music/WinSound.wav");
        
        WinScreenController winScreenController = new WinScreenController();
        FXMLLoader winScreenLoader = new FXMLLoader(getClass().getResource("/unsw/loopmania/view/WinScreenView.fxml"));
        winScreenLoader.setController(winScreenController);
        Parent winScreenRoot = winScreenLoader.load();
        Scene winScreen = new Scene(winScreenRoot);
        winScreenRoot.requestFocus();
        winScreen.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setScene(winScreen);
        primaryStage.show();
    }

    private void switchToHerosCastleMenu() throws IOException {

        Stage primaryStage = (Stage) anchorPaneRoot.getScene().getWindow();
        Scene gameScreen = primaryStage.getScene();

        HerosCastleMenuController herosCastleMenuController =
                new HerosCastleMenuController(LoopManiaWorld.getUnequippedItems(), gameScreen, this);
        FXMLLoader herosCastleMenuLoader = new FXMLLoader(getClass().getResource("/unsw/loopmania/view/HerosCastleMenuView.fxml"));
        herosCastleMenuLoader.setController(herosCastleMenuController);
        Parent herosCastleMenuRoot = herosCastleMenuLoader.load();

        Scene herosCastleMenueScreen = new Scene(herosCastleMenuRoot);
        herosCastleMenuRoot.requestFocus();
        herosCastleMenueScreen.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setScene(herosCastleMenueScreen);
        primaryStage.show();

        pause();
        herosCastleMenuController.refreshInventory();

    }


    /* ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────┐ */
    /* │                                                Helper Methods                                              │ */
    /* └────────────────────────────────────────────────────────────────────────────────────────────────────────────┘ */

    private ImageView onLoadBuildingView(Building building) {
        ImageView view = null;
        if (building instanceof BarracksBuilding) 
            view = new ImageView(barracksBuildingImage);
        else if (building instanceof CampfireBuilding) 
            view = new ImageView(campfireBuildingImage);
        else if (building instanceof TowerBuilding) 
            view = new ImageView(towerBuildingImage);
        else if (building instanceof TrapBuilding) 
            view = new ImageView(trapBuildingImage);
        else if (building instanceof VampireCastleBuilding) 
            view = new ImageView(vampireCastleBuildingImage);
        else if (building instanceof VillageBuilding) 
            view = new ImageView(villageBuildingImage);
        else if (building instanceof ZombiePitBuilding) 
            view = new ImageView(zombiePitBuildingImage);
        else if (building instanceof GlacierBuilding) 
            view = new ImageView(glacierBuildingImage);
        else if (building instanceof CloakingTowerBuilding) 
            view = new ImageView(cloakingTowerBuildingImage);
        return view;
    }

    private ImageView onLoadCardView(Card card) {
        ImageView view = null;
        if (card instanceof BarracksCard) 
            view = new ImageView(barracksCardImage);
        else if (card instanceof CampfireCard) 
            view = new ImageView(campfireCardImage);
        else if (card instanceof TowerCard) 
            view = new ImageView(towerCardImage);
        else if (card instanceof TrapCard) 
            view = new ImageView(trapCardImage);
        else if (card instanceof VampireCastleCard) 
            view = new ImageView(vampireCastleCardImage);
        else if (card instanceof VillageCard) 
            view = new ImageView(villageCardImage);
        else if (card instanceof ZombiePitCard) 
            view = new ImageView(zombiePitCardImage);
        else if (card instanceof GlacierCard) 
            view = new ImageView(glacierCardImage);
        else if (card instanceof CloakingTowerCard) 
            view = new ImageView(cloakingTowerCardImage);
        return view;
    }

    private ImageView onLoadItemView(Item item) {
        ImageView view = null;
        if (item instanceof Armour)
            view = new ImageView(armourImage);
        else if (item instanceof Gold) 
            view = new ImageView(goldImage);
        else if (item instanceof HealthPotion) 
            view = new ImageView(healthPotionImage);
        else if (item instanceof Helmet)
            view = new ImageView(helmetImage);
        else if (item instanceof Shield)
            view = new ImageView(shieldImage);
        else if (item instanceof Staff) 
            view = new ImageView(staffImage);
        else if (item instanceof Stake) 
            view = new ImageView(stakeImage);
        else if (item instanceof Sword) 
            view = new ImageView(swordImage);
        else if (item instanceof TheOneRing)
            view = new ImageView(theOneRingImage);
        else if (item instanceof Anduril)
            view = new ImageView(andurilFlameOfTheWestImage);
        else if (item instanceof TreeStump)
            view = new ImageView(treeStumpImage);
        else if (item instanceof DoggieCoin)
            view = new ImageView(doggieCoinImage);
        return view;
    }

    private ImageView onLoadEnemyView(Enemy enemy) {
        ImageView view = null;
        if (enemy instanceof Slug) 
            view = new ImageView(slugImage);
        else if (enemy instanceof Vampire) 
            view = new ImageView(vampireImage);
        else if (enemy instanceof Zombie) 
            view = new ImageView(zombieImage);
        else if (enemy instanceof Elan)
            view = new ImageView(elanImage);
        else if (enemy instanceof Doggie)
            view = new ImageView(doggieImage);
        return view;
    }

    private void setDraggedEntityCardImage(Card card) {
        if (card instanceof BarracksCard) 
            draggedEntity.setImage(barracksCardImage);
        else if (card instanceof CampfireCard) 
            draggedEntity.setImage(campfireCardImage);
        else if (card instanceof TowerCard) 
            draggedEntity.setImage(towerCardImage);
        else if (card instanceof TrapCard) 
            draggedEntity.setImage(trapCardImage);
        else if (card instanceof VampireCastleCard) 
            draggedEntity.setImage(vampireCastleCardImage);
        else if (card instanceof VillageCard) 
            draggedEntity.setImage(villageCardImage);
        else if (card instanceof ZombiePitCard) 
            draggedEntity.setImage(zombiePitCardImage);
        else if (card instanceof GlacierCard) 
            draggedEntity.setImage(glacierCardImage);
        else if (card instanceof CloakingTowerCard) 
            draggedEntity.setImage(cloakingTowerCardImage);
    }

    private void setDraggedEntityItemImage(Item item) {
        if (item instanceof Armour) 
            draggedEntity.setImage(armourImage);
        else if (item instanceof Gold) 
            draggedEntity.setImage(goldImage);
        else if (item instanceof HealthPotion) 
            draggedEntity.setImage(healthPotionImage);
        else if (item instanceof Helmet) 
            draggedEntity.setImage(helmetImage);
        else if (item instanceof Shield) 
            draggedEntity.setImage(shieldImage);
        else if (item instanceof Staff) 
            draggedEntity.setImage(staffImage);
        else if (item instanceof Sword) 
            draggedEntity.setImage(swordImage);
        else if (item instanceof TheOneRing) 
            draggedEntity.setImage(theOneRingImage);
        else if (item instanceof Anduril)
            draggedEntity.setImage(andurilFlameOfTheWestImage);
        else if (item instanceof TreeStump)
            draggedEntity.setImage(treeStumpImage);
        else if (item instanceof DoggieCoin)
            draggedEntity.setImage(doggieCoinImage);
    }


    public LoopManiaWorld getWorld(){
        return world;
    }

}