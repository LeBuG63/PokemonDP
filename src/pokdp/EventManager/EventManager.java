package EventManager;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;

public class EventManager {
    // Permet de stocker les événements, clé = EventHandler<?> val = EEVentType
    public HashMap<EventHandler<?>, EEventType> eventDict = new HashMap<EventHandler<?>, EEventType>();

    // TODO: penser à faire des vérifications
    public void add(EventHandler<?> event, EEventType type) {
        eventDict.put(event, type);
    }

    // Permet de relier tous les éléments à la scéne
    public void attachAllEventsToScene(Scene scene) {
        for(Map.Entry<EventHandler<?>, EEventType> e : eventDict.entrySet()) {
            switch (e.getValue()) {
                case KEYBOARD_PRESSED:
                    scene.setOnKeyPressed((EventHandler<KeyEvent>)e.getKey());
                    break;
                case KEYBOARD_RELEASED:
                    scene.setOnKeyReleased((EventHandler<KeyEvent>)e.getKey());
                    break;
                case MOUSE_MOVED:
                    scene.setOnMouseMoved((EventHandler<MouseEvent>)e.getKey());
                    break;
                case MOUSE_CLICKED:
                    scene.setOnMouseClicked((EventHandler<MouseEvent>)e.getKey());
                    break;
            }
        }
    }
}
