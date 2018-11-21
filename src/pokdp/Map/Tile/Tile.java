package Map.Tile;

import AnimationManager.AnimationManagerSprite;
import Entity.EEntityType;
import Entity.IEntity;
import Utils.Constantes;
import javafx.animation.Animation;

import java.util.List;

public class Tile extends IEntity {
    AnimationManagerSprite animationManager = new AnimationManagerSprite();

    public Tile(List<String> pathList) {
        super(EEntityType.TERRAIN, IEntity.HAS_NO_COLLISION);

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
}
