package pokdp.World.Map.Tile.Factory;

import pokdp.World.Map.Tile.ITile;

import java.util.List;

public interface ITileFactory {
    static ITile create(List<String> pathList) { return null; }
    static ITile create(List<String> pathList, double duration) {return null; }
}
