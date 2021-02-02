import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecideTest {
    int numPoints;
    ArrayList<Point2D.Double> points;
    Parameters parameters = new Parameters();
    ArrayList<ArrayList<String>> lcm;
    ArrayList<Boolean> puv;

    @BeforeEach
    void setUp() {
        setParameters();
        points = new ArrayList<Point2D.Double>(Arrays.asList(new Point2D.Double(7.0,20.0), new Point2D.Double(9.0,25.0),
                new Point2D.Double(6.0,20.0), new Point2D.Double(15.0,21.0),new Point2D.Double(14.0,28.0),
                new Point2D.Double(25.0,32.0), new Point2D.Double(30.0,34.0), new Point2D.Double(35.0,45.0)));
        numPoints = points.size();
        CMV cmv = new CMV(points, numPoints, parameters);
        setLCM("ORR");
        setPUV(true);
    }

    private void setLCM(String str){
        this.lcm = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            ArrayList<String> tmp = new ArrayList<>();
            for (int j = 0; j < 15; j++){
                tmp.add(str);
            }
            this.lcm.add(tmp);
        }
    }

    private void setPUV(boolean b){
        this.puv = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            this.puv.add(b);
        }
    }

    private void setParameters(){
        parameters.setLength1(1.0);
        parameters.setRadius1(1);
        parameters.setEpsilon(0.0);
        parameters.setArea1(1.0);
        parameters.setDIST(1);
        parameters.setN_PTS(3);
        parameters.setK_PTS(1);
        parameters.setA_PTS(1);
        parameters.setB_PTS(2);
        parameters.setC_PTS(1);
        parameters.setD_PTS(2);
        parameters.setE_PTS(1);
        parameters.setF_PTS(2);
        parameters.setG_PTS(1);
        parameters.setLength2(10.0);
        parameters.setRadius2(500);
        parameters.setArea2(10000);
    }

    // With the testcases at least one LIC is true, and if we set LCM to ORR,
    // PUM should be true in every element, which results in an FUV with only true elements and a launch return of "YES"
    @Test
    void testDecideTrue(){
        Decide dec = new Decide(numPoints, points, parameters, lcm, puv);
        assertEquals("YES", dec.getLaunch());
    }

    // If we set all elements in PUV to false, all elements in FUV should be true
    // Therefore the launch should be "YES"
    @Test
    void testDecideTrue2(){
        setPUV(false);
        Decide dec = new Decide(numPoints, points, parameters, lcm, puv);
        assertEquals("YES", dec.getLaunch());
    }

    //Setting the length to 500 results in a few LICS returning false, therefore
    // PUM is not true in every row, thus resulting in an FUV with false values and a launch return of "NO"
    @Test
    void testDecideFalse(){
        setLCM("ANDD");
        parameters.setLength1(500);
        Decide dec = new Decide(numPoints, points, parameters, lcm, puv);
        assertEquals("NO", dec.getLaunch());
    }


}
