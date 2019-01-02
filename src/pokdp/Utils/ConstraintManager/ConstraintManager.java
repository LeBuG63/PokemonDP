package pokdp.Utils.ConstraintManager;

import javafx.scene.Node;
import javafx.scene.layout.ConstraintsBase;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public abstract class ConstraintManager {
    private List<Integer> listConstraints = new ArrayList<>();

    public ConstraintManager(List<Integer> list) {
        listConstraints = list;
    }

    public ConstraintManager(int[] list) {
        for(int val : list) {
            listConstraints.add(val);
        }
    }

    public List<Integer> getConstraintsValues() {
        return listConstraints;
    }

    public abstract void addPercentToPane(GridPane pane);
    public abstract void addFixedToPane(GridPane pane);
}
