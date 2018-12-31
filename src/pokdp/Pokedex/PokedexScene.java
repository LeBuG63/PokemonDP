package pokdp.Pokedex;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.EventManager.EEventType;
import pokdp.EventManager.EventManager;
import pokdp.Scene.AScene;
import pokdp.Scene.SceneManager;
import pokdp.Utils.Constantes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class PokedexScene extends AScene {
    private GridPane gridPane = new GridPane();
    private EventManager eventManager = new EventManager();

    @Override
    public void load(double width, double height) {
        int index = 1;

        gridPane.setPrefWidth(width);
        gridPane.setPrefHeight(height);

        gridPane.setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY, Insets.EMPTY)));

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();

        col1.setPrefWidth(100);
        col2.setPrefWidth(150);
        col3.setPrefWidth(150);


        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col2);
        gridPane.getColumnConstraints().add(col3);

        String[] colName = {"Id", "Nom", "Photo", "Vie", "Atq", "Def", "Vit", "Spe"};

        int col = 0;

        ColumnConstraints columnConstraintsLabel = new ColumnConstraints();

        columnConstraintsLabel.setPrefWidth(130);

        for(int i = 2; i < colName.length - 2; ++i) {
            gridPane.getColumnConstraints().add(columnConstraintsLabel);
        }

        Font font = null;

        try {
            font = Font.loadFont(new FileInputStream(new File("assets/fonts/pokemon.ttf")), 14);
        }
        catch(FileNotFoundException e) {
        }

        for(String name : colName) {
            Label label = new Label(name);
            label.setStyle("-fx-text-fill: white");

            if(font != null) {
                label.setFont(font);
            }

            label.setAlignment(Pos.CENTER);

            gridPane.add(label, col++, 0);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);

        scrollPane.setPannable(true);

        List<Pokemon> pokemonSorted =  new ArrayList<>(Constantes.pokemonHashMap.values());

        Collections.sort(pokemonSorted, Comparator.comparing(Pokemon::getNumber));

        for(Pokemon pokemon : pokemonSorted) {
            Label name = new Label(pokemon.getName());
            ImageView sprite = new ImageView(new Image(pokemon.getSpriteURL(), 128, 128, false, false));
            Label id = new Label("N. " + String.format("%3s", String.valueOf(pokemon.getNumber())).replace(' ', '0'));

            name.setStyle("-fx-text-fill: white");
            id.setStyle("-fx-text-fill: white");

            if(font != null) {
                name.setFont(font);
                id.setFont(font);
            }

            gridPane.add(id, 0, index);
            gridPane.add(name, 1, index);
            gridPane.add(sprite, 2, index);

            int bstatcol = 3;
            for(int stat : pokemon.getArrBaseStats()) {

                if(bstatcol - 3 == pokemon.getArrBaseStats().length - 1)
                    break;

                Label label = new Label(String.valueOf(stat));
                label.setStyle("-fx-text-fill: white");

                if(font != null) {
                    label.setFont(font);
                }

                gridPane.add(label, bstatcol++, index);
            }

            index++;
        }

        eventManager.add(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case P:
                    case ENTER:
                    case ESCAPE:
                        SceneManager.setScene("WorldScene");
                        break;
                }
            }
        }, EEventType.KEYBOARD_PRESSED);


        Scene scene = new Scene(scrollPane, width, height);

        eventManager.attachAllEventsToScene(scene);

        setScene(scene);
    }
}
