package pokdp.Entity.Player;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pokdp.AnimationManager.AnimationManagerSprite;
import pokdp.Entity.AEntity;
import pokdp.Entity.EEntityType;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Map.Object.DecoObject;
import pokdp.PokemonMenu.UIPokemonMenu;
import pokdp.Scene.SceneManager;
import pokdp.Utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class Player extends AEntity {
    private final int LOOK_UP = 0;
    private final int LOOK_DOWN = 1;
    private final int LOOK_RIGHT = 2;
    private final int LOOK_LEFT = 3;

    private int look = LOOK_DOWN;

    // Permet de définir le "pas" de pixel
    private static final int KEYBOARD_MOVEMENT_DELTA = 10;
    private static List<Pokemon>   pokemonList = new ArrayList<>();

    private AnimationManagerSprite[] animationManager = new AnimationManagerSprite[4];
    private List<DecoObject> decoObjectList;

    public final static Vec2d DEFAULT_POSITION = new Vec2d(100, 100);

    /// TODO: a changé quand la liste des pokemons sera implémentée
    private Pokemon pokemonAct = Constantes.pokemonHashMap.get("Bullbizare");
    /**
     * @param scene la scène dans laquelle se trouve le joueur
     */
    public Player(Scene scene, List<DecoObject> decoObjectList) {
        super(EEntityType.PLAYER, DEFAULT_POSITION, AEntity.HAS_COLLISION);

        this.decoObjectList = decoObjectList;
        RandomCombatEvent randomCombatEvent = new RandomCombatEvent(this);

        final int SPRITE_WIDTH = Constantes.DEFAULT_SPRITE_WIDTH;
        final int SPRITE_HEIGHT = Constantes.DEFAULT_SPRITE_HEIGHT;

        String[] stringLook = {"up", "down", "right", "left"};

        for(int i = 0; i < 4; ++i) {
            animationManager[i] = new AnimationManagerSprite(SPRITE_HEIGHT);

            for (int j = 0; j < 3; ++j) {
                animationManager[i].addFrameDefaultSize("file:assets/sprites/player/sasha_" + stringLook[i] + (j + 1) + ".png");
            }
        }
        pokemonList.add(Constantes.pokemonHashMap.get("Bullbizare"));

        setSprite(animationManager[LOOK_DOWN].getFrame(0));

        getCollisionObject().setHeight(getCollisionObject().getHeight()/2);
        getCollisionObject().setCoord(new Vec2d(getCollisionObject().getCoord().x, getCollisionObject().getCoord().y - getCollisionObject().getCoord().y/2));
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

    public void resetPosition() {
        setCoord(DEFAULT_POSITION);
    }

    public void processKeyboardEvent(KeyEvent event,Stage primaryStage) {
           boolean collision = false;
           Vec2d save = new Vec2d(getCoord());

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
               case ENTER:
                   SceneManager.setScenePokemon("PokemonMenu", getPokemonList());
                   break;
           }

           if (collision) {
               setCoord(save);
           }

           setSprite(animationManager[look].getNextFrame());
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }
    public void addPokemon(Pokemon pokemon) {
        pokemonList.add(pokemon);
    }

    public Pokemon getPokemon() {
        return pokemonList.get(0);
    }

    public boolean alreayHavePokemon(String name) {
        for(Pokemon pokemon : getPokemonList()) {
            if(pokemon.getName() == name) {
                return true;
            }
        }

        return false;
    }

    public static void setNewPokemonOrder(List<Pokemon> newPokemonList){
        pokemonList = newPokemonList;
    }
}
