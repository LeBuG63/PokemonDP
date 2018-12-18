package pokdp.ScreenComponent;

import com.sun.javafx.geom.Vec2d;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import pokdp.Entity.AEntity;
import pokdp.Entity.EEntityType;
import pokdp.EventManager.EEventType;
import pokdp.EventManager.EventManager;
import pokdp.Scene.SceneManager;

public class Cursor extends AEntity {
    private ImageView   imageView;
    private int         max;
    private int         step;
    private int         id;

    private EventManager eventManager = new EventManager();

    public Cursor(Scene scene, String imgPath, double x, int max, int step) {
        super(EEntityType.NONE,new Vec2d(100,100),AEntity.HAS_NO_COLLISION);
        this.imageView = new ImageView(new Image(imgPath));
        this.max = max;

        imageView.setX(x);

        eventManager.add(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case UP:
                        if(id > 0) {
                            id--;
                            imageView.setY(imageView.getY() - step);
                        }
                        break;
                    case DOWN:
                        if(id < max) {
                            id++;
                            imageView.setY(imageView.getY() + step);
                        }
                        break;
                    case ESCAPE:
                        SceneManager.setScene("WorldScene");
                        break;
                }
            }
        }, EEventType.KEYBOARD_PRESSED);

        eventManager.attachAllEventsToScene(scene);
        this.getChildren().add(imageView);
    }

    public int getID() {
        return id;
    }
}

