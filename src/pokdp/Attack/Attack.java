package pokdp.Attack;

import pokdp.Entity.Pokemon.Pokemon;

public class Attack{
    private int basePower;
    private boolean isSpecial;

    public Attack(int basePower, boolean isSpecial) {
        this.basePower = basePower;
        this.isSpecial = isSpecial;
    }
}
