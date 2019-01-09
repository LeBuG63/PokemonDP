package pokdp;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pokdp.Scene.Custom.LoadingScene;
import pokdp.Scene.AScene;

public class CustomPreloader extends Preloader {
    private Stage preloaderStage;
    private Scene scene;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        AScene loadingScene = new LoadingScene();

        scene = loadingScene.getScene();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }
}
