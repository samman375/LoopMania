package unsw.loopmania.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.javatuples.Pair;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.codefx.libfx.listener.handle.ListenerHandle;
import org.codefx.libfx.listener.handle.ListenerHandles;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.beans.property.SimpleIntegerProperty;

import unsw.loopmania.model.Items.*;
import unsw.loopmania.model.Items.BasicItems.*;
import unsw.loopmania.model.Items.RareItems.*;
import unsw.loopmania.model.Items.RareItems.TheOneRing;
import unsw.loopmania.model.Items.RareItems.TreeStump;
import unsw.loopmania.model.Entity;
import unsw.loopmania.model.LoopManiaWorld;
import unsw.loopmania.model.Buildings.HerosCastleBuilding;

public class HerosCastleMenuController  {

    @FXML
    private Label currentGold;

    @FXML
    private GridPane unequippedInventory;

    private List<Item> inventory;

    private Item trackedItem;

    private LoopManiaWorld world;

    private Scene gameScene;

    private LoopManiaWorldController loopManiaWorldController;

    public HerosCastleMenuController(List<Item> inventory, Scene gameScene,
                                     LoopManiaWorldController loopManiaWorldController) {
        this.inventory = inventory;
        this.world = loopManiaWorldController.getWorld();
        this.gameScene = gameScene;
        this.loopManiaWorldController = loopManiaWorldController;
    }

    @FXML
    public void initialize() {
        Image inventorySlotImage = new Image((new File("src/images/empty_slot.png")).toURI().toString());
        for (int x=0; x<LoopManiaWorld.unequippedInventoryWidth; x++){
            for (int y=0; y<LoopManiaWorld.unequippedInventoryHeight; y++){
                ImageView emptySlotView = new ImageView(inventorySlotImage);
                unequippedInventory.add(emptySlotView, x, y);
            }
        }
        currentGold.textProperty().bind(new SimpleIntegerProperty(world.getGold()).asString());
        unequippedInventory.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Node image = event.getPickResult().getIntersectedNode();
                int y = GridPane.getRowIndex(image);
                int x = GridPane.getColumnIndex(image);
                Item item = world.getUnequippedInventoryItemEntityByCoordinates(x, y);
                if (item != null && image.getOpacity() == 1) {
                    trackedItem = item;
                    image.setOpacity(0.7);
                }
                else if (item != null) {
                    trackedItem = null;
                    image.setOpacity(1);
                }
            }
        });
    }

    // refresh inventory
    public void refreshInventory() {
        List<Item> items = world.getUnequippedInventoryItems();
        for (Item item : items) {
            if (inventory.contains(item)) {
                loadImage(item);
            }
        }
    }

    // track inventory
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

        ListenerHandle handleX = ListenerHandles.createFor(entity.x(), node)
                                               .onAttach((o, l) -> o.addListener(xListener))
                                               .onDetach((o, l) -> {
                                                    o.removeListener(xListener);
                                                    unequippedInventory.getChildren().remove(node);
                                                })
                                               .buildAttached();
        ListenerHandle handleY = ListenerHandles.createFor(entity.y(), node)
                                               .onAttach((o, l) -> o.addListener(yListener))
                                               .onDetach((o, l) -> {
                                                   o.removeListener(yListener);
                                                   unequippedInventory.getChildren().remove(node);
                                                })
                                               .buildAttached();
        handleX.attach();
        handleY.attach();

        entity.shouldExist().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> obervable, Boolean oldValue, Boolean newValue) {
                handleX.detach();
                handleY.detach();
            }
        });
    }

    // load inv images
    private void loadImage(Item item) {
        // Get Image File
        if (item instanceof HealthPotion) {
            Image image = new Image((new File("src/images/brilliant_blue_new.png")).toURI().toString());
            ImageView view = new ImageView(image);
            trackPosition(item, view);
            unequippedInventory.getChildren().add(view);
        } else if (item instanceof Armour) {
            Image image = new Image((new File("src/images/armour.png")).toURI().toString());
            ImageView view = new ImageView(image);
            trackPosition(item, view);
            unequippedInventory.getChildren().add(view);
        } else if (item instanceof Helmet) {
            Image image = new Image((new File("src/images/helmet.png")).toURI().toString());
            ImageView view = new ImageView(image);
            trackPosition(item, view);
            unequippedInventory.getChildren().add(view);
        } else if (item instanceof Shield) {
            Image image = new Image((new File("src/images/shield.png")).toURI().toString());
            ImageView view = new ImageView(image);
            trackPosition(item, view);
            unequippedInventory.getChildren().add(view);
        } else if (item instanceof Staff) {
            Image image = new Image((new File("src/images/staff.png")).toURI().toString());
            ImageView view = new ImageView(image);
            trackPosition(item, view);
            unequippedInventory.getChildren().add(view);
        } else if (item instanceof Stake) {
            Image image = new Image((new File("src/images/stake.png")).toURI().toString());
            ImageView view = new ImageView(image);
            trackPosition(item, view);
            unequippedInventory.getChildren().add(view);
        } else if (item instanceof Sword) {
            Image image = new Image((new File("src/images/basic_sword.png")).toURI().toString());
            ImageView view = new ImageView(image);
            trackPosition(item, view);
            unequippedInventory.getChildren().add(view);
        } else if (item instanceof TheOneRing) {
            Image image = new Image((new File("src/images/the_one_ring.png")).toURI().toString());
            ImageView view = new ImageView(image);
            trackPosition(item, view);
            unequippedInventory.getChildren().add(view);
        } else if (item instanceof Anduril) {
            Image image = new Image((new File("src/images/anduril_flame_of_the_west.png")).toURI().toString());
            ImageView view = new ImageView(image);
            trackPosition(item, view);
            unequippedInventory.getChildren().add(view);
        } else if (item instanceof TreeStump) {
            Image image = new Image((new File("src/images/treestump.png")).toURI().toString());
            ImageView view = new ImageView(image);
            trackPosition(item, view);
            unequippedInventory.getChildren().add(view);
        } 
    }

    @FXML
    private Pane heroCastleMenu;

    @FXML
    private Button buyHelmetButton;

    @FXML
    private Button buyArmourButton;

    @FXML
    private Button buyShieldButton;

    @FXML
    private Button buySwordButton;

    @FXML
    private Button buyStakeButton;

    @FXML
    private Button buyStaffButton;

    @FXML
    private Button buyHealthPotionButton;

    @FXML
    private Button closeButton;

    @FXML
    private Button sellItemButton;

    @FXML
    void buyArmour(ActionEvent event) {
        Pair<Integer, Integer> firstAvailableSlot = world.getFirstAvailableSlotForItem();
        BasicItem item = world.createBasicItem("Armour", firstAvailableSlot);
        if (HerosCastleBuilding.isPurchaseable(world.getGold(),(Item) item)) {
            if(world.purchaseItem(item)) {
                currentGold.textProperty().bind(new SimpleIntegerProperty(world.getGold()).asString());
                inventory.add(item);
                loadImage(item);
                world.getBoughtItems().add(item);
            }
        }
    }
        

    @FXML
    void buyHealthPotion(ActionEvent event) {
        Pair<Integer, Integer> firstAvailableSlot = world.getFirstAvailableSlotForItem();
        BasicItem item = world.createBasicItem("HealthPotion", firstAvailableSlot);
        if (HerosCastleBuilding.isPurchaseable(world.getGold(),(Item) item)) {
            if(world.purchaseItem(item)) {
                currentGold.textProperty().bind(new SimpleIntegerProperty(world.getGold()).asString());
                inventory.add(item);
                loadImage(item);
                world.getBoughtItems().add(item);
            }
        }
    }

    @FXML
    void buyHelmet(ActionEvent event) {
        Pair<Integer, Integer> firstAvailableSlot = world.getFirstAvailableSlotForItem();
        BasicItem item = world.createBasicItem("Helmet", firstAvailableSlot);
        if (HerosCastleBuilding.isPurchaseable(world.getGold(),(Item) item)) {
            if(world.purchaseItem(item)) {
                currentGold.textProperty().bind(new SimpleIntegerProperty(world.getGold()).asString());
                inventory.add(item);
                loadImage(item);
                world.getBoughtItems().add(item);
            }
        }
    }

    @FXML
    void buyShield(ActionEvent event) {
        Pair<Integer, Integer> firstAvailableSlot = world.getFirstAvailableSlotForItem();
        BasicItem item = world.createBasicItem("Shield", firstAvailableSlot);
        if (HerosCastleBuilding.isPurchaseable(world.getGold(),(Item) item)) {
            if(world.purchaseItem(item)) {
                currentGold.textProperty().bind(new SimpleIntegerProperty(world.getGold()).asString());
                inventory.add(item);
                loadImage(item);
                world.getBoughtItems().add(item);
            }
        }
    }

    @FXML
    void buyStaff(ActionEvent event) {
        Pair<Integer, Integer> firstAvailableSlot = world.getFirstAvailableSlotForItem();
        BasicItem item = world.createBasicItem("Staff", firstAvailableSlot);
        if (HerosCastleBuilding.isPurchaseable(world.getGold(),(Item) item)) {
            if(world.purchaseItem(item)) {
                currentGold.textProperty().bind(new SimpleIntegerProperty(world.getGold()).asString());
                inventory.add(item);
                loadImage(item);
                world.getBoughtItems().add(item);
            }
        }
    }

    @FXML
    void buyStake(ActionEvent event) {
        Pair<Integer, Integer> firstAvailableSlot = world.getFirstAvailableSlotForItem();
        BasicItem item = world.createBasicItem("Stake", firstAvailableSlot);
        if (HerosCastleBuilding.isPurchaseable(world.getGold(),(Item) item)) {
            if(world.purchaseItem(item)) {
                currentGold.textProperty().bind(new SimpleIntegerProperty(world.getGold()).asString());
                inventory.add(item);
                loadImage(item);
                world.getBoughtItems().add(item);
            }
        }
    }

    @FXML
    void buySword(ActionEvent event) {
        Pair<Integer, Integer> firstAvailableSlot = world.getFirstAvailableSlotForItem();
        BasicItem item = world.createBasicItem("Sword", firstAvailableSlot);
        if (HerosCastleBuilding.isPurchaseable(world.getGold(),(Item) item)) {
            if(world.purchaseItem(item)) {
                currentGold.textProperty().bind(new SimpleIntegerProperty(world.getGold()).asString());
                inventory.add(item);
                loadImage(item);
                world.getBoughtItems().add(item);
            }
        }
    }
    @FXML
    void sellItem(ActionEvent event) {

        if (trackedItem instanceof Armour) {
            BasicItem armour = (BasicItem) trackedItem;
            world.sellItem(armour);
        } 
        if (trackedItem instanceof Shield) {
            BasicItem shield = (BasicItem) trackedItem;
            world.sellItem(shield);
        } 
        if (trackedItem instanceof Helmet) {
            BasicItem helmet = (BasicItem) trackedItem;
            world.sellItem(helmet);
        } 
        if (trackedItem instanceof Sword) {
            BasicItem sword = (BasicItem) trackedItem;
            world.sellItem(sword);
        } 
        if (trackedItem instanceof Staff) {
            BasicItem staff = (BasicItem) trackedItem;
            world.sellItem(staff);
        } 
        if (trackedItem instanceof Stake) {
            BasicItem stake = (BasicItem) trackedItem;
            world.sellItem(stake);
        } 
        if (trackedItem instanceof HealthPotion) {
            BasicItem healthPotion = (BasicItem) trackedItem;
            world.sellItem(healthPotion);
        } 
        if (trackedItem instanceof DoggieCoin) {
            BasicItem doggieCoin = (BasicItem) trackedItem;
            world.sellItem(doggieCoin);
        } 
        if (trackedItem instanceof Anduril) {
            BasicItem anduril = (BasicItem) trackedItem;
            world.sellItem(anduril);
        } 
        if (trackedItem instanceof TheOneRing) {
            BasicItem theOneRing = (BasicItem) trackedItem;
            world.sellItem(theOneRing);
        } 
        if (trackedItem instanceof TreeStump) {
            BasicItem treeStump = (BasicItem) trackedItem;
            world.sellItem(treeStump);
        } 
        inventory.remove(trackedItem);
        currentGold.textProperty().bind(new SimpleIntegerProperty(world.getGold()).asString());
    }

    @FXML
    void switchToGame(ActionEvent event) throws IOException {

        Stage primaryStage = (Stage) heroCastleMenu.getScene().getWindow();
        gameScene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setScene(gameScene);
        
        primaryStage.show();
        loopManiaWorldController.startTimer();
    }

}
    