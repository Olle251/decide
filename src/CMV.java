
/* Points, numpoints, parameters-> create CMV[lics 0-14]

 */

import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class CMV {
    private ArrayList<Boolean> cmvList;
    private List<Point2D.Double> points;
    private int numPoints;
    private Parameters parameters;


    //TODO add parameters to constructer
    public CMV (List<Point2D.Double> points , int numPoints, Parameters parameters){

        this.points = points;
        this.numPoints = numPoints;
        this.parameters = parameters;
        this.cmvList = createCMV();
    }

    /**
     * @return ArrayList with booleans
     */
    public ArrayList<Boolean> getCMV(){
        return cmvList;
    }

    /**
     * Creates the cmv and runs all lics to add the correct boolean on each index.
     * @return ArrayList with booleans.
     */
    private ArrayList<Boolean> createCMV(){
        ArrayList<Boolean> cmvList = new ArrayList();
        return cmvList;
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

            if (!(p1.equals(p2) || p3.equals(p2))){
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
     * Checks if there are at least one set of 3 data points separated by C_PTS and D_PTS consecutive points respectively.
     * These 3 data points form an angle larger than pi + epsilon or smaller than pi - epsilon.
     * As additional requirements, the number of data points has to be at least 5, the C_PTS and D_PTS parameters have to be larger than 1
     * and C_PTS+D_PTS have to be at least as large as the number of points - 3.
     * @return true/false
     */
    public boolean lic9() {
        if (numPoints < 5 || 1 > parameters.C_PTS || 1 > parameters.D_PTS || (parameters.C_PTS + parameters.D_PTS) >= (numPoints-3)) return false;
        Point2D.Double p1;
        Point2D.Double p2;
        Point2D.Double p3;
        int c_pts = parameters.C_PTS;
        int d_pts = parameters.D_PTS;
        double epsilon = parameters.getEpsilon();
        double pi = Math.PI;

        for (int i = 0; i < numPoints-(2+c_pts+d_pts); i++) {
            p1 = points.get(i);
            p2 = points.get(i+1+c_pts);
            p3 = points.get(i+2+c_pts+d_pts);

            if (!(p1.equals(p2) || p3.equals(p2))) {
                double angle = Utils.calculateAngle(p1, p2, p3);
                if (angle < pi-epsilon || angle > pi+epsilon){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there are at least one set of 3 data points separated by E_PTS and F_PTS consecutive points respectively.
     * These 3 data points form a triangle larger than AREA1.
     * As additional requirements, the number of data points has to be at least 5, the E_PTS and F_PTS parameters have to be larger than 1
     * and E_PTS+F_PTS have to be at least as large as the number of points - 3.
     * @return true/false
     */
    public boolean lic10() {
        if (numPoints < 5 || 1 > parameters.E_PTS || 1 > parameters.F_PTS || (parameters.E_PTS + parameters.F_PTS) >= (numPoints-3)) return false;
        Point2D.Double p1;
        Point2D.Double p2;
        Point2D.Double p3;
        int e_pts = parameters.E_PTS;
        int f_pts = parameters.F_PTS;
        double area1 = parameters.getArea1();

        for (int i = 0; i < numPoints-(2+e_pts+f_pts); i++) {
            p1 = points.get(i);
            p2 = points.get(i+1+e_pts);
            p3 = points.get(i+2+e_pts+f_pts);

            double area = Utils.calculateTriangleArea(p1, p2, p3);
            //the area could be zero if the points lies in a line
            if (area > area1){
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

    /**
     * Checks if both lic7 holds, and if there exists at least one set of two data points separated by exactly K PTS
     * consecutive intervening points, that are a distance less than the length, LENGTH2, apart.
     * @return boolean value
     */
    public boolean lic12() {
        if ( (!lic7()) || (numPoints < 3) ) {
            return false;
        }
        double dist;
        Point2D.Double point1;
        Point2D.Double point2;
        double length2 = parameters.getLength2();
        int K_PTS = parameters.getK_PTS();
        for (int i = 0 ; i < (numPoints - (1+K_PTS)) ; i++) {
            point1 = points.get(i);
            point2 = points.get(i+1+K_PTS);
            dist = Utils.calculateDistance(point1, point2);
            if (dist < length2) {
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

    /**
     * Checks if there are at least two sets of 3 data points separated by E_PTS and F_PTS consecutive points respectively.
     * These two sets contain 3 data points which form at least one triangle larger than AREA1 and smaller than AREA2 respectively.
     * As additional requirements, the number of data points has to be at least 5, the E_PTS and F_PTS parameters have to be larger than 1
     * and E_PTS+F_PTS have to be at least as large as the number of points - 3. AREA2 should be non-negative.
     * @return true/false
     */
    public boolean lic14() {
        if(parameters.getArea2() < 0) return false;
        if (!lic10()) return false;
        Point2D.Double p1;
        Point2D.Double p2;
        Point2D.Double p3;
        int e_pts = parameters.E_PTS;
        int f_pts = parameters.F_PTS;
        double area2 = parameters.getArea2();

        for (int i = 0; i < numPoints-(2+e_pts+f_pts); i++) {
            p1 = points.get(i);
            p2 = points.get(i+1+e_pts);
            p3 = points.get(i+2+e_pts+f_pts);

            double area = Utils.calculateTriangleArea(p1, p2, p3);
            //the area could be zero if the points lies in a line
            if (area < area2){
                return true;
            }
        }
        return false;
    }

}
