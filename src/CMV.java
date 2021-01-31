
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
            double distance1 = Utils.calculateDistance(points.get(i), points.get(i+1));
            double distance2 = Utils.calculateDistance(points.get(i), points.get(i+2));
            double distance3 = Utils.calculateDistance(points.get(i+1), points.get(i+2));

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
            double angle = Utils.calculateAngle(p1, p2, p3);
                if (angle < (Math.PI - parameters.getEpsilon()) || angle > Math.PI + parameters.getEpsilon()) {
                    return true;
                }
            };

        }
    return false;
    }

    public boolean lic5 () {
        Point2D.Double p1;
        Point2D.Double p2;
        for (int i = 0; i < numPoints-1; i++) {
            p1 = points.get(i);
            p2 = points.get(i+1);
            if (p2.getX() < p1.getX()) {
                return true;
            }
        }
        return false;
    }

    public boolean lic7() {
        double dist;
        Point2D.Double point1;
        Point2D.Double point2;
        double length1 = parameters.getLength1();
        int K_PTS = parameters.getK_PTS();
        if (numPoints < 3) { return false; }
        for (int i = 0 ; i < (numPoints - (1+K_PTS)) ; i++) {
            point1 = points.get(i);
            point2 = points.get(i+1+K_PTS);
            dist = Utils.calculateDistance(point1, point2);
            if (dist > length1) {
                return true;
            }
        }
        return false;
    }

}
