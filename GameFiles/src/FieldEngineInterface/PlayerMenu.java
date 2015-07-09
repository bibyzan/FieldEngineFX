package FieldEngineInterface;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Scanner;

/**
 * Created by benra_000 on 7/3/2015.
 */
public class PlayerMenu extends BorderPane {
    public PlayerMenu() throws Exception {
        super();

        super.setBackground(new Background(new BackgroundFill(Color.DARKGRAY,null,null)));
        super.setPadding(new Insets(100, 100, 100, 100));

        Scanner reader = new Scanner(new File("GameFiles/Resources/playerConfig.txt"));

        ComboBox<String> spriteSets = new ComboBox<>();

        for (File set: (new File("GameFiles/Resources/Database/SpriteSets")).listFiles())
            if (set.isDirectory())
                spriteSets.getItems().add(set.getName());

        String playerSpriteSet = reader.nextLine().split(":")[1];

        spriteSets.setValue(playerSpriteSet);

        FlowPane controls = new FlowPane(Orientation.HORIZONTAL);
        controls.setPadding(new Insets(20,20,20,20));
        while (reader.hasNext()) {
            String[] line = reader.nextLine().split(":");
            VBox setting = new VBox();
            setting.setSpacing(5);

            Text action = new Text(line[0]);
            Button change = new Button(line[1]);

            setting.getChildren().addAll(action, change);

            controls.getChildren().addAll(setting, new Text("       "));
        }

        Button save = new Button("Save Changes");
        controls.getChildren().add(save);

        setBottom((new SpriteBrowser(playerSpriteSet)).getPreview());
        setCenter(controls);
        setTop(spriteSets);
    }
}
