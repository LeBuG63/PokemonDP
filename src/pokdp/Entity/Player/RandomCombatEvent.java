package pokdp.Entity.Player;

import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.SceneManager;
import pokdp.Utils.Constantes;

import java.util.Random;

public class RandomCombatEvent {
    private Pokemon pokemon1;
    private Pokemon pokemon2;

    private Player player;

    public RandomCombatEvent(Player actplayer) {
        player = actplayer;
    }

    public void setPokemonPlayer(Pokemon pok) {
        pokemon1 = pok;
    }

    public void setPokemonEnemy(Pokemon pok) {
        pokemon2 = pok;
    }

    public boolean roll() {
        if(Math.random() < Constantes.PROBA_COMBAT) {
            return true;
            //CombatSceneSimple.launch(primaryStage, this, getPokemon(), Constantes.pokemonHashMap.get("Tauros"));
        }

        return false;
    }
}
