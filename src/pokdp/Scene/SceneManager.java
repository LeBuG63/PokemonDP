package pokdp.Scene;

import javafx.stage.Stage;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.Wrappers.WrapperSceneCombat;
import pokdp.Scene.Wrappers.WrapperScenePokemonMenu;
import pokdp.Scene.Wrappers.WrapperSceneVictory;

import java.util.HashMap;
import java.util.List;

public abstract class SceneManager {
    private static HashMap<String, AScene> sceneHashMap = new HashMap<>();
    private static double windowWidth;
    private static double windowHeight;

    private static Stage stage;

    public static void setManager(Stage actstage, double width, double height) {
        windowWidth = width;
        windowHeight = height;

        stage = actstage;
    }

    public static Stage getStage() {
        return stage;
    }

    public static AScene getScene(String name) {
        if(sceneHashMap.containsKey(name)) {
            return sceneHashMap.get(name);
        }

        return null;
    }

    public static void addScene(AScene scene, String sceneName) {
        scene.load(windowWidth, windowHeight);

        sceneHashMap.put(sceneName, scene);
    }

    public static void setScene(String sceneName) {
        if(sceneHashMap.containsKey(sceneName)) {
            stage.setScene(sceneHashMap.get(sceneName).getScene());
        }
    }

    public static void setSceneCombat(String sceneName, Player player, Pokemon pok1, Pokemon pok2) {
        if(sceneHashMap.containsKey(sceneName)) {
            WrapperSceneCombat combatScene = (WrapperSceneCombat)sceneHashMap.get(sceneName);

            combatScene.setAttributes(player, pok1, pok2);

            stage.setScene(combatScene.getScene());
        }
    }

    public static void setScenePokemon(String sceneName, List<Pokemon> pokemonList) {
        if (sceneHashMap.containsKey(sceneName)) {
            WrapperScenePokemonMenu scenePokemonMenu = (WrapperScenePokemonMenu) sceneHashMap.get(sceneName);

            scenePokemonMenu.setPokemonList(pokemonList);

            stage.setScene(scenePokemonMenu.getScene());
        }
    }

    public static void setSceneVictory(String sceneName, Pokemon pokemon) {
        if (sceneHashMap.containsKey(sceneName)) {
            WrapperSceneVictory sceneVictory = (WrapperSceneVictory) sceneHashMap.get(sceneName);

            sceneVictory.setPokemon(pokemon);

            stage.setScene(sceneVictory.getScene());
        }
    }
}
