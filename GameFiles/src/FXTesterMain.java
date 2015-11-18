import FieldEngineFX.DamageHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Ben on 9/29/2015.
 */
public class FXTesterMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new DamageHandler(50,10,300,25, 5, .5)));

        primaryStage.show();
    }
}
