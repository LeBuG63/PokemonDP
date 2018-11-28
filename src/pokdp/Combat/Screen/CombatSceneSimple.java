package pokdp.Combat.Screen;

import pokdp.Combat.Screen.ICombatScene;
import pokdp.Entity.Pokemon.Pokemon;

public class CombatSceneSimple extends ICombatScene {
    public CombatSceneSimple(Pokemon player, Pokemon enemy) {
        super(player, enemy);
    }
}
