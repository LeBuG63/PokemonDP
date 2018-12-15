package pokdp.Combat.Screen;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import pokdp.Entity.Pokemon.Pokemon;

abstract class AUIPokemonStat extends Parent {
    private ImageView sprite;
    private GridPane gridPane = new GridPane();

    AUIPokemonStat() {
        this.getChildren().add(gridPane);
    }

    /**
     * recharge les modifications apport&eacute; &agrave; un pokemon
     * @param pokemon
     */

    public ImageView getPokemonImageView() {
        return sprite;
    }

    public void setPokemonImageView(ImageView imageView) {
        sprite = imageView;
    }

    GridPane getGridPane() {
        return gridPane;
    }
}
