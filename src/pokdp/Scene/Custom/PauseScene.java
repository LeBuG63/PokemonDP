package pokdp.Scene.Custom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.EventManager.EEventType;
import pokdp.EventManager.EventManager;
import pokdp.Scene.SceneManager;
import pokdp.Scene.Wrappers.WrapperScenePause;
import pokdp.Stream.Serialization.IObjectSerializationManager;
import pokdp.Stream.Serialization.SerializablePokemon;
import pokdp.Stream.Serialization.SimpleObjectSerializationManager;
import pokdp.Utils.Constantes;
import pokdp.Utils.ConstraintManager.ConstraintManager;
import pokdp.Utils.ConstraintManager.RowConstraintManager;
import pokdp.Utils.ScreenComponent.Button.ButtonStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PauseScene extends WrapperScenePause {
    private TilePane tilePane = new TilePane();
    private Player player;
    private EventManager eventManager = new EventManager();
    private IObjectSerializationManager objectSerializationManager = new SimpleObjectSerializationManager();

    public void setAttributes(Player player) {
        this.player = player;
    }

    @Override
    public void load(double width, double height) {
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

        tilePane.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setVgap(100);
        tilePane.setOrientation(Orientation.VERTICAL);

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

        tilePane.getChildren().add(nameLabel);
        tilePane.getChildren().add(saveButton);
        tilePane.getChildren().add(loadButton);
        tilePane.getChildren().add(quitButton);

        Scene scene = new Scene(tilePane, width, height);

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
