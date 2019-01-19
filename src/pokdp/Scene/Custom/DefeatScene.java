package pokdp.Scene.Custom;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.AScene;
import pokdp.Scene.SceneManager;
import pokdp.Scene.Wrappers.WrapperSceneDefeat;
import pokdp.Utils.Constantes;
import pokdp.Utils.ConstraintManager.RowConstraintManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class DefeatScene extends WrapperSceneDefeat {

    GridPane pane          = new GridPane();
    Label    defeatMessage = new Label("Vous avez Fui!");

    @Override
    public void load(double width, double height) {
        pane.setPrefHeight(height);
        pane.setPrefWidth(width);
        pane.add(defeatMessage,1,1);

        RowConstraintManager rowConstraintManager = new RowConstraintManager(new int[]{20, 40, 20});

        rowConstraintManager.addPercentToPane(pane);

        pane.setAlignment(Pos.CENTER);

        Button btn = new Button("Continuer");
        btn.setOnAction(actionEvent -> {
            SceneManager.setScene(Constantes.WORLDSCENE_NAME);
        });
        pane.add(btn,1,2);

        try {
            Font font = Font.loadFont(new FileInputStream(new File("assets/fonts/pokemon.ttf")), 30);

            defeatMessage.setFont(font);
            btn.setFont(font);
        }
        catch(FileNotFoundException e) {
        }


        pane.setStyle(
                "-fx-background-image: url(" +
                        "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/intermediary/f/2fb2821a-1406-4a1d-9b04-6668f278e944/d83jrvo-9c5af987-f243-4528-8455-5ca87551aa58.jpg" +
                        "); " +
                        "-fx-background-size: cover;"
        );

        setScene(new Scene(pane, width, height, Color.WHITE));
    }

    @Override
    public void healPokemon(List<Pokemon> listP) {
        for(Pokemon p : listP){
            p.setPV(p.getPVMax());
        }
    }
}
