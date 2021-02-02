import java.util.ArrayList;
import java.util.List;

/**
 * Takes PUM and PUV in the constructor and generates the FUV based on these two paramaters.
 */
public class FUV {
    private ArrayList<Boolean> fuv = new ArrayList<>();
    private ArrayList<Boolean> puv;
    private ArrayList<ArrayList<Boolean>> pum;

    public FUV(ArrayList<ArrayList<Boolean>> pum, ArrayList<Boolean> puv){
        this.puv = puv;
        this.pum = pum;

        for(int i = 0; i < puv.size(); i++) {
            if (!puv.get(i) || evaluatePUMRow(i)) {
                fuv.add(true);
            } else {
                fuv.add(false);
            }
        }
    }

    /**
     * Evaluates the elements of row i in PUM.
     * @param i index of the row.
     * @return  true if all elements are true, false otherwise.
     */
    private boolean evaluatePUMRow(int i) {
        for (int j = 0; j < pum.get(i).size(); j++) {
            if (!pum.get(i).get(j)) return false;
        }
        return true;
    }

    public List<Boolean> getFUV() {
        return fuv;
    }


}
