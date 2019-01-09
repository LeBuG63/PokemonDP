package pokdp;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pokdp.Scene.Custom.Combat.CombatScene;
import pokdp.Scene.Custom.PokemonMenu.PokemonMenuScene;
import pokdp.Scene.Custom.VictoryScene;
import pokdp.Scene.Custom.PauseScene;
import pokdp.Scene.Custom.PokedexScene;
import pokdp.Scene.AScene;
import pokdp.Scene.SceneManager;
import pokdp.Scene.Custom.StarterScene;
import pokdp.Utils.Constantes;
import pokdp.Scene.Custom.WorldScene;

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
        SceneManager.setScene(Constantes.STARTERSCENE_NAME);

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
        AScene combatSceneSimple = new CombatScene();
        AScene victoryScene = new VictoryScene();
        AScene pokedexScene = new PokedexScene();
        AScene starterScene = new StarterScene();
        AScene pauseScene = new PauseScene();
        AScene pokemonMenu = new PokemonMenuScene();

        SceneManager.addScene(pokemonMenu,Constantes.POKEMONMENU_NAME);
        SceneManager.addScene(pauseScene, Constantes.PAUSESCENE_NAME);
        SceneManager.addScene(starterScene, Constantes.STARTERSCENE_NAME);
        SceneManager.addScene(worldScene, Constantes.WORLDSCENE_NAME);
        SceneManager.addScene(combatSceneSimple, Constantes.COMBATSCENE_NAME);
        SceneManager.addScene(victoryScene, Constantes.VICTORYSCENE_NAME);
        SceneManager.addScene(pokedexScene, Constantes.POKEDEXSCENE_NAME);
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, CustomPreloader.class, args);
    }
}
