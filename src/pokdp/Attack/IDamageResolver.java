package Attack;

import Entity.Pokemon.Pokemon;

public interface IDamageResolver {
    public int getDamageDone(Pokemon attacker, Pokemon defender);
}
