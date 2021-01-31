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
     * Takes two two-dimensional points and returns the vector between them as a 2D point.
     * @param start the starting point of the vector
     * @param end the end point of the vector
     * @return a Point2D.Double representing the vector between the points
     */
    public static Point2D.Double calculate2DVector(Point2D.Double start, Point2D.Double end) {
        Point2D.Double vector = new Point2D.Double(end.getX() - start.getX(), end.getY() - start.getY());
        return vector;
    }

}
