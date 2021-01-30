import static org.junit.jupiter.api.Assertions.*;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class CMVTest {
    private Parameters parameters;
    private List<Pair<Double,Double>> points;
    private int numpoints;
    private CMV cmv;

    @BeforeEach
    void setUp() {
        parameters = new Parameters();
        points = Arrays.asList(new Pair<>(7.0,20.0), new Pair<>(9.0,25.0),
                new Pair<>(12.0,23.0), new Pair<>(15.0,21.0),new Pair<>(18.0,28.0),
                new Pair<>(25.0,32.0), new Pair<>(30.0,34.0), new Pair<>(35.0,45.0));
        numpoints = this.points.size();
        cmv = new CMV(points, numpoints, parameters);
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

}