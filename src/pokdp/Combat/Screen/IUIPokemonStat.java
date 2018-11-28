package pokdp.Combat.Screen;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import pokdp.Entity.Pokemon.Pokemon;

abstract class IUIPokemonStat extends Parent {
    private Pokemon pokemon;

    private GridPane gridPane = new GridPane();

    IUIPokemonStat() {
        this.getChildren().add(gridPane);
    }

    /**
     * recharge les modifications apport&eacute; &agrave; un pokemon
     * @param pokemon
     */
    protected abstract void reloadStatsPokemon(Pokemon pokemon);

    GridPane getGridPane() {
        return gridPane;
    }
}
