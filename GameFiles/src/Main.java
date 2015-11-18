import FieldEngineFX.Database;
import FieldEngineFX.Launcher;
import Field.Test;
import FieldEngineFX.RPGMods.RPGPlayer;
import FieldEngineFX.World;
import javafx.scene.layout.BorderPane;

/**
 * Created by Ben on 9/4/2015.
 */
public class Main extends Launcher {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public World worldType() {
       LinkPlayer player = new LinkPlayer();

        World world = new World(new Test(), player);
        BorderPane gui = new BorderPane();

        gui.setCenter(World.currentField);
        gui.setBottom(player.getRPGHud(40));

        world.setRoot(gui);
        return world;
    }
}
