package Map.Object;

import com.sun.javafx.geom.Vec2d;

public class CollisionBox implements ICollisionObject {
    private double  width;
    private double height;

    private Vec2d   coord;

    public Vec2d getCoord() {
        return coord;
    }

    public void setCoord(Vec2d coord) {
        this.coord = coord;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public CollisionBox(Vec2d coord, double w, double h) {
        this.coord = coord;
        this.width = w;
        this.height = h;
    }

    public CollisionBox(double w, double h) {
        this(new Vec2d(0,0), w, h);
    }

    /**
     * vérifie si 2 rectangles sont en collisions
     * @param c     l'autre collisionObject
     * @return      vrai si il y a collision, faux sinon
     */
    public boolean isInCollision(ICollisionObject c) {
        return !((c.getCoord().x >= (this.getCoord().x + this.getWidth()))
                || ((c.getCoord().x + c.getWidth()) <= this.getCoord().x)
                || (c.getCoord().y >= (this.getCoord().y + this.getHeight()))
                || ((c.getCoord().y + c.getHeight()) <= this.getCoord().y));
    }
}
