import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FUVTest {
    ArrayList<Boolean> puv;
    ArrayList<ArrayList<Boolean>> pum;
    @BeforeEach
    void setUp() {
        puv = new ArrayList<>();
        pum = new ArrayList<>();

        //initialize puv with false and true on every other element in the list.
        //initialize pum with all true.
        for (int i = 0; i < 15; i++){
            ArrayList<Boolean> tmp = new ArrayList<>();
            if (i % 2 == 0) puv.add(true);
            else puv.add(false);
            for (int j = 0; j < 15; j++){
                tmp.add(true);
            }
            pum.add(tmp);
        }
    }

    // If everything is true in PUM, then everything in FUV should also be true.
    @Test
    void generateFUVTestTrue() {
        FUV fuv = new FUV(pum, puv);
        ArrayList<Boolean> expectedFUV = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            expectedFUV.add(true);
        }
        assertTrue(fuv.getFUV().equals(expectedFUV));
    }

    //If one element in one row of PUM is set to false and the corresponding element in PUV is true, then the element in FUV should be false.
    //Therefore we check so that the FUV is not set to all true.
    @Test
    void generateFUVTestFalse() {
        pum.get(0).set(0, false);
        FUV fuv = new FUV(pum, puv);
        ArrayList<Boolean> expectedFUV = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            expectedFUV.add(true);
        }
        assertFalse(fuv.getFUV().equals(expectedFUV));
    }




}
