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
    private List<Point2D.Double> threeClosePointsAscending;
    private int numPoints1;
    private int numPoints2;
    private CMV cmvEightDistantPoints;
    private CMV cmvThreeClosePointsAscending;

    @BeforeEach
    void setUp() {
        parameters = new Parameters();
        eightDistantPoints = Arrays.asList(new Point2D.Double(7.0,20.0), new Point2D.Double(9.0,25.0),
                new Point2D.Double(12.0,23.0), new Point2D.Double(15.0,21.0),new Point2D.Double(14.0,28.0),
                new Point2D.Double(25.0,32.0), new Point2D.Double(30.0,34.0), new Point2D.Double(35.0,45.0));
        threeClosePointsAscending = Arrays.asList(new Point2D.Double(1.0,1.0), new Point2D.Double(2.0,2.0),
                new Point2D.Double(4.0,4.0));
        numPoints1 = this.eightDistantPoints.size();
        numPoints2 = this.threeClosePointsAscending.size();
        cmvEightDistantPoints = new CMV(eightDistantPoints, numPoints1, parameters);
        cmvThreeClosePointsAscending = new CMV(threeClosePointsAscending, numPoints2, parameters);
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
        assertFalse(cmvThreeClosePointsAscending.lic0());
    }

    /** Checks if the method lic0 returns true when length1 is less than the distance between at least one pair of
     * consecutive points.
     */
    @Test
    void lic0TestTrue() {
        parameters.setLength1(2.0);
        assertTrue(cmvThreeClosePointsAscending.lic0());
    }

    @Test
    void lic1TestFalse() {
        parameters.setRadius1(100);
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

    /** Checks if the method lic5 returns true when there exists a pair of consecutive points (p1, p2) where
     * p1's X-coordinate > p2's X-coordinate.
     */
    @Test
    void lic5True() {
        assertTrue(cmvEightDistantPoints.lic5());
    }

    /** Checks if the method lic5 returns false when all points are ordered by X-coordinates in ascending order.
     */
    @Test
    void lic5False() {
        assertFalse(cmvThreeClosePointsAscending.lic5());
    }

    /** Checks if lic7 returns false when length1 is greater than the distnance between any set of two points
     * separated by exactly K_PTS consecutive intervening.
     */
    @Test
    void lic7False() {
        parameters.setK_PTS(1);
        parameters.setLength1(5.0);
        assertFalse(cmv2.lic7());
    }

    /** Checks if lic7 returns true when length1 is less than at least one set of two points separated by exactly
     * K_PTS consecutive intervening.
     */
    @Test
    void lic7True() {
        parameters.setK_PTS(1);
        parameters.setLength1(3.0);
        assertTrue(cmv2.lic7());
    }
}