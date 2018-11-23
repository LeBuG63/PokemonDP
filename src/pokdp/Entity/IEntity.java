package pokdp.Entity;

import pokdp.EventManager.EventManager;
import pokdp.Map.Object.CollisionBox;
import pokdp.Map.Object.ICollisionObject;
import pokdp.Utils.Constantes;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pokdp.Entity.*;

public abstract class IEntity extends Parent {
    public final static boolean HAS_COLLISION = true;
    public final static boolean HAS_NO_COLLISION = false;

    private boolean hasCollision = false;

    // Le type de l'entité pour par la suite faire des tests
    public EEntityType type = EEntityType.NONE;

    // Sert à contenir et à afficher une image
    private ImageView spriteView;

    // Permet de gérer les évenements plus facilement
    private EventManager eventManager = new EventManager();

    // Coordonnées pour placer l'entité sur la scéne
    private Vec2d coord = new Vec2d(0,0);

    private ICollisionObject collisionObject;

    /**
     * @param type  le type de l'entité
     */
    public IEntity(EEntityType type, boolean hasCollision) {
        this.hasCollision = hasCollision;
        this.type = type;
        this.spriteView = new ImageView();

        setCollisionObject(new CollisionBox(0, 0));

        this.getChildren().add(spriteView);
    }

    public IEntity(EEntityType type, Vec2d coord, boolean hasCollision) {
        this.hasCollision = hasCollision;
        this.type = type;
        this.spriteView = new ImageView();

        setCollisionObject(new CollisionBox(coord, 0, 0));

        setCoord(coord);

        this.getChildren().add(spriteView);
    }

    /**
     *
     * @param spritePath    le chemin (file:/...) du sprite
     * @param type          le type de l'entité
     */
    public IEntity(String spritePath, EEntityType type, boolean hasCollision) {
        this.hasCollision = hasCollision;
        this.type = type;
        this.spriteView = new ImageView(new Image(spritePath));

        setCollisionObject(new CollisionBox(getCoord(), spriteView.getFitWidth(), spriteView.getFitHeight()));

        this.getChildren().add(spriteView);
    }

    /**
     * modifie la taille du conteneur de l'image
     * @param w     nouvelle largeur
     * @param h     nouvelle hauteur
     */
    public void setFit(int w, int h) {
        this.spriteView.setFitHeight(w);
        this.spriteView.setFitWidth(h);

        setCollisionObject(new CollisionBox(getCoord(), spriteView.getFitWidth(), spriteView.getFitHeight()));
    }

    /**
     * change le sprite visible de l'entité
     * @param sprite le sprite à charger dans le conteneur d'image
     */
    public void setSprite(Image sprite) {
        if (this.spriteView != null) {
            this.spriteView.setImage(sprite);

            this.collisionObject.setHeight(sprite.getHeight() - Constantes.HITBOX_MARGIN);
            this.collisionObject.setCoord(new Vec2d(collisionObject.getCoord().x, collisionObject.getCoord().y - Constantes.HITBOX_MARGIN));
            this.collisionObject.setWidth(sprite.getWidth() - Constantes.HITBOX_MARGIN);
        }
    }

    /**
     * moidifie la coordonnée x
     * @param x     nouvelle coordonnée x
     */
    public void setCoordX(double x) {
        coord.x = x;
        spriteView.setX(x);
        collisionObject.setCoord(new Vec2d(coord.x, coord.y));
    }

    /**
     * modifie la coordonnée y
     * @param y     nouvelle coordonnée y
     */
    public void setCoordY(double y) {
        coord.y = y;
        spriteView.setY(y);
        collisionObject.setCoord(new Vec2d(coord.x, coord.y));
    }

    /**
     * @return les coordonnées de l'entité
     */
    public Vec2d getCoord() {
        return coord;
    }

    public void setCoord(Vec2d vec) {
        setCoordX(vec.x);
        setCoordY(vec.y);
    }

    public void setCollisionObject(ICollisionObject cb) {
        collisionObject = cb;
    }

    public ICollisionObject getCollisionObject() {
        return collisionObject;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public boolean hasCollision() {
        return hasCollision;
    }
}
