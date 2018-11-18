package Entity;

import EventManager.EventManager;
import Map.Object.CollisionBox;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class IEntity extends Parent {
    // Le type de l'entité pour par la suite faire des tests
    public EEntityType type = EEntityType.NONE;

    // Sert à contenir et à afficher une image
    protected ImageView spriteView;

    // Permet de gérer les évenements plus facilement
    protected EventManager eventManager = new EventManager();

    // Coordonnées pour placer l'entité sur la scéne
    private Vec2d coord = new Vec2d();

    protected CollisionBox collisionBox;
    /**
     * @param type  le type de l'entité
     */
    public IEntity(EEntityType type) {
        this.type = type;
        this.spriteView = new ImageView();

        collisionBox = new CollisionBox(getCoord(), 0,0);

        this.getChildren().add(spriteView);
    }

    /**
     *
     * @param spritePath    le chemin (file:/...) du sprite
     * @param type          le type de l'entité
     */
    public IEntity(String spritePath, EEntityType type) {
        this.type = type;
        this.spriteView = new ImageView(new Image(spritePath));

        collisionBox = new CollisionBox(getCoord(), spriteView.getFitWidth(), spriteView.getFitHeight());

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

        collisionBox = new CollisionBox(getCoord(), spriteView.getFitWidth(), spriteView.getFitHeight());
    }

    /**
     * change le sprite visible de l'entité
     * @param sprite le sprite à charger dans le conteneur d'image
     */
    public void setSprite(Image sprite) {
        if (this.spriteView != null) {
            this.spriteView.setImage(sprite);

            this.collisionBox.setHeight(sprite.getHeight());
            this.collisionBox.setWidth(sprite.getWidth());
        }
    }

    /**
     * moidifie la coordonnée x
     * @param x     nouvelle coordonnée x
     */
    public void setCoordX(double x) {
        coord.x = x;
        spriteView.setX(x);
        collisionBox.setCoord(new Vec2d(coord.x, coord.y));
    }

    /**
     * modifie la coordonnée y
     * @param y     nouvelle coordonnée y
     */
    public void setCoordY(double y) {
        coord.y = y;
        spriteView.setY(y);
        collisionBox.setCoord(new Vec2d(coord.x, coord.y));
    }

    /**
     * @return les coordonnées de l'entité
     */
    public Vec2d getCoord() {
        return coord;
    }

    public void setCoord(Vec2d vec) {
        coord = vec;
    }

    public void setCollisionBox(CollisionBox cb) {
        collisionBox = cb;
    }

    public CollisionBox getCollisionBox() {
        return collisionBox;
    }
}
