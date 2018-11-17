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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Pokemon du Pauvre");

        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);

        IEntity player = new Player(scene);
        Map map = new Map(30,30);

        map.addTileSet(new TileSet()
        {{
            load("file:assets/sprites/terrain/grass1.png");
            load("file:assets/sprites/terrain/grass2.png");
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
