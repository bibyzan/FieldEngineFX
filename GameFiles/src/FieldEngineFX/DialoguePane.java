package FieldEngineFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.swing.*;

/**
 * Created by Ben on 9/28/2015.
 */
public class DialoguePane extends BorderPane {
    protected Button exit;

    public DialoguePane(String titleText) {
        fillTopBar(titleText);
        styleDialogue();

        World.currentField.getChildren().add(this);
        World.freeze();
    }

    public DialoguePane(Node content, String titleText) {
        fillTopBar(titleText);
        styleDialogue();

        super.setCenter(content);
        World.currentField.getChildren().add(this);
        World.freeze();
    }

    public void resizeDialogue() {
        super.setTranslateX(World.currentField.getWidth() * .15);
        super.setPrefWidth(World.currentField.getWidth() * .75);
        super.setPrefHeight(World.currentField.getHeight() * .3);
        super.setTranslateY(World.currentField.getHeight() * .6);
    }

    private void styleDialogue() {
        super.setStyle("-fx-border-color: black;\n"
                + "-fx-border-width: 3;\n"
                + "-fx-border-style: solid;\n");
        super.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        resizeDialogue();
    }

    private void fillTopBar(String titleText) {
        BorderPane topBar = new BorderPane();
        topBar.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, null, null)));
        Text title = new Text(titleText);
        title.setFont(Launcher.bodyFont);
        topBar.setLeft(title);

        exit = new Button("x");
        exit.setTextFill(Color.WHITE);
        exit.setBackground(new Background(new BackgroundFill(Color.DARKRED, new CornerRadii(2), null)));
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                remove();
            }
        });

        topBar.setRight(exit);

        super.setTop(topBar);
    }

    public void remove() {
        World.currentField.getChildren().remove(this);
        World.resume();
    }

    public Button getExit() {
        return exit;
    }

    public static void showMessageDialogue(String message) {
        Text text = new Text(message);
        text.setEffect(new DropShadow());
        World.currentDialogue = new DialoguePane(text, "message");
    }
}
