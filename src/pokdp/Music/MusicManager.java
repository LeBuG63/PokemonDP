package Music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicManager {
    protected HashMap<String, Media> mediaHash = new HashMap<>();
    protected MediaPlayer     mediaPlayer;

    protected List<String>    musicName = new ArrayList<>();
    protected String          actualMusicName = "";

    protected HashMap<String, Media> getMediaHash() {
        return mediaHash;
    }

    protected void setMediaHash(HashMap<String, Media> mediaHash) {
        this.mediaHash = mediaHash;
    }

    public void load(String musicPath, String name) {
        mediaHash.put(name, new Media(new File(musicPath).toURI().toString()));
        musicName.add(name);
    }

    public void play(String name) {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if(!mediaHash.containsKey(name)) return;

        actualMusicName = name;

        mediaPlayer = new MediaPlayer(mediaHash.get(name));
        mediaPlayer.play();
    }

    public void pause() {
        if(mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void next() {
        List<Map.Entry<String, Media>> collection = new ArrayList<Map.Entry<String, Media>>(mediaHash.entrySet());

        if(musicName.indexOf(actualMusicName) + 1 > musicName.size()) {
            play(musicName.get(0));
        }
        else {
            play(musicName.get(musicName.indexOf(actualMusicName) + 1));
        }
    }

    public void prev() {
        List<Map.Entry<String, Media>> collection = new ArrayList<Map.Entry<String, Media>>(mediaHash.entrySet());

        if(musicName.indexOf(actualMusicName) - 1 > musicName.size()) {
            play(musicName.get(musicName.size() - 1));
        }
        else {
            play(musicName.get(musicName.indexOf(actualMusicName) - 1));
        }
    }
}
