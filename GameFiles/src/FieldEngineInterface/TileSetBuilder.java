package FieldEngineInterface;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ben Rasmussen on 7/3/2015.
 */
public class TileSetBuilder extends FlowPane {
	public TileSetBuilder(String setName) throws Exception {
		super(Orientation.HORIZONTAL);

		Scanner reader = new Scanner(new File("GameFiles/Resources/Database/TileSets/" + setName + "/Boundaries.txt"));
		while (reader.hasNext()) {
			VBox editor = new VBox(10);

			String[] line = reader.nextLine().split(":");

			CheckBox temp = new CheckBox(line[0]);

			if (line[1].equals("A"))
				temp.setSelected(true);

			temp.setGraphic(new ImageView(
					new Image("file:///" +
							(new File("GameFiles/Resources/Database/TileSets/" + setName + "/" + line[0] + ".png")).getAbsolutePath())));

			Button delete = new Button("Delete");

			editor.getChildren().addAll(temp, delete);

			super.getChildren().add(editor);
		}

		Button add = new Button("Add Tile");

		super.getChildren().addAll(add, new Text("	Selected tiles will be collisions"));
	}
}
