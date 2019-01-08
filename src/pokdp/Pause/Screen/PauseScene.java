package pokdp.Pause.Screen;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.EventManager.EEventType;
import pokdp.EventManager.EventManager;
import pokdp.Pokedex.PokedexScene;
import pokdp.Scene.AScene;
import pokdp.Scene.SceneManager;
import pokdp.Scene.Wrappers.WrapperScenePause;
import pokdp.Serialization.IObjectSerializationManager;
import pokdp.Serialization.SerializablePokemon;
import pokdp.Serialization.SimpleObjectSerializationManager;
import pokdp.Utils.Constantes;
import pokdp.Utils.ConstraintManager.ConstraintManager;

import java.util.ArrayList;
import java.util.List;

public class PauseScene extends WrapperScenePause {
    private GridPane gridPane = new GridPane();
    private Player player;
    private EventManager eventManager = new EventManager();

    public void setAttributes(Player player) {
        this.player = player;
    }

    @Override
    public void load(double width, double height) {
        Button saveButton = new Button("Sauvegarder");
        Button loadButton = new Button("Charger");

        IObjectSerializationManager objectSerializationManager = new SimpleObjectSerializationManager();

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<SerializablePokemon> serializablePokemons = new ArrayList<>();

                for(Pokemon pokemon : player.getPokemonList()) {
                    SerializablePokemon serializablePokemon = new SerializablePokemon(pokemon);
                    serializablePokemons.add(serializablePokemon);
                }

                objectSerializationManager.writeObjectFromFile(Constantes.SAVE_FILE_PATH, serializablePokemons);
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
            }
        });

        eventManager.add(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ESCAPE:
                        SceneManager.setScene("WorldScene");
                        return;
                }
            }
        }, EEventType.KEYBOARD_PRESSED);

        gridPane.add(saveButton, 0, 1);
        gridPane.add(loadButton, 0, 2);

        Scene scene = new Scene(gridPane, width, height);

        eventManager.attachAllEventsToScene(scene);

        setScene(scene);
    }
}
