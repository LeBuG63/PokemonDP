package pokdp.Combat;

import pokdp.Entity.Pokemon.Pokemon;

public class SimpleCombatManager extends ACombatManager {
    public ECombatRules checkRules(Pokemon player, Pokemon enemy) {
        if(player.getPV() <= 0) {
            return ECombatRules.PLAYER_DEAD;
        }
        if(enemy.getPV() <= 0) {
            return ECombatRules.ENEMY_DEAD;
        }

        return ECombatRules.NONE;
    }
}
