package pokdp.Entity.Pokemon;


import pokdp.Entity.*;
import pokdp.Type.EType;

public class Pokemon extends IEntity {

    private final int PV     = 0;

    private String name;

    private int level;

    private int[] arrBaseStats , arrEV , arrIV, currentStats;

    private EType type;

    public Pokemon(String name , int[] arrBaseStats , int[] arrEV , int[] arrIV, int level ,EType type) throws IllegalArgumentException{
        super(EEntityType.POKEMON, false);
        if(arrBaseStats.length == 6 && arrEV.length == 6 && arrIV.length == 6) {
            this.name = name;
            this.arrBaseStats = arrBaseStats;
            this.arrEV = arrEV;
            this.arrIV = arrIV;
            currentStats = new int[6];
            this.level = level;
            this.type = type;
            setCurrentStats();
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Calcule les attributs d'un pokemon
     */
    private void setCurrentStats() {
        this.currentStats[PV] = (((2*arrBaseStats[PV]+arrIV[PV]+(arrEV[PV]/4))*level/100)+level+10);
        for(int i = 1 ; i < 6 ; i++)
              this.currentStats[i] = ((2*arrBaseStats[i]+arrIV[i]+(arrEV[i]/4))*level/100)+5;
    }

    public String getName() {
        return name;
    }

    public int getLevel(){
        return level;
    }

    /**
     * recuperer le status à l'index
     * @param index index à récuperer
     * @return le status actuel
     */
    public int getStatFromArray(int index){
        return currentStats[index];
    }


    /**
     * Fait gagner un niveau au pokemon et reactualise ses statistiques
     */
    public void lZevelUp(){
        level++;
        setCurrentStats();
    }

}