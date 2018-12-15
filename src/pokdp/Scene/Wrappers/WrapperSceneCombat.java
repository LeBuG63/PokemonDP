package pokdp.Scene.Wrappers;

import pokdp.Entity.Player.Player;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Scene.AScene;

public abstract class WrapperSceneCombat extends AScene {
    public abstract void setAttributes(Player player, Pokemon pok1, Pokemon pok2);
}
