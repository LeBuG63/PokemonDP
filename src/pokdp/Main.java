package pokdp;

import Entity.EEntityType;
import Entity.IEntity;
import Entity.Player.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Pokemon du Pauvre");

        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        IEntity player = new Player(scene, null);

        primaryStage.setScene(scene);

        root.getChildren().add(player);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
