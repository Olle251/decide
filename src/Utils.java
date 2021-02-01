import java.awt.geom.Point2D;
import java.lang.Math;
import java.util.Arrays;


public class Utils {
    public static double calculateDistance(Point2D.Double lhs, Point2D.Double rhs) {
        return Math.sqrt(Math.pow(lhs.getX() - rhs.getX(), 2) + Math.pow(lhs.getY() - rhs.getY(), 2));
    }

    /**
     * Returns an angle given three two-dimensional points, using the second argument as the vertex.
     * @param p1 the first point
     * @param vertex the vertex where the angle is located
     * @param p3 the third point
     * @return   the angle in radians
     */
    public static double calculateAngle(Point2D.Double p1, Point2D.Double vertex, Point2D.Double p3) {

        Point2D.Double vector1 = new Point2D.Double((p1.getX() - vertex.getX()), p1.getY() - vertex.getY());
        Point2D.Double vector2 = new Point2D.Double((p3.getX() - vertex.getX()), p3.getY() - vertex.getY());


        double numerator = (vector1.getX() * vector2.getX()) + (vector1.getY() * vector2.getY());
        double denominator = Math.sqrt(Math.pow(vector1.getX(),2) + Math.pow(vector1.getY(),2)) * Math.sqrt(Math.pow(vector2.getX(),2) + Math.pow(vector2.getY(),2));
        double angle = Math.acos(numerator/denominator);


        return angle;
    }

    /**
     * Returns the area of a triangle spanned by three points in 2D space.
     * @param p1 a Point2D.Double
     * @param p2 a Point2D.Double
     * @param p3 a Point2D.Double
     * @return the area of the triangle connected by the points
     */
    public static double calculateTriangleArea(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {

        double intermediateResult1 = p1.getX()*(p2.getY() - p3.getY());
        double intermediateResult2 = p2.getX()*(p3.getY() - p1.getY());
        double intermediateResult3 = p3.getX()*(p1.getY() - p2.getY());

        return Math.abs((intermediateResult1 + intermediateResult2 + intermediateResult3)/2.0);
    }
}
