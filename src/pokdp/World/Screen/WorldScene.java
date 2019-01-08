package pokdp.World.Screen;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pokdp.AnimationManager.AnimationManagerSprite;
import pokdp.Entity.AEntity;
import pokdp.Entity.Player.Player;
import pokdp.Entity.Player.RandomCombatEvent;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.EventManager.EEventType;
import pokdp.EventManager.EventManager;
import pokdp.Map.ETerrainType;
import pokdp.Map.Map;
import pokdp.Map.ObjectSet;
import pokdp.Music.Playlist;
import pokdp.PokemonMenu.UIPokemonMenu;
import pokdp.Scene.AScene;
import pokdp.Scene.SceneManager;
import pokdp.Utils.Constantes;

import java.util.ArrayList;
import java.util.Random;

public class WorldScene extends AScene {
    private Stage primaryStage;
    private AEntity player = new Player();

    public WorldScene(Stage primaryStage){
        super();
        this.primaryStage = primaryStage;
    }

    @Override
    public void load(double width, double height) {
        Group group = new Group();

        EventManager eventManager = new EventManager();
        RandomCombatEvent randomCombatEvent;

        setScene(new Scene(group));

        Map map = new Map((int)width / Constantes.DEFAULT_TILE_MAP_WIDTH,(int)height / Constantes.DEFAULT_TILE_MAP_HEIGHT + 1);


            map.addTileSet(new ObjectSet() {{
                load("file:assets/sprites/terrain/grass1.png", 0.80f, AEntity.HAS_NO_COLLISION);
                load("file:assets/sprites/terrain/grass2.png", 0.10f, AEntity.HAS_NO_COLLISION);

                load(new ArrayList<String>() {{
                    add("file:assets/sprites/terrain/flower1_down.png");
                    add("file:assets/sprites/terrain/flower1_up.png");
                }}, 0.10f, AnimationManagerSprite.RANDOM_DURATION, AEntity.HAS_NO_COLLISION);
            }}, ETerrainType.FOREST);

            map.addDecoObjectSet(new ObjectSet() {{
                load("file:assets/sprites/terrain/tallgrass1.png", 0.5f, AEntity.HAS_NO_COLLISION);
                load("file:assets/sprites/objects/factory1.png", 0.10f, AEntity.HAS_COLLISION);
                load("file:assets/sprites/objects/mart1.png", 0.10f, AEntity.HAS_COLLISION);
                load("file:assets/sprites/objects/pc1.png", 0.10f, AEntity.HAS_COLLISION);
                load("file:assets/sprites/objects/littletree1.png", 0.55f, AEntity.HAS_COLLISION);
            }}, ETerrainType.FOREST);

            map.addFences(new ObjectSet() {{
                load("file:assets/sprites/objects/fence_forest_vertical1.png", AEntity.HAS_COLLISION);
                load("file:assets/sprites/objects/fence_forest_horizontal1.png", AEntity.HAS_COLLISION);
                load("file:assets/sprites/objects/fence_forest_vertical2.png", AEntity.HAS_COLLISION);
                load("file:assets/sprites/objects/fence_forest_horizontal1.png", AEntity.HAS_COLLISION);
                load("file:assets/sprites/objects/fence_forest_cornerupright.png", AEntity.HAS_COLLISION);
                load("file:assets/sprites/objects/fence_forest_cornerupleft.png", AEntity.HAS_COLLISION);
                load("file:assets/sprites/objects/fence_forest_cornerdownright.png", AEntity.HAS_COLLISION);
                load("file:assets/sprites/objects/fence_forest_cornerdownleft.png", AEntity.HAS_COLLISION);
            }}, ETerrainType.FOREST);

        map.addTileSet(new ObjectSet()
        {{
            load("file:assets/sprites/terrain/snow1.png", 0.80f, AEntity.HAS_NO_COLLISION);
            load("file:assets/sprites/terrain/snow2.png", 0.30f, AEntity.HAS_NO_COLLISION);
        }}, ETerrainType.SNOW);

        map.addDecoObjectSet(new ObjectSet() {{
            load("file:assets/sprites/objects/snowrock1.png", 0.25f, AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/factory1.png", 0.10f, AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/mart1.png", 0.20f, AEntity.HAS_COLLISION);
        }}, ETerrainType.SNOW);

        map.addFences(new ObjectSet() {{
            load("file:assets/sprites/objects/fence_snow_vertical1.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_snow_horizontal1.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_snow_vertical2.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_snow_horizontal2.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_snow_cornerupright.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_snow_cornerupleft.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_snow_cornerdownright.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_snow_cornerdownleft.png", AEntity.HAS_COLLISION);
        }}, ETerrainType.SNOW);

        map.addTileSet(new ObjectSet()
        {{
            load("file:assets/sprites/terrain/dirt1.png", 0.80f, AEntity.HAS_NO_COLLISION);
            load("file:assets/sprites/terrain/dirt2.png", 0.30f, AEntity.HAS_NO_COLLISION);
        }}, ETerrainType.DIRT);

        map.addDecoObjectSet(new ObjectSet() {{
            load("file:assets/sprites/objects/dirtpebble1.png", 0.25f, AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/factory1.png", 0.15f, AEntity.HAS_COLLISION);
        }}, ETerrainType.DIRT);

        map.addFences(new ObjectSet() {{
            load("file:assets/sprites/objects/fence_dirt_vertical1.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_dirt_horizontal1.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_dirt_vertical2.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_dirt_horizontal2.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_dirt_cornerupright.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_dirt_cornerupleft.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_dirt_cornerdownright.png", AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/fence_dirt_cornerdownleft.png", AEntity.HAS_COLLISION);
        }}, ETerrainType.DIRT);

        Playlist playlist = new Playlist()
        {{
            load("assets/musics/opening.wav", "opening");
            load("assets/musics/professoroak.wav", "professoroak");
            load("assets/musics/palettetown.wav", "palettetown");
        }};


        AScene pokemonMenu = new UIPokemonMenu((Player)player);
        SceneManager.addScene(pokemonMenu,Constantes.POKEMONMENU_NAME);

        playlist.shuffle();
        playlist.play();

        ((Player)player).setCollisionObjectsList(map.getDecoObjectList());

        randomCombatEvent = new RandomCombatEvent((Player) player);

        eventManager.add(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                boolean combat = randomCombatEvent.roll();


                switch (event.getCode()) {
                    case ENTER:
                        SceneManager.setScenePokemon(Constantes.POKEMONMENU_NAME, ((Player) player).getPokemonList());
                        return; // ici on veut un return car la suite ne doit pas se passer

                    case P:
                        SceneManager.setScene(Constantes.POKEDEXSCENE_NAME);
                        return;

                    case ESCAPE:
                        SceneManager.setScenePause(Constantes.PAUSESCENE_NAME, getPlayer());
                        return;
                }

                if(combat) {
                    Pokemon enemy;
                    Random random = new Random();

                    int pokemonId = random.nextInt(Constantes.pokemonHashMap.size());
                    int levelPokPlayer = ((Player) player).getPokemon().getLevel();
                    int level = levelPokPlayer + Constantes.DIFFICULTY - random.nextInt(2  * Constantes.DIFFICULTY);

                    enemy = new Pokemon((Pokemon)Constantes.pokemonHashMap.values().toArray()[pokemonId]);

                    enemy.setLevel(level);

                    ((Player) player).resetPosition();

                    map.generateRandomTerrain();

                    SceneManager.setSceneCombat(Constantes.COMBATSCENE_NAME, ((Player) player), ((Player) player).getPokemon(), enemy);
                }

                ((Player) player).processKeyboardEvent(event,primaryStage);
            }
        }, EEventType.KEYBOARD_PRESSED);

        eventManager.attachAllEventsToScene(getScene());

        map.generateRandomTerrain(ETerrainType.FOREST);

        group.getChildren().add(map);
        group.getChildren().add(player);
    }

    public Player getPlayer() {
        return (Player)player;
    }
}
