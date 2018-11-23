package pokdp;

import pokdp.AnimationManager.AnimationManagerSprite;
import pokdp.Entity.IEntity;
import pokdp.Entity.Player.*;
import pokdp.Music.Playlist;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

import pokdp.Map.*;
import pokdp.Utils.Constantes;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Pokemon du Pauvre");

        Group root = new Group();
        Scene scene = new Scene(root, 1600, 900, Color.WHITE);

        primaryStage.setFullScreen(true);

        Scale scale = new Scale(Screen.getPrimary().getVisualBounds().getWidth() / 1920, Screen.getPrimary().getVisualBounds().getHeight() / 1080);

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
    }


    public static void main(String[] args) {
        launch(args);
    }
}
