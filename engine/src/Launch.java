import EngineCode.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by benra_000 on 5/19/2015.
 */
public class Launch extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        GameDatabase.init();
        Field field = new Field("Castle", "center", GameDatabase.tileSets.get("Castle"));
        World.currentScreen = field;

        primaryStage.setScene(new World());
        primaryStage.show();
    }
}
