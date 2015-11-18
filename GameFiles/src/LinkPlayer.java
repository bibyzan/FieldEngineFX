import FieldEngineFX.Action;
import FieldEngineFX.CommandEvent;
import FieldEngineFX.Direction;
import FieldEngineFX.KeyControl;
import FieldEngineFX.RPGMods.RPGPlayer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Created by Ben on 10/5/2015.
 */
public class LinkPlayer extends RPGPlayer {
    public LinkPlayer() {
        super();

        controls.add(new KeyControl("f", new SwingEvent(.08)));
    }

    private class SwingEvent implements CommandEvent {
        private double duration;

        public SwingEvent(double duration) {
            this.duration = duration;
        }

        @Override
        public void run() {
            setCurrentAction(Action.ATTACK);

            if (currentDirection.equals(Direction.RIGHT))
                spriteSet.switchList("swing_right");
            else if (currentDirection.equals(Direction.LEFT))
                spriteSet.switchList("swing_left");
            else if (currentDirection.equals(Direction.DOWN))
                spriteSet.switchList("swing_down");
            else if (currentDirection.equals(Direction.UP))
                spriteSet.switchList("swing_up");

            setImage(spriteSet.rotateImage());
            Timeline animator = new Timeline(new KeyFrame(Duration.seconds(duration)));
            animator.setCycleCount(spriteSet.getCurrentList().size());
            animator.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    setImage(spriteSet.rotateImage());
                }
            });
            animator.play();

            Timeline endAnimation = new Timeline(new KeyFrame(Duration.seconds(duration * spriteSet.getCurrentList().size() + duration)));
            endAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    spriteSet.turn(currentDirection);
                    setImage(spriteSet.rotateImage());
                    setCurrentAction(Action.IDLE);
                }
            });
            endAnimation.setCycleCount(1);
            endAnimation.play();
        }
    }
}
