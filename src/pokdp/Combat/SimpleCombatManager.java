package pokdp.Combat;

import pokdp.Entity.Pokemon.Pokemon;

public class SimpleCombatManager extends ACombatManager {
    /**
     * verifie si les regles du combat son respectees
     * @param player    le pokemon du joueur
     * @param enemy     le pokemon ennemi
     * @return          si une des regles est activee ou NONE
     */
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
