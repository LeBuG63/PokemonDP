package pokdp.Combat.Screen;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pokdp.Entity.Pokemon.Pokemon;

public class UIPokemonStatSimple extends AUIPokemonStat {
    private Label name = new Label();
    private Label level = new Label();
    private Label hp = new Label();

    private ImageView sprite;

    public UIPokemonStatSimple(Pokemon pokemon) {

        sprite = new ImageView(new Image(pokemon.getSpriteURL(),  300, 300, false, false));


        getGridPane().add(sprite, 2, 0);
        getGridPane().add(name, 0, 0);
        getGridPane().add(level, 1, 0);
        getGridPane().add(hp, 3, 1);


        name.textProperty().bind(pokemon.getNameProperty());
        level.textProperty().bind(pokemon.getLevelProperty().asString());
        hp.textProperty().bind(pokemon.getHpProperty().asString());
    }
}
