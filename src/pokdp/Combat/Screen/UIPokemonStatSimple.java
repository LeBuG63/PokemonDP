package pokdp.Combat.Screen;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import pokdp.Entity.Pokemon.Pokemon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UIPokemonStatSimple extends AUIPokemonStat {
    private Label name = new Label();
    private Label level = new Label();
    private Label hp = new Label();

    public UIPokemonStatSimple(Pokemon pokemon, Pokemon enemy) {
        setPokemonImageView(new ImageView(new Image(pokemon.getSpriteURL(),  300, 300, false, false)));

        Label strLevel = new Label("Niveau ");
        Label strHp = new Label("Vie ");

        try {
            Font font = Font.loadFont(new FileInputStream(new File("assets/fonts/pokemon.ttf")), 15);

            strHp.setFont(font);
            strLevel.setFont(font);
            name.setFont(font);
            level.setFont(font);
            hp.setFont(font);
        }
        catch(FileNotFoundException e) {
            System.out.println("font not found");
        }

        getGridPane().add(getPokemonImageView(), 2, 0);
        getGridPane().add(name, 0, 0);
        getGridPane().add(level, 4, 0);
        getGridPane().add(strLevel, 3, 0);
        getGridPane().add(strHp, 3, 2);
        getGridPane().add(hp, 4, 2);

        name.textProperty().bind(pokemon.getNameProperty());
        level.textProperty().bind(pokemon.getLevelProperty().asString());
        hp.textProperty().bind(pokemon.getHpProperty().asString());

        int i = 4;
    }
}
