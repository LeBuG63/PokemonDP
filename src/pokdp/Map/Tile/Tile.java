package pokdp.Map.Tile;

import pokdp.AnimationManager.AnimationManagerSprite;
import pokdp.Entity.AEntity;
import pokdp.Entity.EEntityType;
import pokdp.Utils.Constantes;
import javafx.animation.Animation;

import java.util.List;

<<<<<<< HEAD
public class Tile extends IEntity {
=======
public class Tile extends AEntity {
>>>>>>> f0491ba07135180a4bad065f2686ab0e7d84ee94
    private AnimationManagerSprite animationManager = new AnimationManagerSprite();

    public Tile(List<String> pathList) {
        super(EEntityType.TERRAIN, AEntity.HAS_NO_COLLISION);

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
