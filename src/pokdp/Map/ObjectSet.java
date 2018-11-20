package Map;

import Map.Object.CollisionBox;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjectSet {
    private List<List<String>> set = new ArrayList<List<String>>();
    private List<Double> probaList = new ArrayList<Double>();
    private HashMap<Integer, Double> animationDurationHash = new HashMap<Integer, Double>();
    /**
     * stock une liste de chemin pour une animation
     * @param pathList  la liste de chemin pour une animation
     * @param proba     la probabilité d'aparaitre
     */
    public void load(List<String> pathList, double proba) {
        set.add(pathList);
        probaList.add(proba);
    }

    public void load(List<String> pathList, double proba, double duration) {
        load(pathList, proba);

        animationDurationHash.put(set.size() - 1, duration);
    }

    /**
     * stock un chemin pour un sprite
     * @param path      le chemin du sprite
     * @param proba     la probabilité qu'il apparaisse
     */
    public void load(String path, double proba) {
        ArrayList<String> converted = new ArrayList<>();

        converted.add(path);

        load(converted, proba);
    }

    public List<String> getPathObject(int i) {
        return set.get(i);
    }

    /**
     * retourne la probabilité de l'objectset à l'index I
     * @param i     l'index de l'objectset
     * @return      la probabilité de l'objectset
     */
    public double getProbability(int i) {
        return probaList.get(i);
    }

    public int size() {
        return set.size();
    }

    /**
     * retourne si l'objectset à l'index I a une animation
     * @param i     l'index de l'objectset
     * @return      true si il y'a une animation, faux autrement
     */
    public boolean hasAnimation(int i) {
        return getPathObject(i).size() > 1;
    }

    /**
     * retourne la durée de l'animation de l'objectset I
     * @param i     l'index de l'objectset
     * @return      la durée de l'animation ou 0.00 si il n'y en a pas
     */
    public double getAnimationDuration(int i) {
        if(hasAnimation(i)) {
            return animationDurationHash.get(i);
        }

        return 0.0f;
    }
}
