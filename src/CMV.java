
/* Points, numpoints, parameters-> create CMV[lics 0-14]

 */

import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class CMV {
    private boolean[] vector;
    private List<Point2D.Double> points;
    private int numPoints;
    private Parameters parameters;


    //TODO add parameters to constructer
    public CMV (List<Point2D.Double> points , int numPoints, Parameters parameters){

        this.points = points;
        this.numPoints = numPoints;
        this.parameters = parameters;

        vector = createCMV();
    }

    public boolean[] getCMV(){
        return vector;
    }
    public boolean[] createCMV(){
            // call all LICS
        return null;
    }

    public boolean lic0() {
        double dist;
        Point2D.Double point1;
        Point2D.Double point2;
        double length1 = parameters.getLength1();
        for (int i = 0 ; i < (numPoints - 1) ; i++) {
            point1 = points.get(i);
            point2 = points.get(i+1);

            dist = Math.sqrt(Math.pow((point2.getX() - point1.getX()),2) +
                    Math.pow((point2.getY() - point1.getY()), 2));
            if (dist > length1) {
                return true;
            }
        }
        return false;
    }


    public boolean lic1(){
        if (numPoints < 3) return false;
        double radius1 = parameters.RADIUS1;

        for (int i = 0; i < numPoints-2; i++) {
           double distance1 = Math.sqrt(Math.pow(points.get(i).getX() - points.get(i+1).getX(), 2) + Math.pow(points.get(i).getY() - points.get(i+1).getY(), 2));
           double distance2 = Math.sqrt(Math.pow(points.get(i).getX() - points.get(i+2).getX(), 2) + Math.pow(points.get(i).getY() - points.get(i+2).getY(), 2));
           double distance3 = Math.sqrt(Math.pow(points.get(i+1).getX() - points.get(i+2).getX(), 2) + Math.pow(points.get(i+1).getY() - points.get(i+2).getY(), 2));

           if (distance3 > radius1*2 || distance1 > radius1*2 || distance2 > radius1*2) return true;
        }
        return false;
    }


    //LIC2 Checks if there exists a angle that satisfies the condition between three consecutive coordinates in points, p1,p2,p3, p2 always being vertex.
    public boolean lic2 () {
        for (int i = 2; i < numPoints; i++) {
            Point2D.Double p3 = points.get(i);
            Point2D.Double p2 = points.get(i-1);
            Point2D.Double p1 = points.get(i-2);

            if (!(p1.equals(p2) | p3.equals(p2))){

                double[] a = {p2.getX() - p1.getX(), p2.getY() - p1.getY()};
                double[] b = {p2.getX() - p3.getX(), p2.getY() - p3.getY()};

                double angle = Math.acos((a[0] * b[0] + a[1] * b[1]) / (Math.sqrt(a[0] * a[0] + a[1] * a[1]) * Math.sqrt(b[0] * b[0] + b[1] * b[1])));
                if (angle < (Math.PI - parameters.getEpsilon()) || angle > Math.PI + parameters.getEpsilon()) {
                    return true;
                }
            };

        }
    return false;
    }

}
