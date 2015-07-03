package EngineCode;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by benra_000 on 5/16/2015.
 */
public class Player extends FieldActor {
    private Controls controls;

    public Player() {
        Scanner configParser = new Scanner(getClass().getClassLoader().getResourceAsStream("playerConfig.txt"));

        super.initialize(GameDatabase.spriteSets.get(configParser.nextLine().split(":")[1]));

        String[] importedControls = new String[5];
        for (int i = 0; i < 5; i++)
            importedControls[i] = configParser.nextLine().split(":")[1];

        controls = new Controls(importedControls);
    }

    public EventHandler<KeyEvent> keyBoardListener() {
        return new KeyBoardControl();
    }

    @Override
    boolean isFriendly() {
        return false;
    }

    public void keyPressed(String keyCode) {
        controls.keyPressed(keyCode);
    }

    private class KeyBoardControl implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            controls.keyPressed(event.getText());
        }

    }

    private class Controls {
        private String up, down, left, right, interact;

        public Controls(String[] controls) {
            up = controls[0];
            down = controls[1];
            left = controls[2];
            right = controls[3];
            interact = controls[4];
        }

        public void keyPressed(String keyCode) {
            setCurrentAction(Action.MOVING);

            if (keyCode.equals(up))
                setCurrentDirection(Direction.UP);
            else if (keyCode.equals(down))
                setCurrentDirection(Direction.DOWN);
            else if (keyCode.equals(left))
                setCurrentDirection(Direction.LEFT);
            else if (keyCode.equals(right))
                setCurrentDirection(Direction.RIGHT);
            else if (keyCode.equals(interact))
                setCurrentAction(Action.INTERACT);

            animate();
        }
    }
}