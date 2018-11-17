package Entity.Player;

import AnimationManager.AnimationManagerSprite;
import AnimationManager.IAnimationManager;
import Entity.EEntityType;
import Entity.IEntity;
import EventManager.EEventType;
import javafx.animation.Animation;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import pokdp.Constantes;

public class Player extends IEntity {
    // Permet de définir le "pas" de pixel
    private static final int KEYBOARD_MOVEMENT_DELTA = 64;
    private final int SPRITE_WIDTH = Constantes.DEFAULT_SPRITE_WIDTH;
    private final int SPRITE_HEIGHT = Constantes.DEFAULT_SPRITE_HEIGHT;

    private IAnimationManager animationManager = new AnimationManagerSprite();

    /**
     *
     * @param scene la scène dans laquelle se trouve le joueur
     */
    public Player(Scene scene) {
        super(EEntityType.PLAYER);

        animationManager.addFrame("file:assets/sprites/player/player1.png");
        animationManager.addFrame("file:assets/sprites/player/player2.png");
        animationManager.addFrame("file:assets/sprites/player/player3.png");

        animationManager.setTimeline(this, 1000, Animation.INDEFINITE);

        setSprite(animationManager.getFrame(0));

        // ajout de l'événement pour déplacer le joueur
        eventManager.add(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    setCoordY(getCoord().y - KEYBOARD_MOVEMENT_DELTA); break;
                    case RIGHT: setCoordX(getCoord().x + KEYBOARD_MOVEMENT_DELTA); break;
                    case DOWN:  setCoordY(getCoord().y + KEYBOARD_MOVEMENT_DELTA); break;
                    case LEFT:  setCoordX(getCoord().x - KEYBOARD_MOVEMENT_DELTA); break;
                }

                spriteView.setY(getCoord().y);
                spriteView.setX(getCoord().x);

                setSprite(animationManager.getNextFrame());
            }
        }, EEventType.KEYBOARD_PRESSED);

        eventManager.attachAllEventsToScene(scene);
    }

    public void update() {

    }
}
