package pokdp;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pokdp.Combat.Screen.CombatSceneSimple;
import pokdp.Combat.Screen.Victory.VictoryScene;
import pokdp.Loading.Screen.LoadingScene;
import pokdp.Pokedex.PokedexScene;
import pokdp.PokemonMenu.UIPokemonMenu;
import pokdp.Scene.AScene;
import pokdp.Scene.SceneManager;
import pokdp.World.Screen.WorldScene;
import sun.java2d.loops.TransformHelper;

public class Main extends Application {
    private Stage stage;
    private double width;
    private double height;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pokemon du Pauvre");

        stage = primaryStage;


        Group root = new Group();

        primaryStage.setFullScreen(true);

        Scale scale = new Scale(width / 1920, height / 1080);

        scale.setPivotX(0);
        scale.setPivotY(0);

        SceneManager.setManager(stage, width, height);
        SceneManager.setScene("WorldScene");

        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        width = screenWidth;
        height = screenHeight;

        SceneManager.setManager(stage, width, height);

        AScene worldScene = new WorldScene(stage);
        AScene combatSceneSimple = new CombatSceneSimple();
        AScene pokemonMenu = new UIPokemonMenu();
        AScene victoryScene = new VictoryScene();
        AScene pokedexScene = new PokedexScene();

        SceneManager.addScene(pokemonMenu,"PokemonMenu");
        SceneManager.addScene(worldScene, "WorldScene");
        SceneManager.addScene(combatSceneSimple, "CombatScene");
        SceneManager.addScene(victoryScene, "VictoryScene");
        SceneManager.addScene(pokedexScene, "PokedexScene");
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, CustomPreloader.class, args);
    }
}
