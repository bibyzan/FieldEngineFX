package FieldEngineFX.RPGMods;

import FieldEngineFX.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Created by Ben on 9/29/2015.
 */
public class RPGPlayer extends Player {
    protected ArrayList<Item> inventory;
    protected Equipment equipped;
    protected DamageHandler hitPoints;
    protected int currency;

    public RPGPlayer() {
        inventory = new ArrayList<>();
        hitPoints = new DamageHandler(10,3,150,40,3,.5);

        Fist startingFist = new Fist();
        currency = 0;
        inventory.add(startingFist);
        equipped = startingFist;

        super.controls.add(new KeyControl("i", new CommandEvent() {
            @Override
            public void run() {
                World.currentDialogue = new InventoryPane(getThis());
            }
        }));
    }

    public void takeDamage(int damage, Direction incomingDirection) {
        hitPoints.takeDamage(damage);

        Timeline animator = new Timeline(new KeyFrame(Duration.millis(5)));
        animator.setCycleCount(damage * 10);
        animator.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shift(incomingDirection);
            }
        });
        animator.play();
    }

    public BorderPane getRPGHud(double height) {
        BorderPane hud = new BorderPane();

        hud.setPrefHeight(height);
        hud.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));

        VBox health = new VBox();
        Text healthText = new Text("Health");
        healthText.setFont(Launcher.bodyFont);
        health.getChildren().addAll(healthText, hitPoints);

        VBox equipment = new VBox();
        Text equipText = new Text("Equipped");
        equipText.setFont(Launcher.bodyFont);
        equipped.setFitHeight(height);
        equipped.setFitWidth(height);
        equipment.getChildren().addAll(equipText,equipped);

        hud.setLeft(health);
        hud.setRight(equipment);
        hud.setStyle("-fx-border-color: black;\n"
                + "-fx-border-width: 3;\n"
                + "-fx-border-style: solid;\n");

        return hud;
    }

    private RPGPlayer getThis() {
        return this;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Equipment getEquipped() {
        return equipped;
    }

    public int getCurrency() {
        return currency;
    }

    public DamageHandler getHitPoints() {
        return hitPoints;
    }
}
