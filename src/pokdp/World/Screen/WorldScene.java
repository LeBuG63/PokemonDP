package pokdp.World.Screen;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pokdp.AnimationManager.AnimationManagerSprite;
import pokdp.Entity.AEntity;
import pokdp.Entity.Player.Player;
import pokdp.Map.ETerrainType;
import pokdp.Map.Map;
import pokdp.Map.ObjectSet;
import pokdp.Music.Playlist;
import pokdp.Scene.AScene;
import pokdp.Scene.SceneManager;
import pokdp.Utils.Constantes;

import java.util.ArrayList;

public class WorldScene extends AScene {
    @Override
    public void load(double width, double height) {
        Group group = new Group();

        super.setScene(new Scene(group));

        Map map = new Map(1920 / Constantes.DEFAULT_TILE_MAP_WIDTH,1080 / Constantes.DEFAULT_TILE_MAP_HEIGHT + 1);

        map.addTileSet(new ObjectSet()
        {{
            load("file:assets/sprites/terrain/grass1.png", 0.80f, AEntity.HAS_NO_COLLISION);
            load("file:assets/sprites/terrain/grass2.png", 0.10f, AEntity.HAS_NO_COLLISION);

            load(new ArrayList<String>()
            {{
                add("file:assets/sprites/terrain/flower1_down.png");
                add("file:assets/sprites/terrain/flower1_up.png");
            }}, 0.10f, AnimationManagerSprite.RANDOM_DURATION, AEntity.HAS_NO_COLLISION);
        }}, ETerrainType.FOREST);

        map.addDecoObjectSet(new ObjectSet()
        {{
            load("file:assets/sprites/terrain/tallgrass1.png", 0.5f, AEntity.HAS_NO_COLLISION);
            load("file:assets/sprites/objects/factory1.png", 0.10f, AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/mart1.png", 0.10f, AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/pc1.png", 0.10f, AEntity.HAS_COLLISION);
            load("file:assets/sprites/objects/littletree1.png", 0.55f, AEntity.HAS_COLLISION);
        }}, ETerrainType.FOREST);

        Playlist playlist = new Playlist()
        {{
            load("assets/musics/opening.wav", "opening");
            load("assets/musics/professoroak.wav", "professoroak");
            load("assets/musics/palettetown.wav", "palettetown");
        }};

        playlist.shuffle();
        playlist.play();

        AEntity player = new Player(getScene(), map.getDecoObjectList(), SceneManager.getStage());

        map.generateRandomTerrain(ETerrainType.FOREST);

        group.getChildren().add(map);
        group.getChildren().add(player);

    }
}
