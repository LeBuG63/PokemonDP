package pokdp.Attack;

import pokdp.Entity.Pokemon.Pokemon;

public class Attack implements IAttackManager{
    private int basePower;
    private boolean isSpecial;
    private String name;

    public Attack(String name , int basePower, boolean isSpecial) {
        this.basePower = basePower;
        this.isSpecial = isSpecial;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getBasePower(){
        return basePower;
    }

    public boolean getSpecial(){
        return isSpecial;
    }

    @Override
    public int calculateDamage(Pokemon defender, Pokemon attacker) {
        final int STAT_INDEX = (isSpecial)?5:3;
        return ((((2*attacker.getLevel())/5)+2)*basePower*(attacker.getStatFromArray(STAT_INDEX)/defender.getStatFromArray(STAT_INDEX+2))/50)+2;
    }
}
