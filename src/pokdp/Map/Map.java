package Map;

import Entity.IEntity;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Parent;
import pokdp.Constantes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

public class Map extends Parent {
    public int width;
    public int height;

    private HashMap<ETerrainType, TileSet> tilesetHash = new HashMap<ETerrainType, TileSet>();
    private List<Tile> mapTile = new ArrayList<Tile>();

    public Map(int w, int h) {
        width = w;
        height = h;
    }

    public void addTileSet(TileSet tileset, ETerrainType type) {
        tilesetHash.put(type,tileset);
    }

    public void generateRandomTerrain(ETerrainType type) {
        mapTile.clear();
        this.getChildren().removeAll();

        Random random = new Random();

        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                Tile t = new Tile(tilesetHash.get(type).getPathTile(random.nextInt(2)));

                t.setCoordX(x * Constantes.DEFAULT_SPRITE_WIDTH);
                t.setCoordY(y * Constantes.DEFAULT_SPRITE_HEIGHT);

                mapTile.add(t);
                this.getChildren().add(t);
            }
        }
    }

}
