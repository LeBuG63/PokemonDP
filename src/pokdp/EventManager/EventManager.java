package EventManager;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.event.Event;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class EventManager {
    public HashMap<EventHandler<?>, EEventType> eventDict = new HashMap<EventHandler<?>, EEventType>();

    public void add(EventHandler<?> event, EEventType type) {
        eventDict.put(event, type);
    }

    public void attachAllEventsToScene(Scene scene) {
        for(Map.Entry<EventHandler<?>, EEventType> e : eventDict.entrySet()) {
            switch (e.getValue()) {
                case KEYBOARD_PRESSED:
                    scene.setOnKeyPressed((EventHandler<KeyEvent>)e.getKey());
                    break;
                case KEYBOARD_RELEASED:
                    scene.setOnKeyReleased((EventHandler<KeyEvent>)e.getKey());
                    break;
            }
        }
    }
}
