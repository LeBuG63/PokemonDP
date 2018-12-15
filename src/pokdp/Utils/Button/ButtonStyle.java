package pokdp.Utils.Button;

import javafx.scene.control.Button;

public class ButtonStyle extends Button {
    public ButtonStyle(String text, String style) {
        super(text);

        this.setStyle(style);
    }
}
