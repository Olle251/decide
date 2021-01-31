import static org.junit.jupiter.api.Assertions.*;
import java.awt.geom.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class CMVTest {
    private Parameters parameters;
    private List<Point2D.Double> points;
    private List<Point2D.Double> points2;
    private int numpoints;
    private int numpoints2;
    private CMV cmv;
    private CMV cmv2;

    @BeforeEach
    void setUp() {
        parameters = new Parameters();
        points = Arrays.asList(new Point2D.Double(7.0,20.0), new Point2D.Double(9.0,25.0),
                new Point2D.Double(12.0,23.0), new Point2D.Double(15.0,21.0),new Point2D.Double(14.0,28.0),
                new Point2D.Double(25.0,32.0), new Point2D.Double(30.0,34.0), new Point2D.Double(35.0,45.0));
        points2 = Arrays.asList(new Point2D.Double(1.0,1.0), new Point2D.Double(2.0,2.0),
                new Point2D.Double(4.0,4.0));
        numpoints = this.points.size();
        numpoints2 = this.points2.size();
        cmv = new CMV(points, numpoints, parameters);
        cmv2 = new CMV(points2, numpoints2, parameters);
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
        cmv = new CMV(points2, numpoints2, parameters);
        assertFalse(cmv.lic0());
    }

    /** Checks if the method lic0 returns true when length1 is less than the distance between at least one pair of
     * consecutive points.
     */
    @Test
    void lic0TestTrue() {
        parameters.setLength1(2.0);
        cmv = new CMV(points2, numpoints2, parameters);
        assertTrue(cmv.lic0());
    }

    @Test
    void lic1TestFalse() {
        parameters.setRadius1(100);
        assertFalse(cmv.lic1());
    }

    @Test
    void lic1TestTrue() {
        parameters.setRadius1(1);
        assertTrue(cmv.lic1());
    }

    @Test
    void lic2TestTrue() {
        parameters.setEpsilon(0.0);
        assertTrue(cmv.lic2());
    }
    @Test
    void lic2TestFalse() {
        parameters.setEpsilon(Math.PI);
        assertFalse(cmv.lic2());
    }

    /** Checks if the method lic5 returns true when there exists a pair of consecutive points (p1, p2) where
     * p1's X-coordinate > p2's X-coordinate.
     */
    @Test
    void lic5True() {
        assertTrue(cmv.lic5());
    }

    /** Checks if the method lic5 returns false when all points are ordered by X-coordinates in ascending order.
     */
    @Test
    void lic5False() {
        assertFalse(cmv2.lic5());
    }
}