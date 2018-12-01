package pokdp;

import pokdp.AnimationManager.AnimationManagerSprite;
import pokdp.Combat.Screen.CombatSceneSimple;
import pokdp.Combat.Screen.ICombatScene;
import pokdp.Entity.IEntity;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

import pokdp.LoadPokemon.IPokemonLoader;
import pokdp.LoadPokemon.PokemonLoaderXML;
import pokdp.Map.ETerrainType;
import pokdp.Map.Map;
import pokdp.Map.ObjectSet;
import pokdp.Music.Playlist;
import pokdp.Type.EType;
import pokdp.Utils.Constantes;

import java.util.ArrayList;
import java.util.List;

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
/*
        Map map = new Map(1920 / Constantes.DEFAULT_TILE_MAP_WIDTH,1080 / Constantes.DEFAULT_TILE_MAP_HEIGHT + 1);

        map.addTileSet(new ObjectSet()
        {{
            load("file:assets/sprites/terrain/grass1.png", 0.80f, IEntity.HAS_NO_COLLISION);
            load("file:assets/sprites/terrain/grass2.png", 0.10f, IEntity.HAS_NO_COLLISION);

            load(new ArrayList<String>()
            {{
                add("file:assets/sprites/terrain/flower1_down.png");
                add("file:assets/sprites/terrain/flower1_up.png");
            }}, 0.10f, AnimationManagerSprite.RANDOM_DURATION, IEntity.HAS_NO_COLLISION);
        }}, ETerrainType.FOREST);

        map.addDecoObjectSet(new ObjectSet()
        {{
            load("file:assets/sprites/terrain/tallgrass1.png", 0.5f, IEntity.HAS_NO_COLLISION);
            load("file:assets/sprites/objects/factory1.png", 0.10f, IEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/mart1.png", 0.10f, IEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/pc1.png", 0.10f, IEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/littletree1.png", 0.55f, IEntity.HAS_COLLISION);
        }}, ETerrainType.FOREST);

        Playlist playlist = new Playlist()
        {{
            load("assets/musics/opening.wav", "opening");
            load("assets/musics/professoroak.wav", "professoroak");
            load("assets/musics/palettetown.wav", "palettetown");
        }};

        //MusicManager musicManager = new MusicManager();
        //musicManager.load("assets/musics/opening.mp3", "opening");
        //musicManager.play("opening");

        playlist.shuffle();

        playlist.play();

        IEntity player = new Player(scene, map.getDecoObjectList());

        map.generateRandomTerrain(ETerrainType.FOREST);

        primaryStage.setScene(scene);

        root.getChildren().add(map);
        root.getChildren().add(player);

        scene.getRoot().getTransforms().setAll(scale);

        primaryStage.show();
*/
/*
            Pokemon pok1 = new Pokemon("Bullbizare", new int[] {45, 49, 49, 65, 65, 45}, new int[] {0, 0, 252, 252, 8, 0}, new int[] {31, 31, 31, 31, 31, 31}, 100, EType.PLANTE);
            Pokemon pok2 = new Pokemon("Tauros", new int[] {75, 100, 95, 40, 70, 110}, new int[] {0, 252, 0, 8, 0, 252}, new int[] {31, 31, 31, 31, 31, 31}, 100, EType.FEU);

            ICombatScene combatScene = new CombatSceneSimple(pok1, pok2, screenWidth, screenHeight);
            combatScene.getScene().getRoot().getTransforms().setAll(scale);
            primaryStage.setScene(combatScene.getScene());

        primaryStage.show();
*/

        IPokemonLoader pokemonLoader = new PokemonLoaderXML();

        List<Pokemon> pokemonList = pokemonLoader.load("/home/rachartier/Documents/cours/java/pokemondp/assets/pokemons/list.xml");

        System.out.println(pokemonList.size());

        for(Pokemon p : pokemonList) {
            System.out.println(p.getName());
            System.out.println(p.getPV());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
