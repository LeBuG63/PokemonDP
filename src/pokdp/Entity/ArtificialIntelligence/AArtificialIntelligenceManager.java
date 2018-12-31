package pokdp.Entity.ArtificialIntelligence;

import pokdp.Attack.Attack;
import pokdp.Entity.Pokemon.Pokemon;

public abstract class AArtificialIntelligenceManager {
    private Attack attack = null;

    /**
     * gere l intelligence artificielle d un pokemon
     * @param attacker  le pokemon attaquant
     * @param enemy     le pokemon ennemi
     * @param aiType    le type d intelligence
     * @return          le type de degat subit
     */
    public abstract EEntityHurted performAction(Pokemon attacker, Pokemon enemy, EAiType aiType);

    public void setSelectedAttack(Attack attack) {
        this.attack = attack;
    }

    public Attack getSelectedAttack() {
        return attack;
    }
}
