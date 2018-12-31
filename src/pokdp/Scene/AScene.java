package pokdp.Scene;

import javafx.scene.Scene;

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
