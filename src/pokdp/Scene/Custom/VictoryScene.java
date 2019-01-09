package pokdp.Scene.Custom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.SceneManager;
import pokdp.Scene.Wrappers.WrapperSceneVictory;
import pokdp.Utils.Constantes;
import pokdp.Utils.ConstraintManager.RowConstraintManager;
import pokdp.Utils.Transition.Transition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VictoryScene extends WrapperSceneVictory {
    private GridPane gridPane = new GridPane();
    private Label msg = new Label();

    private ImageView imageView = new ImageView();

    @Override
    public void load(double heigth, double height) {
        gridPane.setPrefHeight(height);
        gridPane.setPrefWidth(heigth);

        RowConstraintManager rowConstraintManager = new RowConstraintManager(new int[]{20, 40, 20});

        rowConstraintManager.addPercentToPane(gridPane);

        gridPane.setAlignment(Pos.CENTER);

        Button button = new Button("continuer");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                SceneManager.setScene(Constantes.WORLDSCENE_NAME);
            }
        });

        try {
            Font font = Font.loadFont(new FileInputStream(new File("assets/fonts/pokemon.ttf")), 30);

            msg.setFont(font);
            button.setFont(font);
        }
        catch(FileNotFoundException e) {
        }

        Transition.rotate(imageView, 2500, 20);

        gridPane.add(msg, 0, 0);
        gridPane.add(imageView, 0, 1);
        gridPane.add(button, 0, 2);

        gridPane.setHalignment(imageView, HPos.CENTER);
        gridPane.setHalignment(button, HPos.CENTER);

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
}
