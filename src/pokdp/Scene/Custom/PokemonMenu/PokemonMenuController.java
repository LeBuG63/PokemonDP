package pokdp.Scene.Custom.PokemonMenu;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.SceneManager;
import pokdp.Utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class PokemonMenuController{
    @FXML
    private ListView<String> listPokeView;
    private List<Pokemon> listPokemon;

    private ListProperty<String>   listP;
    private Player player;

    public PokemonMenuController(){
    }

    @FXML
    public void initialize(){
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @FXML
    public void setListPokeView(List<Pokemon> listPokemon){
        this.listPokemon = listPokemon;
        listP = new SimpleListProperty<>();
        List<String> listStringPokemon = new ArrayList<>();

        for(int i = 0 ; i < listPokemon.size() ; i ++ ){
            listStringPokemon.add(listPokemon.get(i).toString());
        }

        listP.setValue(FXCollections.observableArrayList(listStringPokemon));
        listPokeView.itemsProperty().bind(listP);
    }

    @FXML
    private void setHeadPokemon(){
        if(listPokeView.getItems().size() == 0)
            return;

        Pokemon oldHeadPokemon = listPokemon.get(0);

        int selectedIndex = listPokeView.getSelectionModel().getSelectedIndex();

        if(selectedIndex < 0) {
            selectedIndex = 0;
        }

        listPokemon.set(0,listPokemon.get(selectedIndex));
        listPokemon.set(selectedIndex,oldHeadPokemon);

        setListPokeView(listPokemon);

        player.setNewPokemonOrder(listPokemon);
        SceneManager.setScene(Constantes.WORLDSCENE_NAME);
    }
}
