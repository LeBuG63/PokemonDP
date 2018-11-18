package pokdp;

import AnimationManager.AnimationManagerSprite;
import Entity.IEntity;
import Entity.Player.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

import Map.*;
import Utils.Constantes;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Pokemon du Pauvre");

        Group root = new Group();
        Scene scene = new Scene(root, 1600, 900, Color.WHITE);

        Map map = new Map((int)scene.getWidth() / Constantes.DEFAULT_TILE_MAP_WIDTH,(int)scene.getHeight() / Constantes.DEFAULT_TILE_MAP_HEIGHT);

        map.addTileSet(new ObjectSet()
        {{
            load("file:assets/sprites/terrain/grass1.png", 0.80f);
            load("file:assets/sprites/terrain/grass2.png", 0.10f);

            load(new ArrayList<String>()
            {{
                add("file:assets/sprites/terrain/flower1_down.png");
                add("file:assets/sprites/terrain/flower1_up.png");
            }}, 0.10f, AnimationManagerSprite.RANDOM_DURATION);

        }}, ETerrainType.FOREST);

        map.addDecoObjectSet(new ObjectSet()
        {{
            load("file:assets/sprites/objects/factory1.png", 0.10f);
            load("file:assets/sprites/objects/mart1.png", 0.10f);
            load("file:assets/sprites/objects/pc1.png", 0.10f);
            load("file:assets/sprites/objects/tree1.png", 0.55f);
        }}, ETerrainType.FOREST);


        IEntity player = new Player(scene, map.getDecoObjectList());

        map.generateRandomTerrain(ETerrainType.FOREST);

        primaryStage.setScene(scene);

        root.getChildren().add(map);
        root.getChildren().add(player);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
