package pokdp.Utils.ScreenComponent.Button;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ClickableImageView extends Button {

    private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 5, 5, 5, 5;";
    private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 6 4 4 6;";

    public ClickableImageView(String imageurl) {
        setGraphic(new ImageView(new Image(imageurl)));
        setStyle(STYLE_NORMAL);

        addEvent();
    }

    public ClickableImageView(String imageurl, int imgwidth, int imgheight) {
        setGraphic(new ImageView(new Image(imageurl, imgwidth, imgheight, false, false)));
        setStyle(STYLE_NORMAL);

        addEvent();
    }

    private void addEvent() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(STYLE_PRESSED);
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(STYLE_NORMAL);
            }
        });
    }
}