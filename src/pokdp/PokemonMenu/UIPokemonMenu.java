package pokdp.PokemonMenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.server.ExportException;

public class UIPokemonMenu  {
    private static Parent root;
    private static Scene scene;
    private static UIPokemonMenu p;

    public UIPokemonMenu(){
        try{
            MenuController m = new MenuController();
            FXMLLoader fxmlLoader = new FXMLLoader();
            FileInputStream fxmlStream = new FileInputStream("src/pokdp/PokemonMenu/PokemonMenu.fxml");
            Parent root2 = (Parent)fxmlLoader.load(fxmlStream);

            m.initialize();
            scene = new Scene(root2,1600,900);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void launch(Stage stage){
        p = new UIPokemonMenu();
        stage.setScene(scene);
        stage.show();
    }
}
