package Map;

import Map.Object.DecoObject;
import Map.Tile.Tile;
import javafx.scene.Parent;
import Utils.Constantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Map extends Parent {
    private int width;
    private int height;

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

    /**
     * ajoute un tile
     * @param tileset   l'objet tile à ajouter
     * @param type      le type du terrain
     */
    public void addTileSet(ObjectSet tileset, ETerrainType type) {
        tilesetHash.put(type, tileset);
    }

    /**
     * ajoute un objet de décoration
     * @param decoObjectSet     l'objet de décoration à ajouter
     * @param type              le type du terrain
     */
    public void addDecoObjectSet(ObjectSet decoObjectSet, ETerrainType type) {
        decoObjectsetHash.put(type, decoObjectSet);
    }

    /**
     * génére un terrain aléatoire en fonction du type du terrain
     * @param type  le type du terrain
     */
    public void generateRandomTerrain(ETerrainType type) {
        mapTile.clear();
        this.getChildren().removeAll();

        Random random = new Random();

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if (random.nextDouble() > Constantes.PROBA_DECO) {
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

                ObjectSet objectSet = tilesetHash.get(type);

                int i = random.nextInt(objectSet.size());

                while (random.nextDouble() > objectSet.getProbability(i)) {
                    i = random.nextInt(objectSet.size());
                }


                Tile t;

                if(objectSet.hasAnimation(i)) {
                    t = new Tile(objectSet.getPathObject(i), objectSet.getAnimationDuration(i));
                }
                else {
                    t = new Tile(objectSet.getPathObject(i));
                }

                t.getCollisionBox().setWidth(t.getCollisionBox().getWidth() - 100);

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
