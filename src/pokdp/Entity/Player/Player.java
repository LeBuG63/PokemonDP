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
    private final int LOOK_UP = 0;
    private final int LOOK_DOWN = 1;
    private final int LOOK_RIGHT = 2;
    private final int LOOK_LEFT = 3;

    private int look = LOOK_DOWN;

    // Permet de définir le "pas" de pixel
    private static final int KEYBOARD_MOVEMENT_DELTA = 10;
    private final int SPRITE_WIDTH = Constantes.DEFAULT_SPRITE_WIDTH;
    private final int SPRITE_HEIGHT = Constantes.DEFAULT_SPRITE_HEIGHT;

    private IAnimationManager[] animationManager = new AnimationManagerSprite[4];

    /**
     * @param scene la scène dans laquelle se trouve le joueur
     */
    public Player(Scene scene, List<DecoObject> decoObjectList) {
        super(EEntityType.PLAYER);

        String[] stringLook = {"up", "down", "right", "left"};

        for(int i = 0; i < 4; ++i) {
            animationManager[i] = new AnimationManagerSprite(SPRITE_WIDTH, SPRITE_HEIGHT);

            for(int j = 0; j < 3; ++j) {
                animationManager[i].addFrameDefaultSize("file:assets/sprites/player/sasha_" + stringLook[i] + (j+1) + ".png");
            }
        }

        setSprite(animationManager[LOOK_DOWN].getFrame(0));

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
                        look = LOOK_UP;
                        break;
                    case RIGHT:
                        setCoordX(getCoord().x + KEYBOARD_MOVEMENT_DELTA);
                        collision = isCollidingWithDeco(decoObjectList);
                        look = LOOK_RIGHT;
                        break;
                    case DOWN:
                        setCoordY(getCoord().y + KEYBOARD_MOVEMENT_DELTA);
                        collision = isCollidingWithDeco(decoObjectList);
                        look = LOOK_DOWN;
                        break;
                    case LEFT:
                        setCoordX(getCoord().x - KEYBOARD_MOVEMENT_DELTA);
                        collision = isCollidingWithDeco(decoObjectList);
                        look = LOOK_LEFT;
                        break;
                }

                if(collision) {
                    setCoord(save);
                }

                setSprite(animationManager[look].getNextFrame());
            }
        }, EEventType.KEYBOARD_PRESSED);

        eventManager.attachAllEventsToScene(scene);
    }

    public boolean isCollidingWithDeco(List<DecoObject> decoObjectList) {
        for (DecoObject decoObject : decoObjectList) {
            if (getCollisionBox().isInCollision(decoObject.getCollisionBox()))
                return true;
        }
        return false;
    }

    public void update() {

    }
}
