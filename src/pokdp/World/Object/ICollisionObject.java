package pokdp.World.Object;

import com.sun.javafx.geom.Vec2d;

public interface ICollisionObject {
     Vec2d getCoord() ;

     void setCoord(Vec2d coord);
     double getWidth();
     void setWidth(double width);
     double getHeight();
     void setHeight(double height);

     boolean isInCollision(ICollisionObject c);
}
