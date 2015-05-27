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

    public Player(String dirName) {
        super(dirName);

        controls = new Controls(getClass().getClassLoader().getResourceAsStream(dirName + "/controls.txt"));
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

        public Controls(InputStream inStream) {
            try {
                Scanner fileReader = new Scanner(inStream);

                up = fileReader.nextLine().split(":")[1];
                down = fileReader.nextLine().split(":")[1];
                left = fileReader.nextLine().split(":")[1];
                right = fileReader.nextLine().split(":")[1];
                interact = fileReader.nextLine().split(":")[1];
            } catch (Exception e) {
                e.printStackTrace();
            }
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