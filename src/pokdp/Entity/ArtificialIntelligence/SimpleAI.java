package pokdp.Entity.ArtificialIntelligence;

import pokdp.Attack.Attack;
import pokdp.Entity.Pokemon.Pokemon;

import java.util.Random;

public class SimpleAI extends AArtificialIntelligenceManager {
    public EEntityHurted performAction(Pokemon attacker, Pokemon victim, EAiType aiType) {
        Random random = new Random();

        int def = random.nextInt(100);

        if(def < 30) {
            attacker.setDefense(true);
        }

        if(attacker.isDefending()) {
            return EEntityHurted.POKEMON_ATTACKER_DEF;
        }

        int iAttack = random.nextInt(attacker.getAttacks().size());
        Attack attack = attacker.getAttacks().get(iAttack);

        setSelectedAttack(attack);

        if(victim.isDefending()) {
            victim.setDefense(false);
            return EEntityHurted.POKEMON_VICTIM_MISSED;
        }

        int damage = attack.calculateDamage(victim, attacker);
        victim.setPV(victim.getPV() - damage);

        return EEntityHurted.POKEMON_VICTIM;
    }
}
