package pokdp.Scene.Custom.PokemonMenu;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.SceneManager;
import pokdp.Utils.Constantes;
import pokdp.Utils.ScreenComponent.Button.ClickableImageView;

import java.util.ArrayList;
import java.util.List;

public class PokemonMenuController{

    @FXML
    private ListView<GridPane> listPokeView;


    private List<Pokemon> listPokemon;

    private ListProperty<GridPane>   listP;
    private Player player;

    public PokemonMenuController(){
    }

    @FXML
    public void initialize(){
    }

    public void setListSize(double width, double height){
        listPokeView.setPrefSize(width, height);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @FXML
    public void setListPokeView(List<Pokemon> listPokemon){
        int  lineLimit = Constantes.MENU_LIMIT;
        boolean hasBeenInterrupted = false;

        this.listPokemon = listPokemon;
        listP = new SimpleListProperty<>();
        List<GridPane> listPanePokemon = new ArrayList<>();

        if(lineLimit > listPokemon.size()){
            lineLimit = listPokemon.size();
        }

        for(int i = 0 ; i < listPokemon.size() ; i+=lineLimit) {
            if(hasBeenInterrupted) {
                break;
            }

            GridPane g = new GridPane();


            for(int pokemonOnLine = 0 ; pokemonOnLine < lineLimit; pokemonOnLine++){
                if(i+pokemonOnLine > listPokemon.size()-1){
                    hasBeenInterrupted = true;
                    break;
                }

                GridPane pokemonPane = new GridPane();

                Pokemon p = listPokemon.get(i+pokemonOnLine);
                Button btn = new ClickableImageView(p.getSpriteURL());

                btn.setOnAction(actionEvent -> setHeadPokemon(p));

                pokemonPane.add(btn,0,0);
                pokemonPane.add(new Label(p.toString()),0,1);
                g.add(pokemonPane,pokemonOnLine,0);
            }
            listPanePokemon.add(g);
        }
        listP.setValue(FXCollections.observableArrayList(listPanePokemon));
        listPokeView.itemsProperty().bind(listP);
    }

    private void setHeadPokemon(Pokemon pokemon){
        if(listPokeView.getItems().size() == 0)
            return;
        int oldIndex = listPokemon.indexOf(pokemon);
        Pokemon oldHeadPokemon = listPokemon.get(0);
        listPokemon.set(0,pokemon);
        listPokemon.set(oldIndex,oldHeadPokemon);
        setListPokeView(listPokemon);
        player.setNewPokemonOrder(listPokemon);
        SceneManager.setLastScene(player);

    }
}
