package Music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.*;

public class Playlist extends MusicManager {
    @Override
    public void play(String name) {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if(!mediaHash.containsKey(name)) return;

        mediaPlayer = new MediaPlayer(mediaHash.get(name));

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                next();
            }
        });

        mediaPlayer.play();
    }

    /**
     * lance la playlist
     */
    public void play() {
        play(musicName.get(0));
    }

    /**
     * mélange de façon aléatoire la playlist
     */
    public void shuffle() {
        List<Map.Entry<String, Media>> collection = new ArrayList<Map.Entry<String, Media>>(mediaHash.entrySet());

        HashMap<String, Media> shuffledHashMap = new HashMap<>();

        Collections.shuffle(collection);

        musicName.clear();

        for(Map.Entry<String, Media> entry : collection) {
            shuffledHashMap.put(entry.getKey(), entry.getValue());
            musicName.add(entry.getKey());
        }

        mediaHash = shuffledHashMap;
    }
}
