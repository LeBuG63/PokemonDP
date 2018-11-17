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
    }

    /**
     *
     * @param pathList  la liste des chemins pour les images
     * @param duration  la durée de l'animation, si elle est inférieure à 0 alors la durée sera aléatoire
     */
    public Tile(List<String> pathList, double duration) {
        this(pathList);

        if(duration < 0.0f) {
            duration = 1000.0f + Math.random() * 1000;
        }

        animationManager.setTimeline(this, duration, Animation.INDEFINITE);
    }

    public void update() {

    }
}
