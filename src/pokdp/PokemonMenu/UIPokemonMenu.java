package pokdp.PokemonMenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.EventManager.EventManager;
import pokdp.Scene.AScene;

import java.io.FileInputStream;
import java.util.List;

public class UIPokemonMenu extends AScene {
    private Stage    primaryStage;
    private GridPane root;
    private List<Pokemon> listPoke;


    @Override
    public void load(double width, double height) {
        EventManager eventManager = new EventManager();
        setScene(new Scene(root,1600,900));
    }

    public UIPokemonMenu(Stage primaryStage,List<Pokemon> listPoke){
        super();
        this.primaryStage = primaryStage;
        this.listPoke = listPoke;
        try {
            PokemonMenuController mpc;

            FXMLLoader fxmlLoader = new FXMLLoader();
            FileInputStream fxmlStream = new FileInputStream("src/pokdp/PokemonMenu/PokemonMenu.fxml");
            root = fxmlLoader.load(fxmlStream);

            mpc = fxmlLoader.getController();
            mpc.setListPokeView(listPoke);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
