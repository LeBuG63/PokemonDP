package AnimationManager;

import Entity.IEntity;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Duration;
import pokdp.Constantes;

public class AnimationManagerSprite implements IAnimationManager {
    private List<Image>   imageList = new ArrayList<>();

    private int frameIndex = 0;
    private int defaultWidth = Constantes.DEFAULT_SPRITE_WIDTH;
    private int defaultHeight = Constantes.DEFAULT_SPRITE_HEIGHT;

    public Timeline timeline = new Timeline();

    public AnimationManagerSprite() {

    }
    /**
     *
     * @param w la largeur de chaque sprite
     * @param h la hauteur de chaque sprite
     */
    public AnimationManagerSprite(int w, int h) {
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
     * creer une timeline pour qu'une animation se fasse automatiquement
     * @param entity        l'entite sur laquelle la timeline va agire
     * @param millis        la durée entre chaque frame
     * @param cycleCount    son nombre de cycle
     */
    public void setTimeline(IEntity entity, double millis, int cycleCount) {
        this.timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(millis),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                entity.setSprite(getNextFrame());
                            }
                        }));
        this.timeline.setCycleCount(cycleCount);
        this.timeline.play();
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
