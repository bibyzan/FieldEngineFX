package FieldEngineInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Ben Rasmussen on 7/2/2015.
 */
public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Field Engine Assistant");
		primaryStage.setScene(new Scene(new FieldEngineInterface()));
		primaryStage.setWidth(550);
		primaryStage.setHeight(600);
		primaryStage.show();
	}
}
