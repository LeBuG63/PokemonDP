package pokdp.Attack;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Utils.Constantes;

public class Attack implements IAttackManager{
    private int basePower;
    private int accuracy;
    private boolean isSpecial;
    private String name;

    public Attack(String name , int basePower, boolean isSpecial, int accuracy) {
        this.basePower = basePower;
        this.isSpecial = isSpecial;
        this.name = name;
    }

    private int getAccuracy() {
        return accuracy;
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

    /**
     * calcule les degats subits entre l attacker et le defender
     * @param defender pokemon qui se fait attacker
     * @param attacker pokemon qui attaque
     * @return degats subis
     */
    @Override
    public int calculateDamage(Pokemon defender, Pokemon attacker) {
        final int STAT_INDEX = (isSpecial)?3:1;
        return ((((2*attacker.getLevel())/5)+2)*basePower*(attacker.getStatFromArray(STAT_INDEX)/defender.getStatFromArray(STAT_INDEX+1))/50)+2;
    }
}
