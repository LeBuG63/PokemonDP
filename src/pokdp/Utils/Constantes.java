package pokdp.Utils;

import pokdp.Entity.Pokemon.Pokemon;
import pokdp.LoadPokemon.PokemonLoaderXML;

import java.util.HashMap;

public class Constantes {
    public static final int DEFAULT_SPRITE_WIDTH = 64;
    public static final int DEFAULT_SPRITE_HEIGHT = 64;
    public static final int DEFAULT_TILE_MAP_WIDTH = 32;
    public static final int DEFAULT_TILE_MAP_HEIGHT = 32;
    public static final int DEFAULT_FENCE_WIDTH = 64;
    public static final int DEFAULT_FENCE_HEIGHT = 64;
    public static final double PROBA_DECO = 0.95f;
    public static final double HITBOX_MARGIN = 20.0f;
    //public static final double PROBA_COMBAT = 0.03f;
    public static final double PROBA_COMBAT = 0.02f;
    public static final int DIFFICULTY = 2;

    public static HashMap<String, Pokemon> pokemonHashMap = PokemonLoaderXML.load("assets/pokemons/list.xml");
}
