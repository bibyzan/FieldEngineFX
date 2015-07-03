package EngineCode;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by benra_000 on 5/17/2015.
 */
public class World extends Scene {
    public static Field currentScreen;
    public static Player player;

    private Timeline animator;

    public World() {
        super(currentScreen);

        player = new Player();
        player.setLocation(16,16);
        currentScreen.getChildren().add(player);

        animator = new Timeline(new KeyFrame(Duration.millis(30), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.move();
            }
        }));
        animator.setCycleCount(Animation.INDEFINITE);
        
        super.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                player.keyPressed(event.getText());

                if (player.getCurrentAction().equals(Action.MOVING))
                    animator.play();

            }
        });

        super.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                player.setCurrentAction(Action.IDLE);
                animator.stop();
            }
        });
    }

    public static void moveTo(String dirName, String fieldName, int row, int col) {

    }
}
