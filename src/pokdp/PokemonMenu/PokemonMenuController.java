package pokdp.PokemonMenu;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.SceneManager;

import java.util.ArrayList;
import java.util.List;

public class PokemonMenuController{
    @FXML
    private ListView<String> listPokeView;

    private List<Pokemon> listPokemon;

    ListProperty<String>   listP;

    public PokemonMenuController(){
    }

    @FXML
    public void initialize(){
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
        listPokemon.set(0,listPokemon.get(listPokeView.getSelectionModel().getSelectedIndex()));
        listPokemon.set(listPokeView.getSelectionModel().getSelectedIndex(),oldHeadPokemon);
        setListPokeView(listPokemon);
        Player.setNewPokemonOrder(listPokemon);
        SceneManager.setScene("WorldScene");
    }
}
