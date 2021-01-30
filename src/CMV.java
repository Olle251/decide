
/* Points, numpoints, parameters-> create CMV[lics 0-14]

 */
import javafx.util.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;


public class CMV {
    private boolean[] vector;
    private List<Pair<Double,Double>> points;
    private int numPoints;
    private Parameters parameters;
    //TODO add parameters to constructer
    public CMV (List<Pair<Double,Double>> points , int numPoints, Parameters parameters){

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


}
