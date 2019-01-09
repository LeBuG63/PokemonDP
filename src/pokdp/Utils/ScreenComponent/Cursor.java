package pokdp.Utils.ScreenComponent;

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

public class Cursor extends AEntity {
    private final static int CUR_HOR = 0;
    private final static int CUR_VER = 1;

    private ImageView   imageView;
    private int         max;
    private int         step;
    private int         id;

    private EventManager eventManager = new EventManager();

    public Cursor(String imgPath, double x, int max, int step, int cursorpos) {
        super(EEntityType.NONE,new Vec2d(100,100),AEntity.HAS_NO_COLLISION);
        this.imageView = new ImageView(new Image(imgPath));
        this.max = max;

        imageView.setX(x);eventManager.add(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case UP:
                        if(id > 0) {
                            id--;
                            if(cursorpos == CUR_VER)
                                imageView.setY(imageView.getY() - step);
                            else
                                imageView.setX(imageView.getX() - step);
                        }
                        break;
                    case DOWN:
                        if(id < max) {
                            id++;
                            if(cursorpos == CUR_VER)
                                imageView.setY(imageView.getY() + step);
                            else
                                imageView.setX(imageView.getX() + step);
                        }
                        break;
                }
            }
        }, EEventType.KEYBOARD_PRESSED);

        this.getChildren().add(imageView);
    }

    public Cursor(String imgPath, double x, int max, int step) {
        this(imgPath, x, max, step, CUR_VER);
    }

    public int getID() {
        return id;
    }

    public void attachEvent(Scene scene) {
        eventManager.attachAllEventsToScene(scene);
    }
}

