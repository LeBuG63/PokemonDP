package Entity.Player;

import AnimationManager.AnimationManagerSprite;
import AnimationManager.IAnimationManager;
import Entity.EEntityType;
import Entity.IEntity;
import EventManager.EEventType;
import Map.Object.CollisionBox;
import Map.Object.DecoObject;
import com.sun.javafx.geom.Vec2d;
import javafx.animation.Animation;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import pokdp.Constantes;

import java.util.List;

public class Player extends IEntity {
    // Permet de définir le "pas" de pixel
    private static final int KEYBOARD_MOVEMENT_DELTA = 10;
    private final int SPRITE_WIDTH = Constantes.DEFAULT_SPRITE_WIDTH;
    private final int SPRITE_HEIGHT = Constantes.DEFAULT_SPRITE_HEIGHT;

    private IAnimationManager animationManager = new AnimationManagerSprite();

    /**
     * @param scene la scène dans laquelle se trouve le joueur
     */
    public Player(Scene scene, List<DecoObject> decoObjectList) {
        super(EEntityType.PLAYER);

        animationManager.addFrame("file:assets/sprites/player/player1.png", Constantes.DEFAULT_SPRITE_WIDTH, Constantes.DEFAULT_SPRITE_HEIGHT);
        animationManager.addFrame("file:assets/sprites/player/player2.png", Constantes.DEFAULT_SPRITE_WIDTH, Constantes.DEFAULT_SPRITE_HEIGHT);
        animationManager.addFrame("file:assets/sprites/player/player3.png", Constantes.DEFAULT_SPRITE_WIDTH, Constantes.DEFAULT_SPRITE_HEIGHT);

        animationManager.setTimeline(this, 1000, Animation.INDEFINITE);

        setSprite(animationManager.getFrame(0));

        // ajout de l'événement pour déplacer le joueur
        eventManager.add(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Vec2d save = new Vec2d(getCoord());

                boolean collision = false;

                switch (event.getCode()) {
                    case UP:
                        setCoordY(getCoord().y - KEYBOARD_MOVEMENT_DELTA);
                        collision = isCollidingWithDeco(decoObjectList);
                        break;
                    case RIGHT:
                        setCoordX(getCoord().x + KEYBOARD_MOVEMENT_DELTA);
                        collision = isCollidingWithDeco(decoObjectList);
                        break;
                    case DOWN:
                        setCoordY(getCoord().y + KEYBOARD_MOVEMENT_DELTA);
                        collision = isCollidingWithDeco(decoObjectList);
                        break;
                    case LEFT:
                        setCoordX(getCoord().x - KEYBOARD_MOVEMENT_DELTA);
                        collision = isCollidingWithDeco(decoObjectList);
                        break;
                }

                if(collision) {
                    setCoord(save);
                }

                setSprite(animationManager.getNextFrame());
            }
        }, EEventType.KEYBOARD_PRESSED);

        eventManager.attachAllEventsToScene(scene);
    }

    public boolean isCollidingWithDeco(List<DecoObject> decoObjectList) {
        if(decoObjectList == null) return false;

        for (DecoObject decoObject : decoObjectList) {
            if (getCollisionBox().isInCollision(decoObject.getCollisionBox()))
                return true;
        }
        return false;
    }

    public void update() {

    }
}
