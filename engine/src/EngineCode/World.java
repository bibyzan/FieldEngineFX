package EngineCode;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * Created by benra_000 on 5/17/2015.
 */
public class World extends Scene {
    public static Field currentScreen;
    public static Player player;

    public World() {
        super(currentScreen);

        player = new Player("player");
        player.setLocation(16,16);
        currentScreen.getChildren().add(player);

        super.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                player.keyPressed(event.getText());

                if (player.getCurrentAction().equals(Action.MOVING)) {
                    for (Point2D point : player.getContainedPoints()) {
                        FieldObject fromPoint = currentScreen.getGrid().get(currentScreen.calcPositionAtPoint(point));
                        if (fromPoint != null) {
                            if (fromPoint.isColliding(player)) {
                                //player.revert(fromPoint);
                                //return;
                            }
                        }
                    }
                }

                if (player.getCurrentAction().equals(Action.MOVING))
                    player.move();

            }
        });

        super.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                player.setCurrentAction(Action.IDLE);
            }
        });
    }

    public static void moveTo(String dirName, String fieldName, int row, int col) {

    }
}
