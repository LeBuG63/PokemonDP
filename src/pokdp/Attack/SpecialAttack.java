package Attack;

import Entity.Pokemon.Pokemon;
import Type.EType;

import java.util.Random;

public class SpecialAttack extends Attack implements IDamageResolver {

    private final static int SP_ATK = 3;
    private final static int SP_DEF = 4;

    public SpecialAttack(String name, String description, int basePower, EType type) {
        super(name, description, basePower, type);
    }

    @Override
    public int getDamageDone(Pokemon attacker, Pokemon defender) {
        Random rnd = new Random();
        int damage = ((((2*attacker.getLevel())/5)+2)*basePower*(attacker.getStatFromArray(SP_ATK)/defender.getStatFromArray(SP_DEF)))/50+2;
        damage *= (rnd.nextInt(20)+80)/100;
        return damage;
    }

}
