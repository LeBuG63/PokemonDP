package pokdp.Entity.Player;

import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Utils.Constantes;

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

    /**
     * verifie si le combat doit etre lance ou non
     * @return un boolean
     */
    public boolean roll() {
        //CombatSceneSimple.launch(primaryStage, this, getPokemon(), Constantes.pokemonHashMap.get("Tauros"));
        return Math.random() < Constantes.PROBA_COMBAT;

    }
}
