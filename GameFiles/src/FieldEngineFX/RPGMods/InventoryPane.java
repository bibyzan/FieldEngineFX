package FieldEngineFX.RPGMods;

import FieldEngineFX.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;

import java.util.ArrayList;

/**
 * Created by Ben on 10/5/2015.
 */
public class InventoryPane extends DialoguePane {
    private RPGPlayer player;

    public InventoryPane(RPGPlayer player) {
        super("Pause Menu");

        this.player = player;
    }

    //method automatically called by super
    @Override
    public void resizeDialogue() {
        super.setTranslateX(World.currentField.getWidth() * .15);
        super.setPrefWidth(World.currentField.getWidth() * .75);
        super.setPrefHeight(World.currentField.getHeight() * .75);
        super.setTranslateY(World.currentField.getHeight() * .15);

        super.setLeft(makeLeftPane());
    }

    private GridPane makeLeftPane() {
        GridPane chooser = new GridPane();

        ToggleGroup navigateGroup = new ToggleGroup();
        int i = 0;
        for (String option: new String[] {"Inventory", "Player", "Quest", "Save", "Exit"} ) {
            MenuToggler button = new MenuToggler(option, navigateGroup);
            button.setPrefHeight(getPrefHeight() / 5 - (3 / 4));
            button.setPrefWidth(getPrefWidth() * .25);

            chooser.add(button,0,i);

            i++;
        }

        return chooser;
    }

    private FlowPane makeInventory() {
        FlowPane inventory = new FlowPane(Orientation.HORIZONTAL,5,5);

        inventory.setOpaqueInsets(new Insets(10));
        inventory.setPrefWrapLength(super.getPrefWidth());

        for (Item item: player.getInventory()) {
            item.setFitWidth(Launcher.calcFillWidth());
            item.setFitHeight(Launcher.calcFillHeight());

            HBox itemView = new HBox(5);


        }

        return inventory;
    }

    private class MenuToggler extends ToggleButton {
        public MenuToggler(String text, ToggleGroup group) {
            super(text);

            super.setToggleGroup(group);
            super.setOnAction(changePane(text));
        }

        public EventHandler<ActionEvent> changePane(final String text) {
            return new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (text.equals("Inventory")) {

                    }
                }
            };
        }
    }
}
