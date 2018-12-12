package pokdp.PokemonMenu;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pokdp.Entity.Pokemon.Pokemon;

import java.util.List;

public class MenuController {
    @FXML private StringProperty pokeName = new SimpleStringProperty();
    @FXML private Label labelName1;
    @FXML private Label labelName2;
    @FXML private Label labelName3;
    @FXML private Label labelName4;
    @FXML private Label labelName5;
    @FXML private Label labelName6;

    @FXML
    public final void setPokeName(String name){
        pokeName.setValue(name);
    }

    public MenuController(){
    }

    @FXML
    public void initialize(){
        labelName1.setText("deafa");
        labelName2.setText("dexaaaaaaaaaae");
        labelName3.setText("dexxxxxe");
        labelName4.setText("dexxsxsxse");
        labelName5.setText("dexqsce");
        labelName6.setText("dexzsxsxse");
    }
}
