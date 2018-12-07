package pokdp.Map;

import pokdp.Entity.IEntity;
import pokdp.Map.Object.DecoObject;
import pokdp.Map.Tile.Tile;
import javafx.scene.Parent;
import pokdp.Utils.Constantes;

import java.util.*;

public class Map extends Parent {
    private int width;
    private int height;

    private HashMap<ETerrainType, ObjectSet> tilesetHash = new HashMap<>();
    private HashMap<ETerrainType, ObjectSet> decoObjectsetHash = new HashMap<>();

    private List<Tile> mapTile = new ArrayList<>();
    private List<DecoObject> decoObjectList = new ArrayList<>();

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

        List<DecoObject> fencesList = new ArrayList<>();

        Random random = new Random();

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if(x == 0 || x == width - 1) {
                    DecoObject fence_ver = new DecoObject("file:assets/sprites/objects/fence_vertical1.png", x * Constantes.DEFAULT_TILE_MAP_WIDTH + 5, y * Constantes.DEFAULT_TILE_MAP_HEIGHT, IEntity.HAS_COLLISION);

                    fencesList.add(fence_ver);
                }
                else if(y == 0 || y == height - 1) {
                    DecoObject fence_hor = new DecoObject("file:assets/sprites/objects/fence_horizontal1.png", (x-1) * Constantes.DEFAULT_TILE_MAP_WIDTH, y * Constantes.DEFAULT_TILE_MAP_HEIGHT + 10, IEntity.HAS_COLLISION);

                    fencesList.add(fence_hor);
                }
                else if (random.nextDouble() > Constantes.PROBA_DECO
                && (x > 5 && y > 5)) {
                    boolean add = true;

                    int i = random.nextInt(decoObjectsetHash.get(type).size());

                    if (random.nextDouble() < decoObjectsetHash.get(type).getProbability(i)) {
                        DecoObject decoObject = new DecoObject(
                                decoObjectsetHash.get(type).getPathObject(i),
                                x * Constantes.DEFAULT_TILE_MAP_WIDTH,
                                y * Constantes.DEFAULT_TILE_MAP_HEIGHT,
                                decoObjectsetHash.get(type).hasCollision(i));

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

                t.setCoordX(x * Constantes.DEFAULT_TILE_MAP_WIDTH);
                t.setCoordY(y * Constantes.DEFAULT_TILE_MAP_HEIGHT);

                mapTile.add(t);
                this.getChildren().add(t);
            }
        }

        for(DecoObject fence : fencesList) {
            decoObjectList.add(0, fence);
        }

        for (DecoObject decoObject : decoObjectList) {
            this.getChildren().add(decoObject);
        }
    }
}
