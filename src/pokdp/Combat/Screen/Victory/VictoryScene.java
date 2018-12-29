package pokdp.Combat.Screen.Victory;

import com.sun.javafx.collections.ImmutableObservableList;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Main;
import pokdp.Scene.SceneManager;
import pokdp.Scene.Wrappers.WrapperSceneVictory;
import pokdp.Utils.Constantes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VictoryScene extends WrapperSceneVictory {
    private GridPane gridPane = new GridPane();
    private Label msg = new Label();

    private ImageView imageView = new ImageView();

    @Override
    public void load(double heigth, double height) {
        Font.loadFont(VictoryScene.class.getResource("pokemon.ttf").toExternalForm(), 40);

        gridPane.setPrefHeight(height);
        gridPane.setPrefWidth(heigth);

        RowConstraints colMsg = new RowConstraints();
        RowConstraints colPok = new RowConstraints();
        RowConstraints colAction = new RowConstraints();

        colMsg.setPercentHeight(20);
        colPok.setPercentHeight(40);
        colAction.setPercentHeight(20);

        gridPane.getRowConstraints().add(colMsg);
        gridPane.getRowConstraints().add(colAction);
        gridPane.getRowConstraints().add(colPok);

        gridPane.setAlignment(Pos.CENTER);

        Button button = new Button("continuer");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                SceneManager.setScene("WorldScene");
            }
        });

        try {
            Font font = Font.loadFont(new FileInputStream(new File("assets/fonts/pokemon.ttf")), 30);

            msg.setFont(font);
            button.setFont(font);
        }
        catch(FileNotFoundException e) {
        }

        rotate(imageView);

        gridPane.add(msg, 0, 0);
        gridPane.add(imageView, 0, 1);
        gridPane.add(button, 0, 2);

        gridPane.setStyle(
                "-fx-background-image: url(" +
                        "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/intermediary/f/2fb2821a-1406-4a1d-9b04-6668f278e944/d83jrvo-9c5af987-f243-4528-8455-5ca87551aa58.jpg" +
                        "); " +
                        "-fx-background-size: cover;"
        );

        setScene(new Scene(gridPane, heigth, height, Color.WHITE));
    }

    @Override
    public void setPokemon(Pokemon pokemon) {
        imageView.setImage(new Image(pokemon.getSpriteURL(),512, 512, false, false));

        msg.setText("Vous avez capture: " + pokemon.getName());
    }

    private void zoom(Node node) {

    }

    private void rotate(Node node) {
        RotateTransition rt = new RotateTransition(Duration.millis(2500), node);

        double angle = 20;

        rt.setFromAngle(-angle);
        rt.setByAngle(angle*2);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setAutoReverse(true);

        rt.play();
    }
}
