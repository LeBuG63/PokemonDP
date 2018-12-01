package pokdp.LoadPokemon;

import pokdp.Entity.Pokemon.Pokemon;
import java.util.HashMap;

public interface IPokemonLoader {
    HashMap<String, Pokemon> load(String path);
}
