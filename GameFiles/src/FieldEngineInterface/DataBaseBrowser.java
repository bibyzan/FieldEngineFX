package FieldEngineInterface;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;

/**
 * Created by Ben Rasmussen on 7/2/2015.
 */
public class DataBaseBrowser extends BorderPane {
	private ListView<String> browser;

	public DataBaseBrowser() {
		fillBrowser();

		super.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
	}

	private void fillBrowser() {
		browser = new ListView<>();
		browser.setPrefWidth(browser.getMinWidth());
		ToggleGroup browse = new ToggleGroup();

		BrowserToggleButton tileSets = new BrowserToggleButton("Tile Sets", browse);
		tileSets.setSelected(true);
		tileSets.buttonClick();

		HBox mainPicker = new HBox();
		BrowserToggleButton spriteSets = new BrowserToggleButton("Sprite Sets", browse);
		BrowserToggleButton soundEffects = new BrowserToggleButton("Sound Effects", browse);
		BrowserToggleButton backgroundMusic = new BrowserToggleButton("Background Music", browse);

		mainPicker.setAlignment(Pos.CENTER);
		mainPicker.setStyle("-fx-padding: 7 30 7 30;");
		mainPicker.setFillHeight(true);

		mainPicker.getChildren().addAll(tileSets, spriteSets, soundEffects, backgroundMusic);


		super.setTop(mainPicker);
	}

	private class BrowserToggleButton extends ToggleButton {
		private String name;

		public BrowserToggleButton(String text, ToggleGroup parentGroup) {
			super(text);

			name = text;

			super.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					buttonClick();
				}
			});
			super.setWidth(super.getMaxWidth());
			super.setToggleGroup(parentGroup);
			super.setStyle("-fx-font-size: 18px;-fx-font-family: \"Andalus\";");
		}

		public void reset() {
			setCenter(null);
			setLeft(null);
			setRight(null);
			setBottom(null);
		}

		public void buttonClick() {
			reset();

			setLeft(browser);
			if (name.equals("Tile Sets")) {
				fillLocalBrowser("TileSets");

			} else if (name.equals("Sprite Sets"))
				fillLocalBrowser("SpriteSets");
			else if (name.equals("Background Music")) {
				fillLocalBrowser("BGMusic");
				setLeft(null);
				setCenter(browser);
				setBottom(new MusicController("BGMusic", browser));
			}

		}

		public void setFileViewer(String selected) throws Exception {
			browser.setPrefWidth(100);

			if (name.equals("Sprite Sets")) {
				setCenter(new SpriteBrowser(selected));
				((SpriteBrowser) getCenter()).setPadding(new Insets(20, 20, 20, 20));

				//setBottom(((SpriteBrowser) getCenter()).getPreview());
				//((BorderPane)getBottom()).setPadding(new Insets(0, 150, 0 , 150));
			} else if (name.equals("Tile Sets")) {
				setCenter(new TileSetBuilder(selected));
			}
		}

		private void fillLocalBrowser(String databaseReference)  {
			browser.getItems().clear();

			for (File item: (new File(
							"GameFiles/Resources/DataBase/" + databaseReference)).listFiles())
				if (item.isDirectory())
					browser.getItems().add(item.getName());

			if (browser.getItems().size() == 0)
				for (File item: (new File(
						"GameFiles/Resources/DataBase/" + databaseReference)).listFiles())
					browser.getItems().add(item.getName());

			browser.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					for (String selected: browser.getSelectionModel().getSelectedItems())
						try {
							setFileViewer(selected);
						} catch (Exception e) { e.printStackTrace(); }
				}
			});
		}
	}


}
