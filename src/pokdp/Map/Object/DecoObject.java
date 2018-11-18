package Map.Object;

import AnimationManager.AnimationManagerSprite;
import AnimationManager.IAnimationManager;
import Entity.EEntityType;
import Entity.IEntity;
import EventManager.EEventType;
import com.sun.javafx.geom.Vec2d;
import javafx.animation.Animation;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class DecoObject extends IEntity {
    private IAnimationManager animationManager = new AnimationManagerSprite();

    public DecoObject(String spritePath, double x, double y, double w, double h) {
        this(new ArrayList<String>() {{ add(spritePath); }}, x, y, w, h);
    }

    public DecoObject(String spritePath, double x, double y) {
        this(new ArrayList<String>() {{ add(spritePath); }}, x, y);
    }

    public DecoObject(List<String> spritePath, double x, double y, double w, double h) {
        this(spritePath, x, y);

        animationManager.setDefaultHeight(h);
        animationManager.setDefaultWidth(w);
    }

    public DecoObject(List<String> spritePath, double x, double y) {
        super(EEntityType.DECO);

        for(String s : spritePath) {
            animationManager.addFrame(s);
        }

        setSprite(animationManager.getFrame(0));
        Image frame = animationManager.getFrame(0);
    }

    public DecoObject(List<String> spritePath, double x, double y, CollisionBox collisionBox) {
        this(spritePath, x, y);

        setCollisionBox(collisionBox);
    }

    /**
     * vérifie si deux entités sont en collision
     * @param entity
     * @return
     */
    public boolean isInCollision(IEntity entity) {
        return getCollisionBox().isInCollision(entity.getCollisionBox());
    }

    public void update() {

    }
}
