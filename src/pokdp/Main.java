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
import pokdp.Music.MusicManager;
import pokdp.Music.Playlist;
import pokdp.Type.EType;
import pokdp.Utils.Constantes;

import java.util.ArrayList;
import java.util.HashMap;
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

     /*Playlist playlist = new Playlist()
        {{
            load("assets/musics/opening.wav", "opening");
            load("assets/musics/professoroak.wav", "professoroak");
            load("assets/musics/palettetown.wav", "palettetown");
        }};

        MusicManager musicManager = new MusicManager();
        musicManager.load("assets/musics/opening.wav", "opening");
        musicManager.play("opening");

        playlist.shuffle();

        playlist.play();
    */
        IEntity player = new Player(scene, map.getDecoObjectList(), primaryStage);

        map.generateRandomTerrain(ETerrainType.FOREST);

        primaryStage.setScene(scene);

        root.getChildren().add(map);
        root.getChildren().add(player);

        scene.getRoot().getTransforms().setAll(scale);

        primaryStage.show();
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

        IEntity player = new Player(scene, map.getDecoObjectList());

        IPokemonLoader pokemonLoader = new PokemonLoaderXML();
        HashMap<String, Pokemon> pokemonHashMap = pokemonLoader.load("assets/pokemons/list.xml");

        ((Player) player).getPokemonList().add(pokemonHashMap.get("Bullbizare"));

        ICombatScene combatScene = new CombatSceneSimple(pokemonHashMap.get("Bullbizare"), pokemonHashMap.get("Tauros"), screenWidth, screenHeight);

        //combatScene.getScene().getRoot().getTransforms().setAll(scale);
        primaryStage.setScene(combatScene.getScene());

        primaryStage.show();
        */
    }


    public static void main(String[] args) {
        launch(args);
    }
}
