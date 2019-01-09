package pokdp.Stream.Serialization;

import pokdp.Entity.Pokemon.Pokemon;

import java.io.Serializable;

public class SerializablePokemon implements Serializable {
    private String name;
    private int level;

    public SerializablePokemon(Pokemon pokemon) {
        name = pokemon.getName();
        level = pokemon.getLevel();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
