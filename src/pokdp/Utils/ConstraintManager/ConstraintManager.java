package pokdp.Utils.ConstraintManager;

import javafx.scene.Node;
import javafx.scene.layout.ConstraintsBase;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public abstract class ConstraintManager {
    private List<Integer> listConstraints = new ArrayList<>();

    public ConstraintManager() {}


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

    /**
     * ajoute une contrainte
     * @param val la valeur de la contrainte
     */
    public void addConstraint(int val) {
        listConstraints.add(val);
    }

    /**
     * ajoute les contraintes en fonction du pourcentage
     * @param pane le gridpane sur lequelle les contraintes doivent etre mises
     */
    public abstract void addPercentToPane(GridPane pane);

    /**
     * ajoute les contraintes en fonction de la taille en pixel
     * @param pane le gridpane sur lequelle les contraintes doivent etre mises
     */
    public abstract void addFixedToPane(GridPane pane);
}
