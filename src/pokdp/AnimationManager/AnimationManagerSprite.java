package pokdp.AnimationManager;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import pokdp.Entity.AEntity;
import pokdp.Utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class AnimationManagerSprite  {
    public static final double RANDOM_DURATION = -1.0f;

    private List<Image>   imageList = new ArrayList<>();

    private int frameIndex = 0;
    private double defaultWidth = Constantes.DEFAULT_SPRITE_WIDTH;
    private double defaultHeight = Constantes.DEFAULT_SPRITE_HEIGHT;

    private Timeline timeline = new Timeline();

    public AnimationManagerSprite() {

    }

    /**
     *
     * @param h la hauteur de chaque sprite
     */
    public AnimationManagerSprite(double h) {
        this();

        setDefaultHeight(h);
        setDefaultWidth(h);
    }

    public Timeline getTimeline() {
        return timeline;
    }

    /**
     * change la largeur des sprites par défaut
     * @param w     nouvelle largeur
     */
    public void setDefaultWidth(double w) {
        defaultWidth = w;
    }

    /**
     * change la hauteur des sprites par défaut
     * @param h     nouvelle hauteur
     */
    public void setDefaultHeight(double h) {
        defaultHeight = h;
    }

    /**
     * change la taille par défaut des sprites
     * @param w nouvelle largeur
     *
     */
    public void setDefaultSize(double w) {
        setDefaultWidth(w);
        setDefaultHeight(w);
    }

    /**
     * creer une timeline pour qu'une animation se fasse automatiquement
     * @param entity        l'entite sur laquelle la timeline va agire
     * @param millis        la durée entre chaque frame
     * @param cycleCount    son nombre de cycles
     */
    public void setTimeline(AEntity entity, double millis, int cycleCount) {
        this.timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(millis),
                        actionEvent -> entity.setSprite(getNextFrame())));
        this.timeline.setCycleCount(cycleCount);
        this.timeline.play();
    }

    /**
     * ajoute une frame à l'animation
     * @param imagePath     le chemin (file:/...) du sprite
     */
    public void addFrame(String imagePath) {
        imageList.add(new Image(imagePath));
    }

    /**
     * ajoute une frame à l'animation de la taille par défaut
     * @param imagePath     le chemin(file:...) du sprite
     */
    public void addFrameDefaultSize(String imagePath) {
        imageList.add(new Image(imagePath, defaultWidth, defaultHeight, false, false));
    }

    /**
     * ajoute une frame à l'animation avec une hauteur / largeur réglable
     * @param imagePath     le chemin (file:/...) du sprite
     * @param w             la largeur
     * @param h             la hauteur
     */
    public void addFrame(String imagePath, double w, double h) {
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
