package pokdp.Map.Object;

import pokdp.AnimationManager.AnimationManagerSprite;
import pokdp.AnimationManager.AnimationManagerSprite;
import pokdp.Entity.EEntityType;
import pokdp.Entity.IEntity;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class DecoObject extends IEntity {
    private AnimationManagerSprite animationManager = new AnimationManagerSprite();

    public DecoObject(String spritePath, double x, double y, double w, double h, boolean hasCollision) {
        this(new ArrayList<String>() {{ add(spritePath); }}, x, y, w, h, hasCollision);
    }

    public DecoObject(String spritePath, double x, double y, boolean hasCollision) {
        this(new ArrayList<String>() {{ add(spritePath); }}, x, y, hasCollision);
    }

    public DecoObject(List<String> spritePath, double x, double y, double w, double h, boolean hasCollision) {
        this(spritePath, x, y, hasCollision);

        animationManager.setDefaultHeight(h);
        animationManager.setDefaultWidth(w);
    }

    public DecoObject(List<String> spritePath, double x, double y, boolean hasCollision) {
        super(EEntityType.DECO, hasCollision);

        for(String s : spritePath) {
            animationManager.addFrame(s);
        }

        setSprite(animationManager.getFrame(0));
        Image frame = animationManager.getFrame(0);

        setCoordX(x);
        setCoordY(y);
    }

    public DecoObject(List<String> spritePath, double x, double y, CollisionBox collisionBox) {
        this(spritePath, x, y, IEntity.HAS_COLLISION);

        setCollisionObject(collisionBox);
    }

    /**
     * vérifie si deux entités sont en collision
     * @param entity
     * @return
     */
    public boolean isInCollision(IEntity entity) {
        if(!entity.hasCollision()) return false;
        return getCollisionObject().isInCollision(entity.getCollisionObject());
    }
}
