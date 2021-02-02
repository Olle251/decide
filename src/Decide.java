import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Decide {
    private int numPoints;
    private ArrayList<Point2D.Double> points;
    private Parameters parameters;
    private ArrayList<ArrayList<String>> lcm;
    private ArrayList<Boolean> puv;
    private CMV cmv;
    private PUM pum;
    private FUV fuv;
    private String launch;

    /**
     * Decide computes whether to launch or not, by generating CMV, PUM, FUV and launch based on the input from the parameters.
     * @param numPoints is the number of coordinates
     * @param points is the list of coordinates
     * @param parameters is the parameters used to compute CMV
     * @param lcm is the logical connector matrix used to compute PUM
     * @param puv is the Preliminary unlocking vector used to compute FUV
     */
    public Decide(int numPoints, ArrayList<Point2D.Double> points, Parameters parameters, ArrayList<ArrayList<String>> lcm, ArrayList<Boolean> puv){
        this.numPoints = numPoints;
        this.points = points;
        this.parameters = parameters;
        this.lcm=lcm;
        this.puv =puv;

        setCMV(new CMV(points, numPoints, parameters));
        setPUM(new PUM(cmv.getCMV(), lcm));
        setFUV(new FUV(pum.getPUM(), puv));
        setLaunch(generateLaunch(fuv));
    }

    private void setCMV(CMV cmv) {
        this.cmv = cmv;
    }

    private void setPUM(PUM pum) {
        this.pum = pum;
    }

    private void setFUV(FUV fuv) {
       this. fuv = fuv;
    }

    /**
     * generateLaunch loops through the fuv list to decide launch or not
     * @param fuv
     * @return "YES" if all elements in FUV is true or "NO" otherwise
     */
    private String generateLaunch(FUV fuv) {
        List<Boolean> fuvList = fuv.getFUV();
        for (int i=0; i< fuvList.size(); i++) {
            if (!fuvList.get(i)) return "NO";
        }
        return "YES";
    }

    private void setLaunch(String str) {
        this.launch = str;
    }

    public String getLaunch(){
        return launch;
    }

}
