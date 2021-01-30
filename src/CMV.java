
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
    
}
