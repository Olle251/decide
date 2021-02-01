import static org.junit.jupiter.api.Assertions.*;
import java.awt.geom.Point2D;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class CMVTest {
    private Parameters parameters;
    private List<Point2D.Double> eightDistantPoints;
    private List<Point2D.Double> threeClosePointsLine;
    private int numPoints1;
    private int numPoints2;
    private CMV cmvEightDistantPoints;
    private CMV cmvThreeClosePointsLine;

    @BeforeEach
    void setUp() {
        parameters = new Parameters();
        eightDistantPoints = Arrays.asList(new Point2D.Double(7.0,20.0), new Point2D.Double(9.0,25.0),
                new Point2D.Double(6.0,20.0), new Point2D.Double(15.0,21.0),new Point2D.Double(14.0,28.0),
                new Point2D.Double(25.0,32.0), new Point2D.Double(30.0,34.0), new Point2D.Double(35.0,45.0));
        threeClosePointsLine = Arrays.asList(new Point2D.Double(1.0,1.0), new Point2D.Double(2.0,2.0),
                new Point2D.Double(4.0,4.0));
        numPoints1 = this.eightDistantPoints.size();
        numPoints2 = this.threeClosePointsLine.size();
        cmvEightDistantPoints = new CMV(eightDistantPoints, numPoints1, parameters);
        cmvThreeClosePointsLine = new CMV(threeClosePointsLine, numPoints2, parameters);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCMV() {
    }

    @Test
    void createCMV() {
    }

    /** Checks if the method lic0 returns false when length1 is greater than the distance between any pair of consecutive
     * points.
     */
    @Test
    void lic0TestFalse() {
        parameters.setLength1(3.0);
        assertFalse(cmvThreeClosePointsLine.lic0());
    }

    /** Checks if the method lic0 returns true when length1 is less than the distance between at least one pair of
     * consecutive points.
     */
    @Test
    void lic0TestTrue() {
        parameters.setLength1(2.0);
        assertTrue(cmvThreeClosePointsLine.lic0());
    }

    @Test
    void lic1TestFalse() {
        parameters.setRadius1(1000);
        assertFalse(cmvEightDistantPoints.lic1());
    }

    @Test
    void lic1TestTrue() {
        parameters.setRadius1(1);
        assertTrue(cmvEightDistantPoints.lic1());
    }

    @Test
    void lic2TestTrue() {
        parameters.setEpsilon(0.0);
        assertTrue(cmvEightDistantPoints.lic2());
    }
    @Test
    void lic2TestFalse() {
        parameters.setEpsilon(Math.PI);
        assertFalse(cmvEightDistantPoints.lic2());
    }

    /**
     * Checks if lic3 returns true when there are 3 consecutive vertex points that form a triangle with Area greater than 2.0.
     */
    @Test
    void lic3TestTrue() {
        parameters.setArea1(2.0);
        assertTrue(cmvEightDistantPoints.lic3());
    }

    /**
     * Checks if lic3 returns false when all points are on one line and the area is positive.
     */
    @Test
    void lic3TestFalse() {
        parameters.setArea1(0.1);
        assertFalse(cmvThreeClosePointsLine.lic3());
    }


    @Test
    void lic4TestTrue() {
        parameters.setQUADS(1);
        parameters.setQ_PTS(5);
        assertTrue(cmvEightDistantPoints.lic4());
    }

    @Test
    void lic4TestFalse() {
        parameters.setQ_PTS(5);
        parameters.setQUADS(2);
    }
    /** Checks if the method lic5 returns true when there exists a pair of consecutive points (p1, p2) where
     * p1's X-coordinate > p2's X-coordinate.
     */
    @Test
    void lic5TestTrue() {
        assertTrue(cmvEightDistantPoints.lic5());
    }

    /** Checks if the method lic5 returns false when all points are ordered by X-coordinates in ascending order.
     */
    @Test
    void lic5TestFalse() {
        assertFalse(cmvThreeClosePointsLine.lic5());
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

    /** Checks if lic7 returns false when length1 is greater than the distnance between any set of two points
     * separated by exactly K_PTS consecutive intervening.
     */
    @Test
    void lic7TestFalse() {
        parameters.setK_PTS(1);
        parameters.setLength1(5.0);
        assertFalse(cmvThreeClosePointsLine.lic7());
    }

    /** Checks if lic7 returns true when length1 is less than at least one set of two points separated by exactly
     * K_PTS consecutive intervening.
     */
    @Test
    void lic7TestTrue() {
        parameters.setK_PTS(1);
        parameters.setLength1(3.0);
        assertTrue(cmvThreeClosePointsLine.lic7());
    }

    @Test
    void lic8TestFalse() {
        parameters.setA_PTS(1);
        parameters.setB_PTS(2);
        parameters.setRadius1(500);
        assertFalse(cmvEightDistantPoints.lic8());
    }
    @Test
    void lic8TestTrue() {
        parameters.setA_PTS(1);
        parameters.setB_PTS(2);
        parameters.setRadius1(0.1);
        assertTrue(cmvEightDistantPoints.lic8());
    }

    /**
     * Sets epsilon to PI to check for an angle smaller than 0 degrees or larger than 360 degrees. Should return false.
     */
    @Test
    void lic9TestFalse() {
        parameters.setC_PTS(1);
        parameters.setD_PTS(2);
        parameters.setEpsilon(Math.PI);
        assertFalse(cmvEightDistantPoints.lic9());
    }

    /**
     * Sets epsilon to 0, which means every angle should return true unless all points are in a line or there are input issues.
     */
    @Test
    void lic9TestTrue() {
        parameters.setC_PTS(1);
        parameters.setD_PTS(2);
        parameters.setEpsilon(0.0);
        assertTrue(cmvEightDistantPoints.lic9());
    }


    /**
     * Sets area1 to a large value which no points in the standard test cases can cover.
     */

    @Test
    void lic10TestFalse() {
        parameters.setE_PTS(1);
        parameters.setF_PTS(2);
        parameters.setArea1(10000);
        assertFalse(cmvEightDistantPoints.lic10());
    }
    /**
     * Sets E_pts to a negative value which should cause method to return false
     */
    @Test
    void lic10TestFalse2() {
        parameters.setE_PTS(-1);
        parameters.setF_PTS(2);
        parameters.setArea1(1);
        assertFalse(cmvEightDistantPoints.lic10());
    }
    /**
     * Sets area1 to a small value which the standard test case should cover
     */
    @Test
    void lic10TestTrue() {
        parameters.setE_PTS(1);
        parameters.setF_PTS(2);
        parameters.setArea1(1);
        assertTrue(cmvEightDistantPoints.lic10());
    }
    /**
     * Checks if the method lic11 returns false when all points are ordered by X-coordinates in ascending order.
     */
    @Test
    void lic11TestFalse() {
        parameters.setG_PTS(1);
        assertFalse(cmvThreeClosePointsLine.lic11());
    }

    /**
     * Checks if lic11 returns true when all points are ordered by X-coordinates in descending order.
     */
    @Test
    void lic11TestTrue() {
        parameters.setG_PTS(1);
        assertTrue(cmvEightDistantPoints.lic11());
    }

    /**
     * Checks if the method lic13 returns false
     */
    @Test
    void lic13TestFalse() {
        parameters.setA_PTS(1);
        parameters.setB_PTS(2);
        parameters.setRadius1(0.1);
        parameters.setRadius2(0.1);
        assertFalse(cmvEightDistantPoints.lic13());
    }

    /**
     * ....
     */
    @Test
    void lic13TestTrue() {
        parameters.setA_PTS(1);
        parameters.setB_PTS(2);
        parameters.setRadius1(0.1);
        parameters.setRadius2(500);
        assertTrue(cmvEightDistantPoints.lic13());
    }
    /**
     * Sets area1 to a small value which the standard test case should evaluate to false
     */
    @Test
    void lic14TestFalse() {
        parameters.setE_PTS(1);
        parameters.setF_PTS(2);
        parameters.setArea1(1);
        parameters.setArea2(1);
        assertFalse(cmvEightDistantPoints.lic14());
    }
    /**
     * Sets area2 to a large value which the standard test case should evaluate to true.
     */
    @Test
    void lic14TestTrue() {
        parameters.setE_PTS(1);
        parameters.setF_PTS(2);
        parameters.setArea1(1);  //From previous tests this evaluates true for lic 10, which is the first part of the lic14 method.
        parameters.setArea2(10000);
        assertTrue(cmvEightDistantPoints.lic14());
    }
}