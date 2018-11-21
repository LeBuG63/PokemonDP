package Attack;

import Type.EType;

public abstract  class Attack {
    private String name;

    private String description;

    private EType type;

    protected int basePower;

    public Attack(String name, String description, int basePower, EType type){
        this.name = name;
        this.description = description;
        this.basePower = basePower;
        this.type = type;
    }

    public int getBasePower() {
        return basePower;
    }

}
