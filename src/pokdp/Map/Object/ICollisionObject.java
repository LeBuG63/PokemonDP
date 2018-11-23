package pokdp.Map.Object;

import com.sun.javafx.geom.Vec2d;

public interface ICollisionObject {
     double width = 0;
     double height = 0;

     Vec2d coord = new Vec2d(0,0);
     Vec2d getCoord() ;

     void setCoord(Vec2d coord);
     double getWidth();
     void setWidth(double width);
     double getHeight();
     void setHeight(double height);

     boolean isInCollision(ICollisionObject c);
}
