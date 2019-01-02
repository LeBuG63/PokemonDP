package pokdp.Utils.ConstraintManager;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class RowConstraintManager extends ConstraintManager {
    private RowConstraints rowConstraints = new RowConstraints ();

    public RowConstraintManager(List<Integer> list) {
        super(list);
    }

    public RowConstraintManager(int[] list) {
        super(list);
    }

    public RowConstraintManager() {super();}

    @Override
    public void addPercentToPane(GridPane pane) {
        for(int val : getConstraintsValues()) {
            rowConstraints.setPercentHeight(val);

            pane.getRowConstraints().add(rowConstraints);
        }
    }

    public void addFixedToPane(GridPane pane) {
        for(int val : getConstraintsValues()) {
            rowConstraints.setPrefHeight(val);

            pane.getRowConstraints().add(rowConstraints);
        }
    }
}
