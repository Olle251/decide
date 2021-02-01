
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

            dist = Utils.calculateDistance(point1, point2);
            if (dist > length1) {
                return true;
            }
        }
        return false;
    }

    // returns true if there is a pair of three poinst that cant be contained within a circle of radius1
    public boolean lic1(){
        if (numPoints < 3) return false;
        double radius1 = parameters.RADIUS1;

        for (int i = 0; i < numPoints-2; i++) {
            double a = Utils.calculateDistance(points.get(i), points.get(i+1));
            double b = Utils.calculateDistance(points.get(i), points.get(i+2));
            double c = Utils.calculateDistance(points.get(i+1), points.get(i+2));
            double radius = Utils.calculateCircumRadius(a, b, c);
            //the area could be zero if the points lies in a line
            if (Double.isInfinite(radius)) {
                double max1 = Math.max(a, b);
                double max2 = Math.max(max1, c);
                if (max2 > radius1) return true;

            } else if (radius > radius1) {
                return true;
            }
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

    /**
     * Checks if there exists at least 3 consecutive data points that form the vertices of a triangle with area greater than parameters.AREA1.
     * @return true or false.
     */
    public boolean lic3 () {
        if (numPoints < 3) {
            return false;
        }
        for (int i = 2; i < numPoints; i++) {
            Point2D.Double p3 = points.get(i);
            Point2D.Double p2 = points.get(i-1);
            Point2D.Double p1 = points.get(i-2);

            if (Utils.calculateTriangleArea(p1, p2, p3) > parameters.getArea1()){
                return true;
            };
        }
        return false;
    }

    /**
     * Checks if there are at least q_points consecutive data points in quads or more quadrants.
     * @return true/false
     */

    public boolean lic4 () {
        int qPoints = parameters.getQ_PTS();
        int quads = parameters.getQUADS();

        List<Integer> visitedQuadrants;

        for (int i = 0; i < numPoints - qPoints - 1; i++) {
            visitedQuadrants = Arrays.asList(0,0,0,0);

            for(int j = 0; j < qPoints; j++) {
                if(points.get(i+j).getX() >= 0) {
                    if(points.get(i+j).getY() >= 0) {
                        visitedQuadrants.set(0, 1);
                    }
                    else {
                        visitedQuadrants.set(3,1);
                    }
                }
                else {
                    if(points.get(i+j).getY() >= 0) {
                        visitedQuadrants.set(1,1);
                    }
                    else {
                        visitedQuadrants.set(2,1);
                    }
                }
            }
            int sum = 0;
            for (Integer num : visitedQuadrants) {
                sum = sum + num;

            }
            if (sum >= quads) {
                return true;
            }
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

    /**
     * Checks the distance between a point and a line between points compared with distance in parameters.
     * If the two points creating the line is the same then we calculate the distance from that point to all other points of n_pts.
     * @return true/false which tells if distance in parameters is bigger than the calculated distance.
     */
    public boolean lic6 () {
        int n_pts = parameters.getN_PTS();
        double dist = parameters.getDIST();
        if (numPoints < 3 || (3 > n_pts || n_pts > numPoints) || 0 > dist) return false;

        for (int i = 0; i < numPoints-n_pts; i++) {
            Point2D.Double first = points.get(i);
            Point2D.Double last = points.get(i+n_pts);

            //Calculate the line between first and last
            double diffX = last.getX() - first.getX();
            double diffY = last.getY() - first.getY();
            double slope = diffY/diffX;
            double intersectY = first.getY() - first.getX()*slope;

            //For every n_pts
            for (int j = 1; j < n_pts-1; j++) {
                double calcDist;
                Point2D.Double currPoint = points.get(i+j);
                //If first and last is the same.
                if (first.equals(last)) {
                    //calculate distance between two points.
                    calcDist = Utils.calculateDistance(first, currPoint);
                } else {
                    //Calculate distance between a point and a line
                    calcDist = (Math.abs((slope*currPoint.getX()) + (-1*currPoint.getY()) + intersectY)) / Math.sqrt(Math.pow(slope,2) + 1);
                }
                if (calcDist > dist) {
                    return true;
                }
            }
        }
        return false;
    }



    /** Checks if there exists at least one set of two data points separated by exactly K_PTS consecutive intervening
     * points that are a distance greater than the length, LENGTH1, apart.
     * @return boolean that says if lic7 holds or not
     */
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

    public boolean lic8() {
        if (numPoints < 5 || 1 > parameters.A_PTS || 1 > parameters.B_PTS || (parameters.A_PTS + parameters.B_PTS) >= (numPoints-3)) return false;
        Point2D.Double p1;
        Point2D.Double p2;
        Point2D.Double p3;
        int a_pts = parameters.A_PTS;
        int b_pts = parameters.B_PTS;
        double radius1 = parameters.RADIUS1;

        for (int i = 0; i < numPoints-(2+a_pts+b_pts); i++) {
            p1 = points.get(i);
            p2 = points.get(i+1+a_pts);
            p3 = points.get(i+2+a_pts+b_pts);

            double a = Utils.calculateDistance(p1, p2);
            double b = Utils.calculateDistance(p1, p3);
            double c = Utils.calculateDistance(p2, p3);
            double radius = Utils.calculateCircumRadius(a, b, c);
            //the area could be zero if the points lies in a line
            if (Double.isInfinite(radius)) {
                double max1 = Math.max(a, b);
                double max2 = Math.max(max1, c);
                if (max2 > radius1) return true;

            } else if (radius > radius1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there exists a set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
     * exactly G_PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j ).
     * @return boolean value
     */
    public boolean lic11 () {
        Point2D.Double p1;
        Point2D.Double p2;
        int G_PTS = parameters.getG_PTS();
        if (numPoints < 3) { return false; }
        for (int i = 0; i < numPoints-1-G_PTS; i++) {
            p1 = points.get(i);
            p2 = points.get(i+1+G_PTS);
            if (p2.getX() < p1.getX()) {
                return true;
            }
        }
        return false;
    }

    public boolean lic13 () {
        if (!lic8() || parameters.RADIUS2 < 0) return false;
        Point2D.Double p1;
        Point2D.Double p2;
        Point2D.Double p3;
        int a_pts = parameters.A_PTS;
        int b_pts = parameters.B_PTS;
        double radius2 = parameters.RADIUS2;

        for (int i = 0; i < numPoints-(2+a_pts+b_pts); i++) {
            p1 = points.get(i);
            p2 = points.get(i+1+a_pts);
            p3 = points.get(i+2+a_pts+b_pts);

            double a = Utils.calculateDistance(p1, p2);
            double b = Utils.calculateDistance(p1, p3);
            double c = Utils.calculateDistance(p2, p3);
            double radius = Utils.calculateCircumRadius(a, b, c);
            //the area could be zero if the points lies in a line
            if (Double.isInfinite(radius)) {
                double max1 = Math.max(a, b);
                double max2 = Math.max(max1, c);
                if (max2 < radius2) return true;

            } else if (radius < radius2) {
                return true;
            }
        }
        return false;

    }

}
