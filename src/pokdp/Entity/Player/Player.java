package Entity.Player;

import AnimationManager.AnimationManagerSprite;
import AnimationManager.IAnimationManager;
import Entity.ETypeEntity;
import Entity.IEntity;
import EventManager.EEventType;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;


public class Player extends IEntity {
    // Permet de définir le "pas" de pixel
    private static final int KEYBOARD_MOVEMENT_DELTA = 64;

    private IAnimationManager animationManager = new AnimationManagerSprite(64, 64);

    public Player(ETypeEntity type, Scene scene) {
        super(type);

        animationManager.addFrame("file:assets/sprites/player/player1.png");
        animationManager.addFrame("file:assets/sprites/player/player2.png");
        animationManager.addFrame("file:assets/sprites/player/player3.png");

        setSprite(animationManager.getFrame(0));

        // ajout de l'événement pour déplacer le joueur
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

                setSprite(animationManager.getNextFrame());
            }
        }, EEventType.KEYBOARD_PRESSED);

        eventManager.attachAllEventsToScene(scene);
    }

    public void update() {

    }
}
