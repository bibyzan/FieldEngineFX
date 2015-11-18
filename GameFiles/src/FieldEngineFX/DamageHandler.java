package FieldEngineFX;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created on 9/29/2015.
 *
 * Class made to contain a players hit points.
 * It automatically regenerates health back to
 * a certain point after a player is injured.
 *
 * @author Ben Rasmussen
 */
public class DamageHandler extends Pane {
    //max hp per bar, and max amount of bars
    protected int maxHitPoints, maxHitPointBars;

    //amount of hp left in the most current bar, and the amount of bars left
    protected int hitPoints, hitPointBars;

    //amount of time before hp begins to regen after taking damage, and how fast it regens
    protected double beginRegenTime, regenSpeed;

    //timers for handling the regen sequence
    private Timeline regenStarter, hitPointsRegen;

    /**
     * Only constructor for this hit points class. Initializes graphics and
     * sets up the timers for handling damage
     *
     * parameters fill private data
     */

    public DamageHandler(int maxHitPoints, int maxHitPointBars, double prefWidth, double prefHeight, double beginRegenTime, double regenSpeed) {
        this.maxHitPoints = maxHitPoints;
        this.maxHitPointBars = maxHitPointBars;

        this.hitPoints = maxHitPoints;
        this.hitPointBars = maxHitPointBars;

        this.beginRegenTime = beginRegenTime;
        this.regenSpeed = regenSpeed;

        super.setStyle("-fx-border-color: maroon;\n"
                + "-fx-border-width: 3;\n"
                + "-fx-border-style: solid;\n");

        super.setPrefHeight(prefHeight);
        super.setPrefWidth(prefWidth);
        this.setUpHealthRegen();
        this.refreshGraphics();
    }

    /**
     * Initializes timers for use when FieldActor takes damage.
     */

    public void setUpHealthRegen() {
        regenStarter = new Timeline(new KeyFrame(Duration.seconds(beginRegenTime)));
        hitPointsRegen = new Timeline(new KeyFrame(Duration.seconds(regenSpeed)));

        hitPointsRegen.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hitPoints++;

                if (hitPoints == maxHitPoints)
                    hitPointsRegen.stop();

                refreshGraphics();
            }
        });
        hitPointsRegen.setCycleCount(Animation.INDEFINITE);

        regenStarter.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hitPointsRegen.play();
            }
        });
        regenStarter.setCycleCount(1);
    }

    public void refreshGraphics(){
        ImageView bar = new ImageView();

        for (int i = 0; i < hitPointBars; i++) {
            double dividedWidth = super.getPrefWidth()/maxHitPointBars;

            bar = new ImageView(new Image(Launcher.pullResource("Database/Graphic_Icons/GUI/red_square.png")));
            bar.setFitHeight(super.getPrefHeight() - 6);
            bar.setFitWidth(dividedWidth * .95 - 3);
            bar.setX((dividedWidth * i) + 3);
            bar.setY(3);

            super.getChildren().add(bar);
        }
        bar.prefWidth(super.getPrefWidth() * ((double)hitPoints / (double)maxHitPoints));
    }

    public void takeDamage(int damage) {
        regenStarter.stop();
        regenStarter.stop();

        while (damage > 0) {

            if (hitPoints <= 0) {
                hitPointBars--;

                hitPoints = maxHitPoints;
            }

            if (hitPointBars <= 0)
                return;

            hitPoints--;
            damage--;
        }

        refreshGraphics();
        regenStarter.play();
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public int getMaxHitPointBars() {
        return maxHitPointBars;
    }

    public void setMaxHitPointBars(int maxHitPointBars) {
        this.maxHitPointBars = maxHitPointBars;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getHitPointBars() {
        return hitPointBars;
    }

    public void setHitPointBars(int hitPointBars) {
        this.hitPointBars = hitPointBars;
    }

    public double getBeginRegenTime() {
        return beginRegenTime;
    }

    public void setBeginRegenTime(double beginRegenTime) {
        this.beginRegenTime = beginRegenTime;
    }

    public double getRegenSpeed() {
        return regenSpeed;
    }

    public void setRegenSpeed(double regenSpeed) {
        this.regenSpeed = regenSpeed;
    }
}
