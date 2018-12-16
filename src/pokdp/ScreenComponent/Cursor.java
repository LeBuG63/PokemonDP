package pokdp.ScreenComponent;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import pokdp.EventManager.EEventType;
import pokdp.EventManager.EventManager;

public class Cursor extends Parent {
    private ImageView   imageView;
    private int         max;
    private int         step;
    private int         id;

    private EventManager eventManager = new EventManager();

    public Cursor(Scene scene, String imgPath, double x, int max, int step) {
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

