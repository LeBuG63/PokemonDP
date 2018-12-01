package pokdp.Combat.Screen;

import pokdp.Combat.Screen.ICombatScene;
import pokdp.Entity.Pokemon.Pokemon;

public class CombatSceneSimple extends ICombatScene {
    public CombatSceneSimple(Pokemon player, Pokemon enemy, double width, double height) {
        super(player, enemy, width, height);
    }
}
