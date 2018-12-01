package pokdp.LoadPokemon;

import org.w3c.dom.Element;
import pokdp.Entity.Pokemon.Pokemon;

import java.util.List;

public interface IPokemonLoader {
    List<Pokemon> load(String path);
}
