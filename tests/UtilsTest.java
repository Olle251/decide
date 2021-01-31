import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {



    @BeforeEach
    void setUp() {

    }


    @Test
    void calculateDistanceTest() {
        Point2D.Double a = new Point2D.Double(1.0, 1.0);
        Point2D.Double b = new Point2D.Double(2.0, 2.0);

        assertEquals(Utils.calculateDistance(a,b), Math.sqrt(2));
    }


    @Test
    void calculateAngleTest() {
        Point2D.Double a = new Point2D.Double(1.0, 1.0);
        Point2D.Double b = new Point2D.Double(2.0, 2.0);
        Point2D.Double c = new Point2D.Double(3.0, 2.0);

        assertEquals(Utils.calculateAngle(a, b, c), (3.0*Math.PI)/4.0);
    }
}