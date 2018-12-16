package pokdp;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pokdp.Combat.Screen.CombatSceneSimple;
import pokdp.Scene.SceneManager;
import pokdp.World.Screen.WorldScene;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pokemon du Pauvre");

        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        Group root = new Group();

        primaryStage.setFullScreen(true);

        Scale scale = new Scale(screenWidth / 1920, screenHeight / 1080);

        scale.setPivotX(0);
        scale.setPivotY(0);

        SceneManager.setManager(primaryStage, screenWidth, screenHeight);

        WorldScene worldScene = new WorldScene(primaryStage);
        CombatSceneSimple combatSceneSimple = new CombatSceneSimple();

        SceneManager.addScene(worldScene, "WorldScene");
        SceneManager.addScene(combatSceneSimple, "CombatScene");

        SceneManager.setScene("WorldScene");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
