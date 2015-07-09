package FieldEngineInterface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;


/**
 * Created by benra_000 on 7/9/2015.
 */
public class MapBuilder extends BorderPane {
    private Image selected;

    public MapBuilder(String tileSet) {

    }

    private class Selector extends VBox {
        public Selector(String tileSet) {
            for (File tile: (new File("GameFiles/Resources/TileSets/" + tileSet)).listFiles()) {
                if (tile.getName().substring(tile.getName().lastIndexOf('.')).equals(".png")) {
                    Button temp = new Button(tile.getName());
                    temp.setGraphic(new ImageView(new Image("file:///" + tile.getAbsolutePath())));
                    temp.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });

                }
            }
        }
    }

    private class Builder extends Pane {

    }
}
