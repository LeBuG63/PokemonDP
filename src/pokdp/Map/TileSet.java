package Map;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TileSet {
    private List<List<String>> tileset = new ArrayList<List<String>>();

    public void load(ArrayList<String> pathList) {
        tileset.add(pathList);
    }

    public void load(String path) {
        List<String> converted = new ArrayList<>();

        converted.add(path);

        tileset.add(converted);
    }

    public List<String> getPathTile(int i) {
        return tileset.get(i);
    }
}
