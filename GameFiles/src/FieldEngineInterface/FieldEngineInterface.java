package FieldEngineInterface;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Font;

/**
 * Created by Ben Rasmussen on 7/2/2015.
 */
public class FieldEngineInterface extends TabPane {
	public FieldEngineInterface() {
		super();

		Tab dataBaseBrowser = new Tab("Database Browser");
		dataBaseBrowser.setClosable(false);
		dataBaseBrowser.setStyle("-fx-font-size: 24px;-fx-background-color:#99CCFF;-fx-font-family: \"Andalus\";");
		dataBaseBrowser.setContent(new DataBaseBrowser());

		Tab mapBuilder = new Tab("Map Builder");
		mapBuilder.setClosable(false);
		mapBuilder.setStyle("-fx-font-size: 24px;-fx-background-color:#99CCFF;-fx-font-family: \"Andalus\";");

		super.getTabs().addAll(dataBaseBrowser, mapBuilder);
		super.setTabMaxHeight(200);
	}
}
