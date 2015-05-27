package EngineCode;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by benra_000 on 5/16/2015.
 */
public class TesterMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameDatabase.globalPreferences = new GamePreferences();

        primaryStage.setScene(new World());
        primaryStage.show();
    }
}
