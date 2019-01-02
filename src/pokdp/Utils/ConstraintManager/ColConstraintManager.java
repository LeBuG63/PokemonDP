package pokdp.Utils.ConstraintManager;


import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ColConstraintManager extends ConstraintManager {
    private ColumnConstraints   columnConstraints = new ColumnConstraints();

    public ColConstraintManager(List<Integer> list) {
        super(list);
    }

    public ColConstraintManager(int[] list) {
        super(list);
    }

    public ColConstraintManager() {super();}

    @Override
    public void addPercentToPane(GridPane pane) {
        for(int val : getConstraintsValues()) {
            columnConstraints.setPercentWidth(val);

            pane.getColumnConstraints().add(columnConstraints);
        }
    }

    public void addFixedToPane(GridPane pane) {
        for(int val : getConstraintsValues()) {
            columnConstraints.setPrefWidth(val);

            pane.getColumnConstraints().add(columnConstraints);
        }
    }
}
