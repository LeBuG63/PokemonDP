package pokdp.World.Map.Tile.Factory;

import pokdp.World.Map.Tile.ITile;
import pokdp.World.Map.Tile.Tile;

import java.util.List;

public class TileFactory implements ITileFactory {
    public static ITile create(List<String> pathList) {
        return new Tile(pathList);
    }

    public static ITile create(List<String> pathList, double duration){
        return new Tile(pathList, duration);
    }
}
