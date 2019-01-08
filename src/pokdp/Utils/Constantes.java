package pokdp.Utils;

import com.sun.org.apache.xpath.internal.operations.Bool;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.LoadPokemon.PokemonLoaderXML;
import pokdp.Scene.SceneManager;

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
    public static final double PROBA_COMBAT = 0.02f;
    public static final int DIFFICULTY = 2;

    public static final String DEFAULT_BUTTON = "        -fx-background-color:"+
            "      linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),"+
            "              linear-gradient(#020b02, #3a3a3a),"+
            "              linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),"+
            "              linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);"+
            "      -fx-background-insets: 0,1,4,5;"+
            "      -fx-background-radius: 9,8,5,4;"+
            "      -fx-padding: 15 30 15 30;"+
            "      -fx-font-size: 18px;"+
            "      -fx-text-fill: #333333;"+
            "      -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);";
    public static final int NUMBER_STARTER = 3;


    public static final String STARTERSCENE_NAME = "StarterScene";
    public static final String POKEMONMENU_NAME = "PokemonMenu";
    public static final String WORLDSCENE_NAME = "WorldScene";
    public static final String COMBATSCENE_NAME = "CombatScene";
    public static final String VICTORYSCENE_NAME = "VictoryScene";
    public static final String POKEDEXSCENE_NAME = "PokedexScene";
    public static final String PAUSESCENE_NAME = "PauseScene";

    public static final String SAVE_FILE_PATH = "filesave1.pkdp";

    public static final Boolean CHEAT_ON = true;

    public static HashMap<String, Pokemon> pokemonHashMap = PokemonLoaderXML.load("assets/pokemons/list.xml");
}
