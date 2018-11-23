package pokdp.Combat.Screen;

import javafx.scene.layout.GridPane;

import pokdp.Entity.Pokemon.Pokemon;

public class IUIPokemonStat {
    private GridPane root = new GridPane();

    public IUIPokemonStat(Pokemon pokemon) {

    }

    public GridPane getRoot() {
        return root;
    }
}
