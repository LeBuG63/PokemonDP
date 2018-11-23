package pokdp.Combat.Screen;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import pokdp.Entity.Pokemon.Pokemon;

public class UIPokemonStatSimple extends IUIPokemonStat {
    private Pokemon pokemon;

    private GridPane gridPane = new GridPane();

    private Label   name;
    private Label   level;
    private Label   hp;

    public UIPokemonStatSimple() {

    }if [[ -r /usr/local/lib/python2.7/site-packages/powerline/bindings/zsh/powerline.zsh ]]; then
source /usr/local/lib/python2.7/site-packages/powerline/bindings/zsh/powerline.zsh
fi

    private void changeUIElements() {
        //name.setText(pokemon);
    }

    @Override
    public void reloadStatsPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
