
/* Points, numpoints, parameters-> create CMV[lics 0-14]

 */

import java.awt.*;
import java.util.*;
import java.util.List;

public class CMV {
    private boolean[] vector;
    private List<Point<Double,Double>> points;
    private int numPoints;
    private Parameters parameters;


    //TODO add parameters to constructer
    public CMV (List<Point<Double, Double>> points , int numpoints, Parameters parameters){

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


    public boolean lic1(){
        if (numPoints < 3) return false;
        double radius1 = parameters.RADIUS1;

        for (int i = 0; i < numPoints-2; i++) {
           double distance1 = Math.sqrt(Math.pow(points.get(i).getKey() - points.get(i+1).getKey(), 2) + Math.pow(points.get(i).getValue() - points.get(i+1).getValue(), 2));
           double distance2 = Math.sqrt(Math.pow(points.get(i).getKey() - points.get(i+2).getKey(), 2) + Math.pow(points.get(i).getValue() - points.get(i+2).getValue(), 2));
           double distance3 = Math.sqrt(Math.pow(points.get(i+1).getKey() - points.get(i+2).getKey(), 2) + Math.pow(points.get(i+1).getValue() - points.get(i+2).getValue(), 2));

           if (distance3 > radius1*2 || distance1 > radius1*2 || distance2 > radius1*2) return true;
        }
        
        Pair<Double, Double> p1 = points.get(0);
        Pair<Double, Double> p2 = points.get(1);
        List a = new ArrayList<>();
        a.add(p2.getKey() - p1.getKey());

        return false;
    }


    //LIC2 Checks if there exists a angle that satisfies the condition between three consecutive coordinates in points, p1,p2,p3, p2 always being vertex.
    public boolean lic2 () {
        for (int i = 2; i < numPoints; i++) {
            Point<Double,Double> p3 = points.get(i);
            Point<Double,Double> p2 = points.get(i-1);
            Point<Double,Double> p1 = points.get(i-2);

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
