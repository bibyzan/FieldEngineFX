package FieldEngineFX;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 9/4/2015
 * Essentially the back-end main for the engine. This class is intended to be extended
 * and ran by the user of this engine. It automatically pulls the game preferences from
 * the program resource directory and fills the database, while simultaneously starting up
 * the JavaFX thread for the game to run on.
 *
 * @author Ben Rasmussen
 */
public abstract class Launcher extends Application {
    public static Font titleFont, bodyFont;
    public static int gridHeight, gridWidth;
    public static double screenHeight, screenWidth;

    public abstract World worldType();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Database.init();

        HashMap<String, String> preferences = retrievePreferences("settings.txt");

        titleFont = new Font(preferences.get("title_font_name"), Double.parseDouble(preferences.get("title_font_size")));
        bodyFont = new Font(preferences.get("body_font_name"), Double.parseDouble(preferences.get("body_font_size")));

        gridHeight = Integer.parseInt(preferences.get("grid_height"));
        gridWidth = Integer.parseInt(preferences.get("grid_width"));
        screenWidth = Integer.parseInt(preferences.get("window_width"));
        screenHeight = Integer.parseInt(preferences.get("window_height"));

        primaryStage.setScene(worldType());
        primaryStage.setWidth(screenWidth);
        primaryStage.setHeight(screenHeight);
        primaryStage.show();
    }

    /**
     * This method is to be used around the engine for the purpose of reading
     * preference files for different parts of the in game database or certain settings
     *
     * @param path  system path within the program resources to be read from
     * @return      hash map of the settings read
     */

    public static HashMap<String, String> retrievePreferences(String path) {
        HashMap<String, String> settings = new HashMap<>();

        Scanner reader = new Scanner(pullResource(path));

        while (reader.hasNext()) {
            String[] line = reader.nextLine().split(":");

            settings.put(line[0], line[1]);
        }

        return settings;
    }

    /**
     * This method is to help assist around the code of this engine
     * in reading different types of files within a jar context.
     *
     * @param path Relative path within the resource directory
     * @return The InputStream for use wherever it's needed.
     */

    public static InputStream pullResource(String path) {
        System.out.println(path);

        return Launcher.class.getClassLoader().getResourceAsStream(path);
    }

    public static Point2D getPointAtPosition(Position pos) {
        return new Point2D((pos.getCol() - 1) * calcFillWidth(), (pos.getRow() - 1) * calcFillHeight());
    }

    public static double calcFillWidth() {
        return screenWidth / gridWidth;
    }

    public static double calcFillHeight() {
        return screenHeight / gridHeight;
    }
}
