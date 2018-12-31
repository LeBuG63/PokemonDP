package pokdp.Utils.Transition;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Transition {
    public static RotateTransition rotate(Node node, double duration, double angle) {
        RotateTransition rt = new RotateTransition(Duration.millis(duration), node);

        rt.setFromAngle(-angle);
        rt.setByAngle(angle*2);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setAutoReverse(true);

        rt.play();

        return rt;
    }


    public static TranslateTransition translate(Node node, float offsetX, int cycleCount, int duration) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(duration), node);
        tt.setByX(offsetX);
        tt.setCycleCount(cycleCount);
        tt.setAutoReverse(true);

        tt.play();

        return tt;
    }

    public static FadeTransition fade(Node node, double duration, double valStart, double valEnd) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), node);

        fadeTransition.setFromValue(valStart);
        fadeTransition.setToValue(valEnd);

        fadeTransition.play();

        return fadeTransition;
    }
}
