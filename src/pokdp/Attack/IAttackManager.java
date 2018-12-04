package pokdp.Attack;

import pokdp.Entity.Pokemon.Pokemon;

public interface IAttackManager {
    int calculateDamage(Pokemon defender, Pokemon attacker);
}
