package AnimationManager;

import Entity.IEntity;
import javafx.animation.Timeline;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public interface IAnimationManager {
    Timeline timeline = new Timeline();

    int frameIndex = 0;
    double defaultWidth = 0;
    double defaultHeight = 0;

    void setTimeline(IEntity entity, double millis, int cycleCount);
    Timeline getTimeline();

    void setDefaultWidth(double w);
    void setDefaultHeight(double h);
    void setDefaultSize(double w, double h);

    void addFrame(String imagePath);
    void addFrameDefaultSize(String imagePath);
    void addFrame(String imagePath, double w, double h);

    Image getFrame(int index);
    Image getNextFrame();
    Image getPrevFrame();
}
