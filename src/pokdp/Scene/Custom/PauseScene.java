package pokdp.Scene.Custom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.EventManager.EEventType;
import pokdp.EventManager.EventManager;
import pokdp.Scene.SceneManager;
import pokdp.Scene.Wrappers.WrapperScenePause;
import pokdp.Utils.ScreenComponent.Button.ButtonStyle;
import pokdp.Stream.Serialization.IObjectSerializationManager;
import pokdp.Stream.Serialization.SerializablePokemon;
import pokdp.Stream.Serialization.SimpleObjectSerializationManager;
import pokdp.Utils.Constantes;
import pokdp.Utils.ConstraintManager.ConstraintManager;
import pokdp.Utils.ConstraintManager.RowConstraintManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PauseScene extends WrapperScenePause {
    private GridPane gridPane = new GridPane();
    private Player player;
    private EventManager eventManager = new EventManager();
    private IObjectSerializationManager objectSerializationManager = new SimpleObjectSerializationManager();

    public void setAttributes(Player player) {
        this.player = player;
    }

    @Override
    public void load(double width, double height) {
        ConstraintManager rowConstraintManager = new RowConstraintManager(new int[]{10,33,33,33});

        Button saveButton = new ButtonStyle("Sauvegarder", Constantes.DEFAULT_BUTTON);
        Button loadButton = new ButtonStyle("Charger", Constantes.DEFAULT_BUTTON);
        Button quitButton = new ButtonStyle("Quitter", Constantes.DEFAULT_BUTTON);

        Label nameLabel = new Label("Pause");

        Font font = null;

        try {
            font = Font.loadFont(new FileInputStream(new File("assets/fonts/pokemon.ttf")), 42);
        }
        catch(FileNotFoundException e) {
            System.out.println("font not found");
        }

        nameLabel.setFont(font);

        gridPane.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setAlignment(Pos.CENTER);

        rowConstraintManager.addPercentToPane(gridPane);

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                saveSerializablePokemon();
                SceneManager.setScene(Constantes.WORLDSCENE_NAME);
            }
        });

        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<SerializablePokemon> loadedSerializablePokemons = (ArrayList <SerializablePokemon>) objectSerializationManager.readObjectFromFile(Constantes.SAVE_FILE_PATH);
                List<Pokemon> pokemonList = new ArrayList<>();

                if(loadedSerializablePokemons == null) {
                    pokemonList = player.getPokemonList();
                }
                else {
                    for(SerializablePokemon serializablePokemon : loadedSerializablePokemons) {
                        pokemonList.add(new Pokemon(serializablePokemon));
                    }
                }

                player.setNewPokemonOrder(pokemonList);
                SceneManager.setScene(Constantes.WORLDSCENE_NAME);
            }
        });

        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                saveSerializablePokemon();
                SceneManager.getStage().close();
            }
        });

        eventManager.add(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ESCAPE:
                        SceneManager.setScene(Constantes.WORLDSCENE_NAME);
                        return;
                }
            }
        }, EEventType.KEYBOARD_PRESSED);

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(saveButton, 0, 1);
        gridPane.add(loadButton, 0, 2);
        gridPane.add(quitButton, 0, 3);

        Scene scene = new Scene(gridPane, width, height);

        eventManager.attachAllEventsToScene(scene);

        setScene(scene);
    }

    private void saveSerializablePokemon() {
        List<SerializablePokemon> serializablePokemons = new ArrayList<>();

        for(Pokemon pokemon : player.getPokemonList()) {
            SerializablePokemon serializablePokemon = new SerializablePokemon(pokemon);
            serializablePokemons.add(serializablePokemon);
        }

        objectSerializationManager.writeObjectFromFile(Constantes.SAVE_FILE_PATH, serializablePokemons);
    }
}
