
/* Points, numpoints, parameters-> create CMV[lics 0-14]

 */
import javafx.util.Pair;
import java.util.*;


public class CMV {
    private boolean[] vector;
    private List<Pair<Double,Double>> points;
    private int numpoints;
    private Parameters parameters;


    //TODO add parameters to constructer
    public CMV (List<Pair<Double,Double>> points , int numpoints, Parameters parameters){

        this.points = points;
        this.numpoints = numpoints;
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
    //LIC2 Checks if there exists a angle that satisfies the condition between three consecutive coordinates in points, p1,p2,p3, p2 always being vertex.
    public boolean lic2 () {
        for (int i = 2; i < numpoints; i++) {
            Pair<Double,Double> p3 = points.get(i);
            Pair<Double,Double> p2 = points.get(i-1);
            Pair<Double,Double> p1 = points.get(i-2);

            if (!(p1.equals(p2) | p3.equals(p2))){

                double[] a = {p2.getKey() - p1.getKey(), p2.getValue() - p1.getValue()};
                double[] b = {p2.getKey() - p3.getKey(), p2.getValue() - p3.getValue()};

                double angle = Math.acos((a[0] * b[0] + a[1] * b[1]) / (Math.sqrt(a[0] * a[0] + a[1] * a[1]) * Math.sqrt(b[0] * b[0] + b[1] * b[1])));
                if (angle < (Math.PI - parameters.getEpsilon()) || angle > Math.PI + parameters.getEpsilon()) {
                    return true;
                }
            };

        }
    return false;
    }
    
}
