package pokdp.Map;

import javafx.scene.Parent;
import pokdp.Map.Object.DecoObject;
import pokdp.Map.Tile.Tile;
import pokdp.Utils.Constantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Map extends Parent {
    private int width;
    private int height;

    private HashMap<ETerrainType, ObjectSet> tilesetHash = new HashMap<>();
    private HashMap<ETerrainType, ObjectSet> decoObjectsetHash = new HashMap<>();

    private HashMap<ETerrainType, ObjectSet> fencesetHash = new HashMap<>();

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
     * ajoute des barrieres pour empecher que le joueur ne sorte de la carte
     * @param fenceObjectSet    les barrieres a ajouter
     * @param type              le type du terrain
     */
    public void addFences(ObjectSet fenceObjectSet, ETerrainType type) {
        fencesetHash.put(type, fenceObjectSet);
    }

    public void generateRandomTerrain() {
        Random random = new Random();

        int terrainType = random.nextInt(ETerrainType.COUNT.ordinal());

        generateRandomTerrain(ETerrainType.values()[terrainType]);
    }

    private void manageFences(int x, int y, int bottomFenceLimit, int rightFenceLimit, List<DecoObject> fencesList, ETerrainType type) {
        final int fenceupright =  4;
        final int fenceupleft = 5;
        final int fencedownright = 6;
        final int fencedownleft = 7;

        if(x == 0 || x == bottomFenceLimit) {
            int i = (x == 0) ? 0 : 2;

            i = (x == 0 && y == 0) ? fenceupleft : i;
            i = (x == bottomFenceLimit && y == 0) ? fenceupright : i;
            i = (x == bottomFenceLimit && y == rightFenceLimit) ? fencedownright : i;
            i = (x == 0 && y == rightFenceLimit) ? fencedownleft : i;

            DecoObject fence = new DecoObject(fencesetHash.get(type).getPathObject(i),
                    x * Constantes.DEFAULT_FENCE_WIDTH,
                    y * Constantes.DEFAULT_FENCE_HEIGHT + 3,
                    fencesetHash.get(type).hasCollision(i));

            fence.setFit(Constantes.DEFAULT_FENCE_WIDTH, Constantes.DEFAULT_FENCE_HEIGHT);

            fencesList.add(fence);
        }
        else if(y == 0 || y == rightFenceLimit) {
            int i = (y == 0) ? 1 : 3;

            DecoObject fence = new DecoObject(fencesetHash.get(type).getPathObject(i),
                    x * Constantes.DEFAULT_FENCE_WIDTH,
                    y * Constantes.DEFAULT_FENCE_HEIGHT,
                    fencesetHash.get(type).hasCollision(i));

            fence.setFit(Constantes.DEFAULT_FENCE_WIDTH, Constantes.DEFAULT_FENCE_HEIGHT);

            fencesList.add(fence);
        }
    }

    /**
     * génére un terrain aléatoire en fonction du type du terrain
     * @param type  le type du terrain
     */
    public void generateRandomTerrain(ETerrainType type) {
        mapTile.clear();
        decoObjectList.clear();

        getChildren().removeAll();
        getChildren().clear();

        List<DecoObject> fencesList = new ArrayList<>();

        Random random = new Random();

        final int bottomFenceLimit = width / (Constantes.DEFAULT_FENCE_WIDTH / Constantes.DEFAULT_TILE_MAP_WIDTH) - 1;
        final int rightFenceLimit = height / (Constantes.DEFAULT_FENCE_HEIGHT / Constantes.DEFAULT_TILE_MAP_HEIGHT) - 1;

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                manageFences(x, y, bottomFenceLimit, rightFenceLimit, fencesList, type);

                if (random.nextDouble() > Constantes.PROBA_DECO
                && (x > 5 && y > 5)
                && (x < width - 13)
                && (y < height - 13)) {
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
