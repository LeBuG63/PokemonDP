package Map.Object;

import com.sun.javafx.geom.Vec2d;

public class CollisionBox {
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
        this.coord = new Vec2d(0, 0);
        this.width = w;
        this.height = h;
    }

    /**
     * vérifie si 2 rectangles sont en collisions
     * @param c     l'autre collisionbox
     * @return      vrai si il y a collision, faux sinon
     */
    public boolean isInCollision(CollisionBox c) {
        return !((c.coord.x >= this.coord.x + this.width)
        || (c.coord.x + c.width <= this.coord.x)
        || (c.coord.y >= this.coord.y + this.height)
        || (c.coord.y + c.height <= this.coord.y));
    }
}
