package pokdp.Attack;

import pokdp.Entity.Pokemon.Pokemon;

public interface IAttackManager {
<<<<<<< HEAD
    int calculateDamage(Attack attack,Pokemon defender, Pokemon attacker);
=======
    int calculateDamage(Pokemon defender, Pokemon attacker);
>>>>>>> f0491ba07135180a4bad065f2686ab0e7d84ee94
    /*
        public int calculateDamage(Attack attack,Pokemon defender, Pokemon attacker){
            final int STAT_INDEX;
            if(attack.isSpecial)
                STAT_INDEX = 3;
            else
                STAT_INDEX = 1;

        }
     */
}
