import static org.junit.jupiter.api.Assertions.*;
import java.awt.geom.Point2D;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CMVTest {
    private Parameters parameters;
    private CMV cmvEightDistantPoints;
    private CMV cmvThreeClosePointsLine;

    @BeforeEach
    void setUp() {
        parameters = new Parameters();
        List<Point2D.Double> eightDistantPoints;
        List<Point2D.Double> threeClosePointsLine;
        int numPoints1;
        int numPoints2;
        eightDistantPoints = Arrays.asList(new Point2D.Double(7.0,20.0), new Point2D.Double(9.0,25.0),
                new Point2D.Double(6.0,20.0), new Point2D.Double(15.0,21.0),new Point2D.Double(14.0,28.0),
                new Point2D.Double(25.0,32.0), new Point2D.Double(30.0,34.0), new Point2D.Double(35.0,45.0));
        threeClosePointsLine = Arrays.asList(new Point2D.Double(1.0,1.0), new Point2D.Double(2.0,2.0),
                new Point2D.Double(4.0,4.0));
        numPoints1 = eightDistantPoints.size();
        numPoints2 = threeClosePointsLine.size();
        cmvEightDistantPoints = new CMV(eightDistantPoints, numPoints1, parameters);
        cmvThreeClosePointsLine = new CMV(threeClosePointsLine, numPoints2, parameters);
    }

    @AfterEach
    void tearDown() {
    }

    //Checks if all elements in the vector are true, when all lics are fulfilled.
    @Test
    void getCMV() {
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
        List<Point2D.Double> eightDistantPoints;
        int numPoints1;
        eightDistantPoints = Arrays.asList(new Point2D.Double(7.0,20.0), new Point2D.Double(9.0,25.0),
                new Point2D.Double(6.0,20.0), new Point2D.Double(15.0,21.0),new Point2D.Double(14.0,28.0),
                new Point2D.Double(25.0,32.0), new Point2D.Double(30.0,34.0), new Point2D.Double(35.0,45.0));
        numPoints1 = eightDistantPoints.size();
        CMV cmv = new CMV(eightDistantPoints, numPoints1, parameters);
        ArrayList<Boolean> cmvList = cmv.getCMV();
        ArrayList<Boolean> testList = new ArrayList();
        for ( int i = 0; i < 15; i++) {
            testList.add(true);
        }
        assertEquals(cmvList, testList);
    }

    @Test
    void createCMV() {
    }

    //Checks if the method lic0 returns false when length1 is greater than the distance between any pair of consecutive points.
    @Test
    void lic0TestFalse() {
        parameters.setLength1(3.0);
        assertFalse(cmvThreeClosePointsLine.lic0());
    }

    //Checks if the method lic0 returns true when length1 is less than the distance between at least one pair of consecutive points.
    @Test
    void lic0TestTrue() {
        parameters.setLength1(2.0);
        assertTrue(cmvThreeClosePointsLine.lic0());
    }

    //Checks if the method lic1 returns false when radius1 is greater than the circumRadius calculated.
    @Test
    void lic1TestFalse() {
        parameters.setRadius1(500);
        assertFalse(cmvEightDistantPoints.lic1());
    }

    //Checks if the method lic1 returns true when radius1 is less than the circumRadius calculated.
    @Test
    void lic1TestTrue() {
        parameters.setRadius1(1);
        assertTrue(cmvEightDistantPoints.lic1());
    }

    //Checks if the method lic2 returns false when epsilon is PI.
    @Test
    void lic2TestFalse() {
        parameters.setEpsilon(Math.PI);
        assertFalse(cmvEightDistantPoints.lic2());
    }
    //Checks if the method lic2 returns true when epsilon is 0.
    @Test
    void lic2TestTrue() {
        parameters.setEpsilon(0.0);
        assertTrue(cmvEightDistantPoints.lic2());
    }

    //Checks if lic3 returns false when all points are on one line and the area is positive.
    @Test
    void lic3TestFalse() {
        parameters.setArea1(0.1);
        assertFalse(cmvThreeClosePointsLine.lic3());
    }

    //Checks if lic3 returns true when there are 3 consecutive vertex points that form a triangle with Area greater than 2.0.
    @Test
    void lic3TestTrue() {
        parameters.setArea1(2.0);
        assertTrue(cmvEightDistantPoints.lic3());
    }

    //Checks if lic4 returns false when there is 2 quadrants.
    @Test
    void lic4TestFalse() {
        parameters.setQ_PTS(5);
        parameters.setQUADS(2);
        assertFalse(cmvEightDistantPoints.lic4());
    }

    //Checks if lic4 returns true when there is only 1 quadrant.
    @Test
    void lic4TestTrue() {
        parameters.setQUADS(1);
        parameters.setQ_PTS(5);
        assertTrue(cmvEightDistantPoints.lic4());
    }

    //Checks if the method lic5 returns false when all points are ordered by X-coordinates in ascending order.
    @Test
    void lic5TestFalse() {
        assertFalse(cmvThreeClosePointsLine.lic5());
    }

    //Checks if the method lic5 returns true when there exists a pair of consecutive points (p1, p2) where p1's X-coordinate > p2's X-coordinate.
    @Test
    void lic5TestTrue() {
        assertTrue(cmvEightDistantPoints.lic5());
    }

    // Checks if method lic6 return false when the distance is a very large number, and therefore should be bigger than the calculated distance.
    @Test
    void lic6TestFalse() {
        parameters.setDIST(100);
        parameters.setN_PTS(3);
        assertFalse(cmvThreeClosePointsLine.lic6());
    }

    //Checks if the method lic6 returns true when the distance is set to a small number, and therefore should be smaller than the calculated distance.
    @Test
    void lic6TestTrue() {
        parameters.setDIST(1);
        parameters.setN_PTS(3);
        assertFalse(cmvThreeClosePointsLine.lic6());
    }

    //Checks if lic7 returns false when length1 is greater than the distance between any set of two points separated by exactly K_PTS consecutive intervening.
    @Test
    void lic7TestFalse() {
        parameters.setK_PTS(1);
        parameters.setLength1(5.0);
        assertFalse(cmvThreeClosePointsLine.lic7());
    }

    // Checks if lic7 returns true when length1 is less than at least one set of two points separated by exactly K_PTS consecutive intervening.
    @Test
    void lic7TestTrue() {
        parameters.setK_PTS(1);
        parameters.setLength1(3.0);
        assertTrue(cmvThreeClosePointsLine.lic7());
    }

    // Checks if lic8 returns false when radius1 is greater than 500.
    @Test
    void lic8TestFalse() {
        parameters.setA_PTS(1);
        parameters.setB_PTS(2);
        parameters.setRadius1(500);
        assertFalse(cmvEightDistantPoints.lic8());
    }

    // Checks if lic8 returns true when radius1 is less than 0.1.
    @Test
    void lic8TestTrue() {
        parameters.setA_PTS(1);
        parameters.setB_PTS(2);
        parameters.setRadius1(0.1);
        assertTrue(cmvEightDistantPoints.lic8());
    }

    //Sets epsilon to PI to check for an angle smaller than 0 degrees or larger than 360 degrees. Should return false.
    @Test
    void lic9TestFalse() {
        parameters.setC_PTS(1);
        parameters.setD_PTS(2);
        parameters.setEpsilon(Math.PI);
        assertFalse(cmvEightDistantPoints.lic9());
    }

    //Sets epsilon to 0, which means every angle should return true unless all points are in a line or there are input issues.
    @Test
    void lic9TestTrue() {
        parameters.setC_PTS(1);
        parameters.setD_PTS(2);
        parameters.setEpsilon(0.0);
        assertTrue(cmvEightDistantPoints.lic9());
    }

    // Sets area1 to a large value which no points in the standard test cases can cover.
    @Test
    void lic10TestFalse() {
        parameters.setE_PTS(1);
        parameters.setF_PTS(2);
        parameters.setArea1(10000);
        assertFalse(cmvEightDistantPoints.lic10());
    }

    //Sets E_pts to a negative value which should cause method to return false
    @Test
    void lic10TestFalse2() {
        parameters.setE_PTS(-1);
        parameters.setF_PTS(2);
        parameters.setArea1(1);
        assertFalse(cmvEightDistantPoints.lic10());
    }

    //Sets area1 to a small value which the standard test case should cover
    @Test
    void lic10TestTrue() {
        parameters.setE_PTS(1);
        parameters.setF_PTS(2);
        parameters.setArea1(1);
        assertTrue(cmvEightDistantPoints.lic10());
    }

    // Checks if the method lic11 returns false when all points are ordered by X-coordinates in ascending order.
    @Test
    void lic11TestFalse() {
        parameters.setG_PTS(1);
        assertFalse(cmvThreeClosePointsLine.lic11());
    }

    // Checks if lic11 returns true when all points are ordered by X-coordinates in descending order.
    @Test
    void lic11TestTrue() {
        parameters.setG_PTS(1);
        assertTrue(cmvEightDistantPoints.lic11());
    }

    //Checks if lic12 returns false when length1 is greater or length2 is shorter than the distance between any points.
    @Test
    void lic12False() {
        parameters.setK_PTS(1);
        parameters.setLength1(1.0);
        parameters.setLength2(1.0);
        assertFalse(cmvThreeClosePointsLine.lic12());
        parameters.setLength1(5.0);
        parameters.setLength2(5.0);
        assertFalse(cmvThreeClosePointsLine.lic12());
    }

    //Checks if lic12 returns true when length1 is less and length2 is greater than a pair of K_PTS-separated points.
    @Test
    void lic12True() {
        parameters.setK_PTS(1);
        parameters.setLength1(3.0);
        parameters.setLength2(5.0);
        assertTrue(cmvThreeClosePointsLine.lic12());
    }

    //Checks if the method lic13 returns false when both radius1 and radius2 are set to 0.1
    @Test
    void lic13TestFalse() {
        parameters.setA_PTS(1);
        parameters.setB_PTS(2);
        parameters.setRadius1(0.1);
        parameters.setRadius2(0.1);
        assertFalse(cmvEightDistantPoints.lic13());
    }

    //Checks if the method lic13 returns true when both radius1 is set to 0.1 and radius2 is set to 500
    @Test
    void lic13TestTrue() {
        parameters.setA_PTS(1);
        parameters.setB_PTS(2);
        parameters.setRadius1(0.1);
        parameters.setRadius2(500);
        assertTrue(cmvEightDistantPoints.lic13());
    }

    //Sets area1 to a small value which the standard test case should evaluate to false
    @Test
    void lic14TestFalse() {
        parameters.setE_PTS(1);
        parameters.setF_PTS(2);
        parameters.setArea1(1);
        parameters.setArea2(1);
        assertFalse(cmvEightDistantPoints.lic14());
    }

    //Sets area2 to a large value which the standard test case should evaluate to true.
    @Test
    void lic14TestTrue() {
        parameters.setE_PTS(1);
        parameters.setF_PTS(2);
        parameters.setArea1(1);  //From previous tests this evaluates true for lic 10, which is the first part of the lic14 method.
        parameters.setArea2(10000);
        assertTrue(cmvEightDistantPoints.lic14());
    }
}