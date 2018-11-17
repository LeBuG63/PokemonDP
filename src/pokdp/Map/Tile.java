package Map;

import AnimationManager.AnimationManagerSprite;
import AnimationManager.IAnimationManager;
import Entity.EEntityType;
import Entity.IEntity;
import javafx.animation.Animation;
import pokdp.Constantes;

import java.util.List;

public class Tile extends IEntity {
    IAnimationManager animationManager = new AnimationManagerSprite();

    public Tile(List<String> pathList) {
        super(EEntityType.TERRAIN);

        if(pathList != null) {
            for (String path : pathList) {
                animationManager.addFrame(path, Constantes.DEFAULT_TILE_MAP_WIDTH, Constantes.DEFAULT_TILE_MAP_HEIGHT);
            }

            setSprite(animationManager.getFrame(0));
        }
        //animationManager.setTimeline(this, 1000, Animation.INDEFINITE);
    }

    public void update() {

    }
}
