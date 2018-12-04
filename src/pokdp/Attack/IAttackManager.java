package pokdp.Attack;

import pokdp.Entity.Pokemon.Pokemon;

public interface IAttackManager {
    int calculateDamage(Attack attack,Pokemon defender, Pokemon attacker);
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
