package pokdp.Music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IMusicManager {
    void load(String musicPath, String name);
    void play(String name);
    void pause();
    void stop();
    void next();
    void prev();
}
