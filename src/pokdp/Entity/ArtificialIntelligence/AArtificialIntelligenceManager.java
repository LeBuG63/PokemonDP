package pokdp.Entity.ArtificialIntelligence;

import pokdp.Attack.Attack;
import pokdp.Entity.Pokemon.Pokemon;

public abstract class AArtificialIntelligenceManager {
    private Attack attack = null;

    public abstract EEntityHurted performAction(Pokemon attacker, Pokemon enemy, EAiType aiType);

    public void setSelectedAttack(Attack attack) {
        this.attack = attack;
    }

    public Attack getSelectedAttack() {
        return attack;
    }
}
