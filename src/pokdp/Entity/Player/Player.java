package pokdp.Entity.Player;

import javafx.scene.input.KeyCode;
import pokdp.AnimationManager.AnimationManagerSprite;
import pokdp.Entity.EEntityType;
import pokdp.Entity.IEntity;
import pokdp.EventManager.EEventType;
import pokdp.Map.Object.DecoObject;
import pokdp.Utils.Constantes;
import com.sun.javafx.geom.Vec2d;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class Player extends IEntity {
    private final int LOOK_UP = 0;
    private final int LOOK_DOWN = 1;
    private final int LOOK_RIGHT = 2;
    private final int LOOK_LEFT = 3;

    private int look = LOOK_DOWN;

    // Permet de définir le "pas" de pixel
    private static final int KEYBOARD_MOVEMENT_DELTA = 10;

    private AnimationManagerSprite[] animationManager = new AnimationManagerSprite[4];

    /**
     * @param scene la scène dans laquelle se trouve le joueur
     */
    public Player(Scene scene, List<DecoObject> decoObjectList) {
        super(EEntityType.PLAYER, new Vec2d(100,100), IEntity.HAS_COLLISION);

        final int SPRITE_WIDTH = Constantes.DEFAULT_SPRITE_WIDTH;
        final int SPRITE_HEIGHT = Constantes.DEFAULT_SPRITE_HEIGHT;

        String[] stringLook = {"up", "down", "right", "left"};

        for(int i = 0; i < 4; ++i) {


            animationManager[i] = new AnimationManagerSprite(SPRITE_HEIGHT);

            for(int j = 0; j < 3; ++j) {
                animationManager[i].addFrameDefaultSize("file:assets/sprites/player/sasha_" + stringLook[i] + (j+1) + ".png");
            }
        }

        setSprite(animationManager[LOOK_DOWN].getFrame(0));

        getCollisionObject().setHeight(getCollisionObject().getHeight()/2);
        getCollisionObject().setCoord(new Vec2d(getCollisionObject().getCoord().x, getCollisionObject().getCoord().y - getCollisionObject().getCoord().y/2));
        // ajout de l'événement pour déplacer le joueur
        getEventManager().add((EventHandler<KeyEvent>) event -> {
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
        }, EEventType.KEYBOARD_PRESSED);


        getEventManager().attachAllEventsToScene(scene);
    }

    /**
     * vérifie si le joueur est en collision avec un élément du décors
     * @param decoObjectList
     * @return
     */
    private boolean isCollidingWithDeco(List<DecoObject> decoObjectList) {
        for (DecoObject decoObject : decoObjectList) {
            if (decoObject.hasCollision() && getCollisionObject().isInCollision(decoObject.getCollisionObject())) {
                return true;
            }
        }
        return false;
    }
}
