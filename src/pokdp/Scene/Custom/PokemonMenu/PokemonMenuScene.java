package pokdp.Scene.Custom.PokemonMenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.Wrappers.WrapperScenePokemonMenu;

import java.io.FileInputStream;
import java.util.List;

public class PokemonMenuScene extends WrapperScenePokemonMenu {
    private GridPane root = new GridPane();
    private List<Pokemon> listPoke;
    private PokemonMenuController mpc;

    private Player player;

    public PokemonMenuScene() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            FileInputStream fxmlStream = new FileInputStream("src/pokdp/Scene/Custom/PokemonMenu/PokemonMenu.fxml");

            root = fxmlLoader.load(fxmlStream);
            mpc = fxmlLoader.getController();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void load(double width, double height) {
        setScene(new Scene(root));
    }

    public void setAttributes(Player player) {
        setPlayer(player);
        setPokemonList(player.getPokemonList());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setPokemonList(List<Pokemon> pokemonList) {
        this.listPoke = pokemonList;
        loadPokemonIntoRoot();
    }

    public void loadPokemonIntoRoot() {
        mpc.setPlayer(player);
        mpc.setListPokeView(listPoke);
    }
}
