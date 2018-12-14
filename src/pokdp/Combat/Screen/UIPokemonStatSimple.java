package pokdp.Combat.Screen;

<<<<<<< HEAD
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import pokdp.Entity.Pokemon.Pokemon;

public class UIPokemonStatSimple extends IUIPokemonStat {
=======
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
>>>>>>> f0491ba07135180a4bad065f2686ab0e7d84ee94
    private Label name = new Label();
    private Label level = new Label();
    private Label hp = new Label();

<<<<<<< HEAD
    public UIPokemonStatSimple(Pokemon pokemon) {
        reloadStatsPokemon(pokemon);

        getGridPane().add(name, 0, 0);
        getGridPane().add(level, 1, 0);
        getGridPane().add(hp, 3, 1);
/*
        name.textProperty().bindBidirectional(new SimpleStringProperty(pokemon.getName()));
        level.textProperty().bindBidirectional(new SimpleIntegerProperty(pokemon.getLevel()).asString());
        hp.textProperty().bindBidirectional(new SimpleIntegerProperty(pokemon.getPV()).asString());
  */
    }

    @Override
    protected void reloadStatsPokemon(Pokemon pokemon) {
        name.setText(pokemon.getName());
        level.setText(Integer.toString(pokemon.getLevel()));
        hp.setText(Integer.toString(pokemon.getPV()));
=======
    private List<Button> buttonList = new ArrayList<>();

    private ImageView sprite;

    public UIPokemonStatSimple(Pokemon pokemon, Pokemon enemy) {
        sprite = new ImageView(new Image(pokemon.getSpriteURL(),  300, 300, false, false));

        getGridPane().add(sprite, 2, 0);
        getGridPane().add(name, 0, 0);
        getGridPane().add(level, 1, 0);
        getGridPane().add(hp, 3, 1);

        name.textProperty().bind(pokemon.getNameProperty());
        level.textProperty().bind(pokemon.getLevelProperty().asString());
        hp.textProperty().bind(pokemon.getHpProperty().asString());

        int i = 4;

        for(Attack attack : pokemon.getAttacks()) {
            Button button = new Button(attack.getName());

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    int damage = attack.calculateDamage(pokemon, enemy);
                    enemy.setPV(enemy.getPV() - damage);
                }
            });

            getGridPane().add(button, i++, 0);
            buttonList.add(button);
        }
>>>>>>> f0491ba07135180a4bad065f2686ab0e7d84ee94
    }
}
