import java.awt.geom.Point2D;

public class Utils {
    public static double calculateDistance(Point2D.Double lhs, Point2D.Double rhs) {
        return Math.sqrt(Math.pow(lhs.getX() - rhs.getX(), 2) + Math.pow(lhs.getY() - rhs.getY(), 2));
    }

    public static double calculateAngle(Point2D.Double lhs, Point2D.Double rhs) {
        return 0.0;
    }
}
