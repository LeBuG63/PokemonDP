package pokdp.PokemonMenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.EventManager.EventManager;
import pokdp.Scene.AScene;
import pokdp.Scene.Wrappers.WrapperScenePokemonMenu;

import java.io.FileInputStream;
import java.util.List;

public class UIPokemonMenu extends WrapperScenePokemonMenu {
    private GridPane root = new GridPane();
    private List<Pokemon> listPoke;
    private PokemonMenuController mpc;

    public UIPokemonMenu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            FileInputStream fxmlStream = new FileInputStream("src/pokdp/PokemonMenu/PokemonMenu.fxml");

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

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.listPoke = pokemonList;
        loadPokemonIntoRoot();
    }

    public void loadPokemonIntoRoot() {
        mpc.setListPokeView(listPoke);
    }
}
