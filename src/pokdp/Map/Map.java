package Map;

import Map.Object.DecoObject;
import javafx.scene.Parent;
import pokdp.Constantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Map extends Parent {
    public int width;
    public int height;

    private HashMap<ETerrainType, ObjectSet> tilesetHash = new HashMap<ETerrainType, ObjectSet>();
    private HashMap<ETerrainType, ObjectSet> decoObjectsetHash = new HashMap<ETerrainType, ObjectSet>();

    private List<Tile> mapTile = new ArrayList<Tile>();
    private List<DecoObject> decoObjectList = new ArrayList<DecoObject>();

    public Map(int w, int h) {
        width = w;
        height = h;
    }

    public List<DecoObject> getDecoObjectList() {
        return decoObjectList;
    }

    public void addTileSet(ObjectSet tileset, ETerrainType type) {
        tilesetHash.put(type, tileset);
    }

    public void addDecoObjectSet(ObjectSet decoObjectSet, ETerrainType type) {
        decoObjectsetHash.put(type, decoObjectSet);
    }

    public void generateRandomTerrain(ETerrainType type) {
        mapTile.clear();
        this.getChildren().removeAll();

        Random random = new Random();

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if (random.nextDouble() > 0.95f) {
                    boolean add = true;

                    int i = random.nextInt(decoObjectsetHash.get(type).size());

                    if (random.nextDouble() < decoObjectsetHash.get(type).getProbability(i)) {
                        DecoObject decoObject = new DecoObject(
                                decoObjectsetHash.get(type).getPathObject(i),
                                x * Constantes.DEFAULT_TILE_MAP_WIDTH,
                                y * Constantes.DEFAULT_TILE_MAP_HEIGHT);

                        decoObject.setCoordX(x * Constantes.DEFAULT_TILE_MAP_WIDTH);
                        decoObject.setCoordY(y * Constantes.DEFAULT_TILE_MAP_HEIGHT);

                        for (DecoObject other : decoObjectList) {
                            if (decoObject.isInCollision(other)) {
                                add = false;
                            }
                        }
                        if (add) {
                            decoObjectList.add(decoObject);
                        }
                    }
                }

                int i = random.nextInt(2);

                while (random.nextDouble() > tilesetHash.get(type).getProbability(i)) {
                    i = random.nextInt(2);
                }

                Tile t = new Tile(tilesetHash.get(type).getPathObject(i));

                t.setCoordX(x * Constantes.DEFAULT_TILE_MAP_WIDTH);
                t.setCoordY(y * Constantes.DEFAULT_TILE_MAP_HEIGHT);

                mapTile.add(t);
                this.getChildren().add(t);
            }
        }

        for (DecoObject decoObject : decoObjectList) {
            this.getChildren().add(decoObject);
        }
    }
}
