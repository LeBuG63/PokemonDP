package pokdp.Combat.Screen;

import javafx.scene.control.Label;
import pokdp.Entity.Pokemon.Pokemon;

public class UIPokemonStatSimple extends IUIPokemonStat {
    private Label name = new Label();
    private Label level = new Label();
    private Label hp = new Label();

    public UIPokemonStatSimple(Pokemon pokemon) {
        reloadStatsPokemon(pokemon);

        getGridPane().add(name, 0, 0);
        getGridPane().add(level, 1, 0);
        getGridPane().add(hp, 2, 0);
    }

    @Override
    protected void reloadStatsPokemon(Pokemon pokemon) {
        name.setText(pokemon.getName());
        level.setText(Integer.toString(pokemon.getLevel()));
    }
}
