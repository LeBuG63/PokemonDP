package Map.Object;

import com.sun.javafx.geom.Vec2d;

public class CollisionBox {
    private double  width, height;
    private Vec2d   coord;

    public CollisionBox(Vec2d coord, double w, double h) {
        this.coord = coord;
        this.width = width;
        this.height = height;
    }

    public boolean isInCollision(CollisionBox c) {
        return !((c.coord.x >= this.coord.x + this.width)
        || (c.coord.x + c.width <= this.coord.x)
        || (c.coord.y >= this.coord.y + this.height)
        || (c.coord.y + c.height <= this.coord.y));
    }
}
