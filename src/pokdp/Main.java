package pokdp;

import Entity.ETypeEntity;
import Entity.IEntity;
import Entity.Player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Pokemon du Pauvre");

        Group root = new Group();

        Scene scene = new Scene(root, 500, 500, Color.WHITE);

        primaryStage.setScene(scene);


        IEntity player = new Player("file:assets/sprites/player/player1.png", ETypeEntity.PLAYER, scene);

        root.getChildren().add(player);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
