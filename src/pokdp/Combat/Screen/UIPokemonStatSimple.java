package pokdp.Combat.Screen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pokdp.Attack.Attack;
import pokdp.Entity.Pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class UIPokemonStatSimple extends AUIPokemonStat {
    private Label name = new Label();
    private Label level = new Label();
    private Label hp = new Label();

    public UIPokemonStatSimple(Pokemon pokemon, Pokemon enemy) {
        setPokemonImageView(new ImageView(new Image(pokemon.getSpriteURL(),  300, 300, false, false)));

        getGridPane().add(getPokemonImageView(), 2, 0);
        getGridPane().add(name, 0, 0);
        getGridPane().add(level, 1, 0);
        getGridPane().add(hp, 3, 2);

        name.textProperty().bind(pokemon.getNameProperty());
        level.textProperty().bind(pokemon.getLevelProperty().asString());
        hp.textProperty().bind(pokemon.getHpProperty().asString());

        int i = 4;
    }
}
