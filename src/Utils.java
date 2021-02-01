import java.awt.geom.Point2D;
import java.lang.Math;
import java.util.Arrays;


public class Utils {

    /**
     * Returns a distance between two two-dimensional points
     * @param lhs the first point
     * @param rhs the second point
     * @return the distance
     */
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
     * Returns a radius of the circumCircle given three two-dimensional points
     * @param a the first distance
     * @param b the second distance
     * @param c the third distance
     * @return   the radius
     */
    public static double calculateCircumRadius(double a, double b, double c) {
        double radius = (a*b*c)/ Math.sqrt((a+b+c)*Math.abs(b+c-a)*Math.abs(c+a-b)*Math.abs(a+b-c));
        return radius;
    }
}
