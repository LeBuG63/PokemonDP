package pokdp.Utils;

import pokdp.Entity.Pokemon.Pokemon;
import pokdp.LoadPokemon.PokemonLoaderXML;

import java.util.HashMap;

public class Constantes {
    public static final int DEFAULT_SPRITE_WIDTH = 64;
    public static final int DEFAULT_SPRITE_HEIGHT = 64;
    public static final int DEFAULT_TILE_MAP_WIDTH = 32;
    public static final int DEFAULT_TILE_MAP_HEIGHT = 32;
    public static final double PROBA_DECO = 0.95f;
    public static final double HITBOX_MARGIN = 20.0f;
<<<<<<< HEAD
    public static final double PROBA_COMBAT = 0.03f;
=======
    //public static final double PROBA_COMBAT = 0.03f;
    public static final double PROBA_COMBAT = 1.03f;
>>>>>>> f0491ba07135180a4bad065f2686ab0e7d84ee94

    public static HashMap<String, Pokemon> pokemonHashMap = PokemonLoaderXML.load("assets/pokemons/list.xml");
}
