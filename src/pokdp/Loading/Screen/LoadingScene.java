package pokdp.Loading.Screen;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pokdp.Scene.AScene;
import pokdp.Utils.Transition.Transition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class LoadingScene extends AScene {
    private GridPane gridPane = new GridPane();

    public LoadingScene() {
        ImageView imageView = new ImageView(new Image("file:assets/loadingscreen/pokeball.png"));
        Label name = new Label("Pokemon du Pauvre");
        Label loading = new Label("Chargement...");

        RowConstraints rowConstraints = new RowConstraints();

        rowConstraints.setPercentHeight(33);

        for(int i = 0; i < 3; ++i) {
            gridPane.getRowConstraints().add(rowConstraints);
        }

        gridPane.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));

        try {
            Font font = Font.loadFont(new FileInputStream(new File("assets/fonts/pokemon.ttf")), 80);

            name.setFont(font);
            loading.setFont(font);
        }
        catch(FileNotFoundException e) {
            System.out.println("font not found");
        }

        name.setStyle("-fx-text-fill: white");
        loading.setStyle("-fx-text-fill: white");

        Transition.rotate(imageView, 2000, 40);

        gridPane.setAlignment(Pos.TOP_CENTER);

        gridPane.setHalignment(imageView, HPos.CENTER);
        gridPane.setHalignment(loading, HPos.CENTER);

        gridPane.add(name, 0, 0);
        gridPane.add(imageView, 0, 1);
        gridPane.add(loading, 0, 2);

        setScene(new Scene(gridPane));
    }

    @Override
    public void load(double width, double height) {
        gridPane.setPrefWidth(width);
        gridPane.setPrefHeight(height);

        gridPane.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));

        setScene(new Scene(gridPane, width, height));
    }
}
