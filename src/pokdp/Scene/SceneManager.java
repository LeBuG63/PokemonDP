package pokdp.Scene;

import javafx.stage.Stage;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.Wrappers.WrapperSceneCombat;
import pokdp.Scene.Wrappers.WrapperScenePause;
import pokdp.Scene.Wrappers.WrapperScenePokemonMenu;
import pokdp.Scene.Wrappers.WrapperSceneVictory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class SceneManager {
    private static HashMap<String, AScene> sceneHashMap = new HashMap<>();
    private static double windowWidth;
    private static double windowHeight;

    private static LinkedList<String> listNameSceneSaved = new LinkedList<>();

    private static Stage stage;

    public static void setManager(Stage actstage, double width, double height) {
        windowWidth = width;
        windowHeight = height;

        stage = actstage;
    }

    public static Stage getStage() {
        return stage;
    }

    /**
     * retourne une scene en fonction de son nom
     * @param name  le nom de la scene
     * @return      la scene en question
     */
    public static AScene getScene(String name) {
        if(sceneHashMap.containsKey(name)) {
            return sceneHashMap.get(name);
        }

        return null;
    }

    /**
     * ajoute une scene
     * @param scene     la scene a ajouter
     * @param sceneName son nom
     */
    public static void addScene(AScene scene, String sceneName) {
        scene.load(windowWidth, windowHeight);

        sceneHashMap.put(sceneName, scene);
    }

    /**
     * dit quelle scene doit etre utilise par le stag
     * @param sceneName le nom de la scene
     */
    public static void setScene(String sceneName) {
        if(sceneHashMap.containsKey(sceneName)) {
            listNameSceneSaved.push(sceneName);
            stage.setScene(sceneHashMap.get(sceneName).getScene());
        }
    }

    public static void setLastScene() {
        if(listNameSceneSaved.size() > 0) {
            listNameSceneSaved.pop();
            String lastName = listNameSceneSaved.pop();

            setScene(lastName);
        }
    }

    /**
     * ajoute une scene de combat
     * @param sceneName le nom de la scene
     * @param player    le joueur
     * @param pok1      le pokemon attaquant
     * @param pok2      le pokemon adverse
     */
    public static void setSceneCombat(String sceneName, Player player, Pokemon pok1, Pokemon pok2) {
        if(sceneHashMap.containsKey(sceneName)) {
            WrapperSceneCombat combatScene = (WrapperSceneCombat)sceneHashMap.get(sceneName);

            combatScene.setAttributes(player, pok1, pok2);

            setScene(sceneName);
        }
    }

    /**
     * ajoute une scene pour gerer ses pokemons
     * @param sceneName     le nom de la scene
     * @param pokemonList   une liste de pokemon
     */
    public static void setScenePokemon(String sceneName, Player player) {
        if (sceneHashMap.containsKey(sceneName)) {
            WrapperScenePokemonMenu scenePokemonMenu = (WrapperScenePokemonMenu) sceneHashMap.get(sceneName);

            scenePokemonMenu.setAttributes(player);
            setScene(sceneName);
        }
    }

    /**
     * ajoute une scene de victoire
     * @param sceneName     le nom de la scene
     * @param pokemon       le pokemon vaincu
     */
    public static void setSceneVictory(String sceneName, Pokemon pokemon) {
        if (sceneHashMap.containsKey(sceneName)) {
            WrapperSceneVictory sceneVictory = (WrapperSceneVictory) sceneHashMap.get(sceneName);

            sceneVictory.setPokemon(pokemon);

            setScene(sceneName);
        }
    }

    /**
     * ajoute une scene de pause
     * @param sceneName     le nom de la scene
     * @param player        le joueur
     */
    public static void setScenePause(String sceneName, Player player) {
        if (sceneHashMap.containsKey(sceneName)) {
            WrapperScenePause scenePause = (WrapperScenePause) sceneHashMap.get(sceneName);

            scenePause.setAttributes(player);

            setScene(sceneName);
        }
    }
}
