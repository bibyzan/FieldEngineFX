package FieldEngineInterface;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Ben Rasmussen on 7/2/2015.
 */
public class SpriteBrowser extends FlowPane {
	private String name;
	private ArrayList<Image> spriteImages;
	private Image faceImage;

	public SpriteBrowser(String setName) {
		super(Orientation.HORIZONTAL);
		super.setAlignment(Pos.TOP_LEFT);
		super.setPadding(new Insets(40, 40, 40, 40));


		name = setName;

		spriteImages = new ArrayList<>();
		for (File category: (new File("GameFiles/Resources/Database/SpriteSets/" + setName)).listFiles()) {
			VBox verticalList = new VBox();
			verticalList.getChildren().add(new Text(category.getName()));

			if (category.isDirectory()) {
				for (File sprite : category.listFiles()) {
					CheckBox tempBox = new CheckBox(sprite.getName());
					tempBox.setGraphic(new ImageView(new Image("file:///" + sprite.getAbsolutePath())));
					verticalList.getChildren().add(tempBox);
					spriteImages.add(new Image("file:///" + sprite.getAbsolutePath()));
					//verticalList.getChildren().add(new ImageView(new Image("file:///" + sprite.getAbsolutePath())));
				}


				Button addNewImage = new Button("add");

				verticalList.getChildren().add(addNewImage);
				super.getChildren().add(verticalList);

				if (verticalList.getHeight() > getPrefHeight())
					setPrefHeight(verticalList.getMaxHeight());
			} else if (category.getName().equals("faceImage.png"))
				faceImage = new Image("file:///" + category.getAbsolutePath());
		}

		VBox editor = new VBox();
		editor.getChildren().add(new Text("batch edits: "));

		Button delete = new Button("delete");
		Button copy = new Button("copy");
		Button cut = new Button("cut");

		editor.getChildren().addAll(delete, copy, cut);

		super.getChildren().addAll(editor, getPreview());
	}

	public BorderPane getPreview() {
		BorderPane pane = new BorderPane();

		pane.setTop(new Text(name));
		pane.getTop().setStyle("-fx-font-family: \"Tahoma\";-fx-font-size: 26px;");
		((Text)pane.getTop()).setTextOrigin(VPos.CENTER);

		pane.setLeft(new SpritePreview(spriteImages));
		pane.setRight(new ImageView(faceImage));
		pane.setStyle("-fx-padding: 10;\n" +
				"-fx-background-color: #4C4C4C;-fx-background-radius: 5;");
		((ImageView)pane.getRight()).setFitHeight(70);
		((ImageView)pane.getRight()).setFitWidth(90);
		pane.getRight().setStyle("-fx-padding: 10;\n" +
				"-fx-background-color: firebrick;-fx-background-radius: 5;");
		pane.setPrefWidth(250);

		return pane;
	}

	private ArrayList<File> obtainSelected() {
		ArrayList<File> selection = new ArrayList<>();

		for (Node parentDir: getChildren())
			if (parentDir instanceof VBox)
				for (Node child: ((VBox)parentDir).getChildren())
					if (child instanceof CheckBox)
						if (((CheckBox)child).isSelected());
							//selection.add(((CheckBox)child).getGraphic())

		return selection;
	}
}
