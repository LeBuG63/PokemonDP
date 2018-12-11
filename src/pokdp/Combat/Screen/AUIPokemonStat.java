package pokdp.Combat.Screen;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import pokdp.Entity.Pokemon.Pokemon;

abstract class AUIPokemonStat extends Parent {
    private Pokemon pokemon;

    private GridPane gridPane = new GridPane();

    AUIPokemonStat() {
        this.getChildren().add(gridPane);
    }

    /**
     * recharge les modifications apport&eacute; &agrave; un pokemon
     * @param pokemon
     */

    GridPane getGridPane() {
        return gridPane;
    }
}
