package pokdp.Scene.Custom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.AScene;
import pokdp.Scene.SceneManager;
import pokdp.Utils.Constantes;
import pokdp.Utils.ConstraintManager.ColConstraintManager;
import pokdp.Utils.ConstraintManager.ConstraintManager;
import pokdp.Utils.ConstraintManager.RowConstraintManager;
import pokdp.Utils.ScreenComponent.Button.ClickableImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StarterScene extends AScene {
    private GridPane gridPane = new GridPane();
    private Random random = new Random();

    @Override
    public void load(double width, double height) {
        Font font = null;
        ConstraintManager rowConstraintManager = new RowConstraintManager(new int[]{30});

        rowConstraintManager.addPercentToPane(gridPane);

        List<ClickableImageView> starterButton = new ArrayList<>();
        int constraintValue = 100 / Constantes.NUMBER_STARTER;

        try {
            font = Font.loadFont(new FileInputStream(new File("assets/fonts/pokemon.ttf")), 30);
        }
        catch(FileNotFoundException e) {
            System.out.println("font not found");
        }

        Label title = new Label("Selectionnez votre pokemon");
        gridPane.setHalignment(title, HPos.CENTER);

        if(font != null) {
            title.setFont(font);
        }

        gridPane.add(title, 0, 0);

        gridPane.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setAlignment(Pos.CENTER);

        GridPane pokemonStarterPane = new GridPane();

        ConstraintManager colConstraintManager = new ColConstraintManager();

        for(int i = 0; i < Constantes.NUMBER_STARTER; ++i) {
            colConstraintManager.addConstraint(constraintValue);
        }

        pokemonStarterPane.setPrefWidth(width);

        colConstraintManager.addPercentToPane(pokemonStarterPane);

        for(int i = 0; i < Constantes.NUMBER_STARTER; ++i) {
            Pokemon randomPokemon = getRandomPokemon();

            Button button = new ClickableImageView(randomPokemon.getSpriteURL(), 256, 256);
            Label nameLabel = new Label(randomPokemon.getName());

            if(font != null) {
                nameLabel.setFont(font);
            }

            button.setOnAction(new EventHandler<ActionEvent>() {
                                   @Override
                                   public void handle(ActionEvent action) {
                                       ((WorldScene)SceneManager.getScene(Constantes.WORLDSCENE_NAME)).getPlayer().addPokemon(randomPokemon);
                                       SceneManager.setScene(Constantes.WORLDSCENE_NAME);
                                   }
                               });

            pokemonStarterPane.setHalignment(button, HPos.CENTER);
            pokemonStarterPane.setHalignment(nameLabel, HPos.CENTER);

            pokemonStarterPane.add(button, i, 0);
            pokemonStarterPane.add(nameLabel, i, 1);
        }


        gridPane.add(pokemonStarterPane, 0, 1);

        Scene scene = new Scene(gridPane, width, height);
        setScene(scene);
    }

    private Pokemon getRandomPokemon() {
        int index = random.nextInt(Constantes.pokemonHashMap.values().size());
        List<Pokemon> list = new ArrayList<>(Constantes.pokemonHashMap.values());

        return list.get(index);
    }
}
