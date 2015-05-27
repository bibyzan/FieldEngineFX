package EngineCode;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by benra_000 on 5/16/2015.
 */
public class DialoguePane extends BorderPane {
    private Pane parentPane;

    public DialoguePane(String text, Image graphic) {
        super();

        super.setStyle("-fx-border-color: grey;");
        super.setBackground(new Background(new BackgroundFill(GameDatabase.globalPreferences.getNeutralColor(), null, null)));

        Text inText = new Text(text);
        inText.setFont(GameDatabase.globalPreferences.getBodyFont());
        super.setCenter(inText);

        double width = graphic.getWidth() + (text.length());
        double height = 15 + graphic.getHeight();
        super.setPrefSize(width,height);

        if (graphic != null)
            super.setLeft(new ImageView(graphic));
    }

    public void addItself(Pane parent) {
        parentPane = parent;
        BorderPane topPane = new BorderPane();

        Button exitWindow = new Button("x");
        //exitWindow.setBackground(new Background(new BackgroundFill(Color.MAROON, null, null)));
        exitWindow.setFont(new Font("Tahoma BOLD", 15));
        exitWindow.setTextFill(Color.MAROON);
        exitWindow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                parentPane.getChildren().remove(obtainThis());
            }
        });

        topPane.setRight(exitWindow);
        topPane.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));

        super.setTop(topPane);

        parentPane.getChildren().add(this);
    }

    private DialoguePane obtainThis() {
        return this;
    }
}
