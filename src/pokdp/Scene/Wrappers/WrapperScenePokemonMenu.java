package pokdp.Scene.Wrappers;

import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.AScene;

import java.util.List;

public abstract class WrapperScenePokemonMenu extends AScene {
    public abstract void setPokemonList(List<Pokemon> pokemonList);
}
