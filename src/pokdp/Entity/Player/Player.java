package Entity.Player;

import Entity.IEntity;
import Entity.ETypeEntity;
import EventManager.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.*;

import java.security.Key;


public class Player extends IEntity {
    private static final int KEYBOARD_MOVEMENT_DELTA = 5;

    public Player(String spritePath, ETypeEntity type, Scene scene) {
        super(spritePath, type);

        eventManager.add(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    coord.y -= KEYBOARD_MOVEMENT_DELTA; break;
                    case RIGHT: coord.x += KEYBOARD_MOVEMENT_DELTA; break;
                    case DOWN:  coord.y += KEYBOARD_MOVEMENT_DELTA; break;
                    case LEFT:  coord.x -= KEYBOARD_MOVEMENT_DELTA; break;
                }

                spriteView.setY(coord.y);
                spriteView.setX(coord.x);
            }
        }, EEventType.KEYBOARD_PRESSED);

        eventManager.add(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                spriteView.setX(100);
            }
        }, EEventType.KEYBOARD_RELEASED);

        eventManager.attachAllEventsToScene(scene);
    }

    public void update() {

    }

    public void draw() {

    }
}
