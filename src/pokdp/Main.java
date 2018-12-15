package pokdp;

import javafx.scene.text.Font;
import pokdp.AnimationManager.AnimationManagerSprite;
import pokdp.Entity.AEntity;
import pokdp.Entity.Player.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pokdp.Map.ETerrainType;
import pokdp.Map.Map;
import pokdp.Map.ObjectSet;
import pokdp.Music.Playlist;
import pokdp.Utils.Constantes;
import pokdp.World.Screen.WorldScreen;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pokemon du Pauvre");

        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        Group root = new Group();
        Scene scene = new Scene(root, screenWidth, screenHeight, Color.WHITE);

        primaryStage.setFullScreen(true);

        Scale scale = new Scale(screenWidth / 1920, screenHeight / 1080);

        scale.setPivotX(0);
        scale.setPivotY(0);

        WorldScreen.load(primaryStage);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
