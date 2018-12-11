package pokdp.Entity.Pokemon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pokdp.Entity.*;
import pokdp.Type.EType;

public class Pokemon extends AEntity {

    private final int PV     = 0;

    private StringProperty NameProperty = new SimpleStringProperty();
    private IntegerProperty HpProperty = new SimpleIntegerProperty();
    private IntegerProperty LevelProperty = new SimpleIntegerProperty();
    private String spritePath;
    //private int level;

    private int[] arrBaseStats , arrEV , arrIV, currentStats;

    private EType type;

    public Pokemon(String name, String spritePath, int[] arrBaseStats , int[] arrEV , int[] arrIV, int level ,EType type) throws IllegalArgumentException{
        super(EEntityType.POKEMON, false);
        if(arrBaseStats.length == 6 && arrEV.length == 6 && arrIV.length == 6) {
            this.spritePath = spritePath;
            this.NameProperty.setValue(name);
            this.arrBaseStats = arrBaseStats;
            this.arrEV = arrEV;
            this.arrIV = arrIV;
            currentStats = new int[6];
            this.LevelProperty.setValue(level);
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
        int level = getLevel();

        this.currentStats[PV] = (((2*arrBaseStats[PV]+arrIV[PV]+(arrEV[PV]/4))*level/100)+level+10);
        for(int i = 1 ; i < 6 ; i++)
              this.currentStats[i] = ((2*arrBaseStats[i]+arrIV[i]+(arrEV[i]/4))*level/100)+5;
    }

    public String getName() {
        return NameProperty.getValue();
    }
    public StringProperty getNameProperty() {return NameProperty; }
    public int getLevel(){
        return LevelProperty.getValue();
    }
    public IntegerProperty getLevelProperty () { return LevelProperty; }
    /**
     * recuperer le status à l'index
     * @param index index à récuperer
     * @return le status actuel
     */
    public int getStatFromArray(int index){
        return currentStats[index];
    }

    public void setPV(int hp) {
        currentStats[PV] = hp;
        HpProperty.setValue(hp);
    }

    public int getPV() {
        return HpProperty.getValue();
    }

    public IntegerProperty getHpProperty() {
        return HpProperty;
    }

    public String getSpriteURL() {
        return spritePath;
    }

    /**
     * Fait gagner un niveau au pokemon et reactualise ses statistiques
     */
    public void levelUp(){
        LevelProperty.setValue(getLevel() + 1);
        setCurrentStats();
    }

}