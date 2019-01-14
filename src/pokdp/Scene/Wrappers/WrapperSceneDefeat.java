package pokdp.Scene.Wrappers;

import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.AScene;

import java.util.List;

public abstract class WrapperSceneDefeat extends AScene {
    public abstract void healPokemon(List<Pokemon> listP);
}
