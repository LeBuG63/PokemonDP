package pokdp.Music;

public interface IMusicManager {
    void load(String musicPath, String name);
    void play(String name);
    void pause();
    void stop();
    void next();
    void prev();
}
