package AnimationManager;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class AnimationManagerSprite implements IAnimationManager {
    protected List<Image>   imageList = new ArrayList<>();

    private int frameIndex = 0;
    private int defaultWidth = 0;
    private int defaultHeight = 0;

    public AnimationManagerSprite(int w, int h) {
        setDefaultHeight(h);
        setDefaultWidth(h);
    }

    public void setDefaultWidth(int w) {
        defaultWidth = w;
    }

    public void setDefaultHeight(int h) {
        defaultHeight = h;
    }

    public void addFrame(String imagePath) {
        this.addFrame(imagePath, defaultWidth, defaultHeight);
    }

    public void addFrame(String imagePath, int w, int h) {
        imageList.add(new Image(imagePath, w, h, false, false));
    }

    public Image getFrame(int index) {
        frameIndex = index;

        if(frameIndex >= imageList.size()) {
            frameIndex = 0;
        }
        else if(frameIndex < 0) {
            frameIndex = imageList.size() - 1;
        }

        return imageList.get(frameIndex);
    }

    public Image getNextFrame() {
        return getFrame(++frameIndex);
    }

    public Image getPrevFrame() {
        return getFrame(--frameIndex);
    }
}
