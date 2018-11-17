package Entity;

import EventManager.EventManager;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class IEntity extends Parent {
    // Coordonnées pour placer l'entité sur la scéne
    public Vec2d coord = new Vec2d(0,0);;

    // Le type de l'entité pour par la suite faire des tests
    public ETypeEntity type = ETypeEntity.NONE;

    // Sert à contenir et à afficher une image
    protected ImageView spriteView;

    // Permet de gérer les évenements plus facilement
    protected EventManager eventManager = new EventManager();

    public IEntity(ETypeEntity type) {
        this.type = type;
        this.spriteView = new ImageView();

        this.getChildren().add(spriteView);
    }

    public IEntity(String spritePath, ETypeEntity type) {
        this.type = type;
        this.spriteView = new ImageView(new Image(spritePath));

        this.getChildren().add(spriteView);
    }

    // modifie la taille du conteneur de l'image
    public void setFit(int w, int h) {
        this.spriteView.setFitHeight(w);
        this.spriteView.setFitWidth(h);
    }

    public void setSprite(Image sprite) {
        if (this.spriteView != null) {
            this.spriteView.setImage(sprite);
        }
    }

    public abstract void update();
}
