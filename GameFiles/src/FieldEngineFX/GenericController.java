package FieldEngineFX;

import java.util.ArrayList;
import java.util.HashMap;

public class GenericController extends ArrayList<KeyControl> {
    protected Player player;

    public GenericController(HashMap<String, String> controls, Player player) {
        super();

        super.add(new KeyControl(controls.get("UP"), Direction.UP));
        super.add(new KeyControl(controls.get("DOWN"), Direction.DOWN));
        super.add(new KeyControl(controls.get("LEFT"), Direction.LEFT));
        super.add(new KeyControl(controls.get("RIGHT"), Direction.RIGHT));
        super.add(new KeyControl(controls.get("INTERACT"), Action.INTERACT));

        this.player = player;
    }


    public void keyPressed(String keyCode) {
        if (!World.paused) {
            for (KeyControl key : this)
                if (key.getLetter().equals(keyCode)) {
                    key.setPressed(true);

                    if (key.hasDirection()) {
                        player.setCurrentDirection(key.getDirection());
                        player.setCurrentAction(Action.MOVING);
                    } else if (key.hasAction()) {
                        player.setCurrentAction(key.getAction());
                        player.checkEvents();
                    } else if (key.hasEvent()) {
                        key.runEvent();
                    }
                }

            player.animate();
        }
    }

    public void keyReleased(String keyCode) {
        for (KeyControl key: this)
            if (key.getLetter().equals(keyCode))
                key.setPressed(false);

        for (KeyControl key: this)
            if (key.isPressed()) {
                if (key.hasDirection())
                    player.setCurrentDirection(key.getDirection());

                return;
            }

        player.setCurrentAction(Action.IDLE);
    }
}