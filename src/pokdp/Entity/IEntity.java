package Entity;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import EventManager.EventManager;

public abstract class IEntity extends Parent {
    public Vec2d coord;
    public ETypeEntity type = ETypeEntity.NONE;

    protected ImageView spriteView;
    protected EventManager eventManager = new EventManager();

    public IEntity(String spritePath, ETypeEntity type) {
        this.coord = new Vec2d(0,0);
        this.type = type;

        this.spriteView = new ImageView(new Image(spritePath));

        this.getChildren().add(spriteView);
    }



    public abstract void update();

    public abstract void draw();
}
