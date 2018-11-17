package pokdp;

import Entity.EEntityType;
import Entity.IEntity;
import Entity.Player.Player;
import Map.Tile;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import Map.*;
import pokdp.Constantes;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Pokemon du Pauvre");

        Group root = new Group();
        Scene scene = new Scene(root, 1600, 900, Color.WHITE);

        IEntity player = new Player(scene);
        Map map = new Map((int)scene.getWidth() / Constantes.DEFAULT_TILE_MAP_WIDTH,(int)scene.getHeight() / Constantes.DEFAULT_TILE_MAP_HEIGHT);

        map.addTileSet(new ObjectSet()
        {{
            load("file:assets/sprites/terrain/grass1.png", 0.80f);
            load("file:assets/sprites/terrain/grass2.png", 0.10f);
        }}, ETerrainType.FOREST);

        map.addDecoObjectSet(new ObjectSet()
        {{
            load("file:assets/sprites/objects/factory1.png", 0.10f);
            load("file:assets/sprites/objects/mart1.png", 0.10f);
            load("file:assets/sprites/objects/pc1.png", 0.10f);
        }}, ETerrainType.FOREST);

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
