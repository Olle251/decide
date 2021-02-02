import static org.junit.jupiter.api.Assertions.*;
import java.awt.geom.Point2D;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class PUMTest {

    private ArrayList<ArrayList<String>> LCMOrr;
    PUM pumtest;
    private ArrayList<ArrayList<String>> LCMAndd;
    private ArrayList<ArrayList<String>> LCMNotUsed;
    ArrayList<Boolean> cmv;
    ArrayList<Boolean> cmvFalse;

    @BeforeEach
    void setUp() {
        cmv = new ArrayList<>();
        cmvFalse = new ArrayList<>();
        LCMAndd = new ArrayList<>();
        LCMNotUsed = new ArrayList<>();
        LCMOrr = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            ArrayList<String> tmp = new ArrayList<>();
            ArrayList<String> tmp2 = new ArrayList<>();
            ArrayList<String> tmp3 = new ArrayList<>();
            cmv.add(true);
            cmvFalse.add(false);
            for (int j = 0; j < 15; j++){
                tmp.add("ORR");
                tmp2.add("ANDD");
                tmp3.add("NOTUSED");
            }
            LCMOrr.add(tmp);
            LCMAndd.add(tmp2);
            LCMNotUsed.add(tmp3);
        }


    }

    @AfterEach
    void tearDown() {
    }

    //Simple get test for PUM class that the returned object is a arraylist of arraylists.
    @Test
    void PUMTestGet() {
        pumtest =  new PUM(cmv,LCMAndd);
        assertTrue(pumtest.getPUM() instanceof ArrayList);
        assertTrue(pumtest.getPUM().get(0) instanceof ArrayList);


    }
    // PUM should be filled with true when LCM is filled with ANDD and CMV is filled with True.
    @Test
    void PUMTestAnddCMVTrue() {

        pumtest = new PUM(cmv,LCMAndd);
        ArrayList<ArrayList<Boolean>> expectedPUM = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            ArrayList<Boolean> tmp = new ArrayList<>();
            for (int j = 0; j < 15; j++){
                tmp.add(true);
            }
            expectedPUM.add(tmp);
        }
        assertTrue(pumtest.getPUM().equals(expectedPUM));
    }
    // PUM should be filled with true when LCM is filled with ORR and CMV is filled with True.
    @Test
    void PUMTestOrrCMVTrue(){
        pumtest = new PUM(cmv,LCMOrr);
        ArrayList<ArrayList<Boolean>> expectedPUM = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            ArrayList<Boolean> tmp = new ArrayList<>();
            for (int j = 0; j < 15; j++){
                tmp.add(true);
            }
            expectedPUM.add(tmp);
        }
        assertTrue(pumtest.getPUM().equals(expectedPUM));

    }
    // PUM should be filled with false when LCM is filled with ORR and CMV is filled with false.
    @Test
    void PUMTestOrrCMVFalse(){
        pumtest = new PUM(cmvFalse,LCMOrr);
        ArrayList<ArrayList<Boolean>> expectedPUM = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            ArrayList<Boolean> tmp = new ArrayList<>();
            for (int j = 0; j < 15; j++){
                tmp.add(false);
            }
            expectedPUM.add(tmp);
        }
        assertTrue(pumtest.getPUM().equals(expectedPUM));

    }
    // PUM should be filled with false when LCM is filled with ANDD and CMV is filled false.
    @Test
    void PUMTestAnddCMVFalse(){
        pumtest = new PUM(cmvFalse,LCMAndd);
        ArrayList<ArrayList<Boolean>> expectedPUM = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            ArrayList<Boolean> tmp = new ArrayList<>();
            for (int j = 0; j < 15; j++){
                tmp.add(false);
            }
            expectedPUM.add(tmp);
        }
        assertTrue(pumtest.getPUM().equals(expectedPUM));

    }
    // PUM should be true when LCM is filled with NotUsed,no matter what CMV is.
    @Test
    void PUMTestNotUsedCMVFalse(){
        pumtest = new PUM(cmvFalse,LCMNotUsed);
        ArrayList<ArrayList<Boolean>> expectedPUM = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            ArrayList<Boolean> tmp = new ArrayList<>();
            for (int j = 0; j < 15; j++){
                tmp.add(true);
            }
            expectedPUM.add(tmp);
        }
        assertTrue(pumtest.getPUM().equals(expectedPUM));

    }

    // PUM should be true when LCM is filled with NotUsed,no matter what CMV is.
    @Test
    void PUMTestNotUsedCMVTrue(){
        pumtest = new PUM(cmv,LCMNotUsed);
        ArrayList<ArrayList<Boolean>> expectedPUM = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            ArrayList<Boolean> tmp = new ArrayList<>();
            for (int j = 0; j < 15; j++){
                tmp.add(true);
            }
            expectedPUM.add(tmp);
        }
        assertTrue(pumtest.getPUM().equals(expectedPUM));

    }


}