package FieldEngineFX;

import FieldEngineFX.RPGMods.RPGPlayer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.util.Duration;

/**
 * Created by Ben on 9/4/2015.
 */
public class World extends Scene {
    public static DialoguePane currentDialogue;
    public static Player player;
    public static Field currentField;
    public static Timeline animator;
    public static boolean paused;

    public World(Field startingField, Player player) {
        super(startingField);

        currentField = startingField;
        this.addScreenListeners();
        this.initPlayer(player);

        currentField.runStartupEvents();
    }

    private void initPlayer(Player player) {
        animator = new Timeline(new KeyFrame(Duration.millis(10), player.getAnimator()));
        animator.setCycleCount(Animation.INDEFINITE);
        animator.play();
        World.player = player;
        paused = false;

        super.setOnKeyPressed(player.getOnKeyDown());
        super.setOnKeyReleased(player.getOnKeyUp());

        currentField.addPlayer(player);
    }

    private void addScreenListeners() {
        super.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                currentField.changeSize(Launcher.screenWidth, oldValue.doubleValue(),
                        Launcher.screenWidth, currentField.getHeight());
                Launcher.screenHeight = currentField.getHeight();

                if (currentDialogue != null)
                    currentDialogue.resizeDialogue();
            }
        });

        super.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                currentField.changeSize(oldValue.doubleValue(),
                        Launcher.screenHeight, currentField.getWidth(), Launcher.screenHeight);
                Launcher.screenWidth = currentField.getWidth();

                if (currentDialogue != null)
                    currentDialogue.resizeDialogue();
            }
        });
    }

    public static void freeze() {
        animator.stop();
        paused = true;
    }

    public static void resume() {
        animator.play();
        paused = false;
    }
}
