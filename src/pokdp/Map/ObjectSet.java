package pokdp.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjectSet {

    private List<List<String>> set = new ArrayList<>();
    private List<Double> probaList = new ArrayList<>();
    private HashMap<Integer, Double> animationDurationHash = new HashMap<>();
    private HashMap<Integer, Boolean> hasCollisionHash = new HashMap<>();
    /**
     * stock une liste de chemin pour une animation
     * @param pathList  la liste de chemin pour une animation
     * @param proba     la probabilité d'aparaitre
     */
    private void load(List<String> pathList, double proba, boolean hasCollision) {
        set.add(pathList);
        hasCollisionHash.put(set.size() - 1, hasCollision);
        probaList.add(proba);
    }

    public void load(List<String> pathList, double proba, double duration, boolean hasCollision) {
        load(pathList, proba, hasCollision);

        animationDurationHash.put(set.size() - 1, duration);
    }


    /**
     * stock un chemin pour un sprite
     * @param path      le chemin du sprite
     * @param proba     la probabilité qu'il apparaisse
     */
    public void load(String path, double proba, boolean hasCollision) {
        ArrayList<String> converted = new ArrayList<>();

        converted.add(path);

        load(converted, proba, hasCollision);
    }

    public void load(String path, boolean hasCollision) {
        ArrayList<String> converted = new ArrayList<>();

        converted.add(path);

        load(converted, 1.0f, hasCollision);
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

    /**
     * retourne si il l objet a l index i a un collisionbox
     * @param i l index de l objet
     * @return  vrai si il a une collisionbox, faux autrement
     */
    public boolean hasCollision(int i) {
        return hasCollisionHash.get(i);
    }
}
