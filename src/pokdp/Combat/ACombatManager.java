package pokdp.Combat;

import pokdp.Entity.Pokemon.Pokemon;

public abstract class ACombatManager {
    public abstract ECombatRules checkRules(Pokemon player, Pokemon enemy);
}
