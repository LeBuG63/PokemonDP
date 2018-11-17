package AnimationManager;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public interface IAnimationManager {
    int frameIndex = 0;
    int defaultWidth = 0;
    int defaultHeight = 0;

    void setDefaultWidth(int w);
    void setDefaultHeight(int h);

    void addFrame(String imagePath);
    void addFrame(String imagePath, int w, int h);

    Image getFrame(int index);
    Image getNextFrame();
    Image getPrevFrame();
}
