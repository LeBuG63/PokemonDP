package pokdp.PokemonMenu;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Type.EType;

import java.util.ArrayList;
import java.util.List;

public class PokemonMenuController {

    @FXML
    private ArrayList<Label> labelNameList;

    @FXML
    private List<SimpleStringProperty> sspList = new ArrayList<>();

    @FXML
    private List<SimpleIntegerProperty> ssiList = new ArrayList<>();

    private List<Pokemon> listPoke;

    public PokemonMenuController(){
    }

    @FXML
    public void initialize() {
        listPoke = new ArrayList<>();
        listPoke.add(new Pokemon("oieoieo",null,new int[]{1,1,1,1,1,1},new int[]{1,1,1,1,1,1},new int[]{1,1,1,1,1,1},100, EType.FEU));
        listPoke.add(new Pokemon("idejie",null,new int[]{1,1,1,1,1,1},new int[]{1,1,1,1,1,1},new int[]{1,1,1,1,1,1},100, EType.FEU));
        listPoke.add(new Pokemon("zzzzzs",null,new int[]{1,1,1,1,1,1},new int[]{1,1,1,1,1,1},new int[]{1,1,1,1,1,1},100, EType.FEU));
        listPoke.add(new Pokemon("bvnjd",null,new int[]{1,1,1,1,1,1},new int[]{1,1,1,1,1,1},new int[]{1,1,1,1,1,1},100, EType.FEU));
        listPoke.add(new Pokemon("oszszsz",null,new int[]{1,1,1,1,1,1},new int[]{1,1,1,1,1,1},new int[]{1,1,1,1,1,1},100, EType.FEU));
        setLabels();
    }


    private void setLabels(){
        for(int i = 0 ; i < listPoke.size() ; i ++){
            setName(i);
            setLevel(i);
            setHP(i);
        }
    }

    private void setName(int index){
        sspList.add(new SimpleStringProperty());
        sspList.get(index).setValue(listPoke.get(index).getName());
        labelNameList.get((3*index)).textProperty().bind(sspList.get(index));
    }

    private void setLevel(int index){
        ssiList.add(new SimpleIntegerProperty());
        ssiList.get((2*index)).setValue(listPoke.get(index).getLevel());
        labelNameList.get(1+(3*index)).textProperty().bind(ssiList.get(2*index).asString());
    }

    private void setHP(int index){
        ssiList.add(new SimpleIntegerProperty());
        ssiList.get(1+(2*index)).setValue(listPoke.get(index).getPV());
        labelNameList.get(2+(3*index)).textProperty().bind(ssiList.get(1+(2*index)).asString());
    }

}
