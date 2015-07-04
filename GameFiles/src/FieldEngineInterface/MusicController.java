package FieldEngineInterface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.File;

/**
 * Created by Ben Rasmussen on 7/3/2015.
 */
public class MusicController extends FlowPane {
	private boolean playing;
	private MediaPlayer musicPlayer;

	public MusicController(String dir, final ListView<String> browser) {
		super(Orientation.HORIZONTAL);
		super.setAlignment(Pos.CENTER);
		playing = false;

		MediaView mediaView = new MediaView();
		super.getChildren().add(mediaView);

		Button btnPlay = new Button();
		btnPlay.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("InterfaceFiles/play.png"))));
		btnPlay.setShape(new Circle(90, Color.LIGHTGRAY));
		btnPlay.setPrefHeight(85);
		btnPlay.setPrefWidth(85);
		btnPlay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (playing) {
					btnPlay.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("InterfaceFiles/play.png"))));
					musicPlayer.stop();
					playing = false;
				} else {
					btnPlay.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("InterfaceFiles/stop.png"))));
					Media media = null;
					for (String selected : browser.getSelectionModel().getSelectedItems()) {
						media = new Media(new File("GameFiles/Resources/Database/" + dir + "/" + selected).toURI().toString());
					}

					if (media != null) {
						musicPlayer = new MediaPlayer(media);
						musicPlayer.setAutoPlay(true);
					}
					playing = true;
				}
			}
		});

		Button btnDelete = new Button();
		btnDelete.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("InterfaceFiles/trash.png"))));
		btnDelete.setShape(new Circle(70, Color.LIGHTGRAY));
		btnDelete.setPrefHeight(70);
		btnDelete.setPrefWidth(70);
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

			}
		});

		Button btnImport = new Button();
		btnImport.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("InterfaceFiles/import.png"))));
		btnImport.setShape(new Circle(70, Color.LIGHTGRAY));
		btnImport.setPrefHeight(70);
		btnImport.setPrefWidth(70);
		super.getChildren().addAll(btnImport, btnPlay, btnDelete);

	}
}
