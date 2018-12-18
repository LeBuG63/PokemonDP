package pokdp.Scene;

import javafx.scene.Scene;
import pokdp.Entity.Pokemon.Pokemon;

import java.util.List;

public abstract class AScene {
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene newScene) {
        scene = newScene;
    }

    public abstract void load(double width, double height);
}
