package AnimationManager;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class AnimationManagerSprite implements IAnimationManager {
    private List<Image>   imageList = new ArrayList<>();

    private int frameIndex = 0;
    private int defaultWidth = 0;
    private int defaultHeight = 0;

    /**
     *
     * @param w la largeur de chaque sprite
     * @param h la hauteur de chaque sprite
     */
    public AnimationManagerSprite(int w, int h) {
        setDefaultHeight(h);
        setDefaultWidth(h);
    }

    /**
     * change la largeur des sprites par défaut
     * @param w     nouvelle largeur
     */
    public void setDefaultWidth(int w) {
        defaultWidth = w;
    }

    /**
     * change la hauteur des sprites par défaut
     * @param h     nouvelle hauteur
     */
    public void setDefaultHeight(int h) {
        defaultHeight = h;
    }

    /**
     * ajoute une frame à l'animation
     * @param imagePath     le chemin (file:/...) du sprite
     */
    public void addFrame(String imagePath) {
        this.addFrame(imagePath, defaultWidth, defaultHeight);
    }

    /**
     * ajoute une frame à l'animation avec une hauteur / largeur réglable
     * @param imagePath     le chemin (file:/...) du sprite
     * @param w             la largeur
     * @param h             la hauteur
     */
    public void addFrame(String imagePath, int w, int h) {
        imageList.add(new Image(imagePath, w, h, false, false));
    }

    /**
     * recupere un sprite de l'animation
     * @param index     l'index du sprite
     * @return          Image du sprite
     */
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

    /**
     * recupere la prochaine frame
     * @return  Image de la prochaine frame
     */
    public Image getNextFrame() {
        return getFrame(++frameIndex);
    }

    /**
     * recupere la frame précedente
     * @return  Image de la frame précédente
     */
    public Image getPrevFrame() {
        return getFrame(--frameIndex);
    }
}
