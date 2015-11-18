package FieldEngineFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;

/**
 * 9/18/2015
 *
 * Main controller class for everything having to do with the user itself.
 * This class handles all keyboard inputs, tests for collisions as the Player
 * moves around the screen, and animates the player by utilizing the parent
 * FieldActor class.
 *
 * @author Ben Rasmussen
 */
public class Player extends FieldActor {
    //private object that keeps track of what all keys are down and performs the actions
    protected GenericController controls;

    /**
     * Main constructor for Player class.
     * Using the Launcher retrievePreferences method, the player_config.txt
     * file holds the controls and the indicated SpriteSet for use
     * in the player.
     */
    public Player() {
        HashMap<String, String> loadedSettings = Launcher.retrievePreferences("player_config.txt");

        super.initialize(Database.spriteSets.get(loadedSettings.get("SPRITE_SET")));

        controls = new GenericController(loadedSettings, this);
    }

    /**
     * boolean requiring the override from the parent FieldActor class.
     *
     * @return false: indicating the Player can take damage.
     */
    @Override
    boolean isFriendly() {
        return false;
    }

    /**
     * Code that runs every time the timer is invoked in the World class.
     * Checks for if the Player is moving, and if the Player is about
     * to pass into an illegal part of the Field.
     *
     * @return EventHandler used in the TimeLine object in the World class.
     */

    public EventHandler<ActionEvent> getAnimator() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentAction.equals(Action.MOVING)) {
                    if (!checkCollisions())
                        move();

                    checkEvents();
                }
            }
        };
    }

    /**
     * This method is called every time the timer code is invoked while the Player is moving.
     * All this does is check the FieldGraphic grid that the Player is on
     * to see if the Player is about to walk into a boundary.
     *
     * First the method moves the player, then it loops through all the FieldGraphics
     * to see if the Player is touching any of the graphics in his new location.
     * Then it makes a parent call to move the Player back to its original position.
     *
     * @return whether or not the Player is about to hit a boundary.
     */

    private boolean checkCollisions() {
        Grid<FieldGraphic> graphicsGrid = World.currentField.getGraphicsGrid();
        boolean collision = false;

        this.move();

        for (FieldGraphic graphic: graphicsGrid.values())
            if (graphic.hasBoundary())
                if (this.isColliding(graphic))
                    collision = true;

        this.revert();

        return collision;
    }

    public void checkEvents() {
        if (currentAction.equals(Action.MOVING)) {
            for (FieldEvent event: World.currentField.getEvents())
                if (event.isColliding(this))
                    event.runEvent(currentAction);
        } else if (currentAction.equals(Action.INTERACT)) {
            this.move();

            for (FieldEvent event: World.currentField.getEvents())
                if (event.isColliding(this))
                    event.runEvent(currentAction);

            this.revert();
        }
    }

    /**
     * @return The code made to run in the world class
     * when the user releases a key.
     */
    public EventHandler<KeyEvent> getOnKeyUp() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controls.keyReleased(event.getText());
            }
        };
    }

    /**
     * @return Code made to run in the World class
     * when the user presses a key.
     */
    public EventHandler<KeyEvent> getOnKeyDown() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controls.keyPressed(event.getText());
            }
        };
    }
}
