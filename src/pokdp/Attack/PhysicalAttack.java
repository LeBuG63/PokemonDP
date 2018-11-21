package Attack;

import Entity.Pokemon.Pokemon;
import Type.EType;

import java.util.Random;

public class PhysicalAttack extends Attack implements IDamageResolver{

    private static final int ATK = 1;
    private static final int DEF = 2;

    public PhysicalAttack(String name, String description, int basePower, EType type){
        super(name,description,basePower,type);
    }

    @Override
    public int getDamageDone(Pokemon attacker, Pokemon defender) {
        Random rnd = new Random();
        int damage = ((((2*attacker.getLevel())/5)+2)*basePower*(attacker.getStatFromArray(ATK)/defender.getStatFromArray(DEF)))/50+2;
        damage *= (rnd.nextInt(20)+80)/100;
        return damage;
    }
}