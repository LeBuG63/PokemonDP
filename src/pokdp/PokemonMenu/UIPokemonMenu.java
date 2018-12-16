package pokdp.PokemonMenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class UIPokemonMenu {

    private static UIPokemonMenu p;
    private static Parent root;
    private static Scene scene;


    public UIPokemonMenu(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            FileInputStream fxmlStream = new FileInputStream("src/pokdp/PokemonMenu/PokemonMenu.fxml");
            root = fxmlLoader.load(fxmlStream);
            scene = new Scene(root,1600,900);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    public static void launch(Stage primaryStage){
        p = new UIPokemonMenu();
        primaryStage.setTitle("SelectMenu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
