import java.util.ArrayList;



public class PUM {
    private ArrayList<ArrayList<Boolean>> PUMM;
    private ArrayList<Boolean> CMV;
    private ArrayList<ArrayList<String>> LCM;

    //Constructor inputs cmv and lcm and creates PUM + fills it with resulting values from calculations.
    public PUM(ArrayList<Boolean> cmv,ArrayList<ArrayList<String>> lcm){
        CMV = cmv;
        LCM = lcm;
        PUMM = new ArrayList<>(LCM.size());

        for(int i=0; i < LCM.size(); i++){
            PUMM.add(new ArrayList<>(LCM.size()));
            for(int j=0; j < LCM.size(); j++){
                PUMM.get(i).add(j,false);

            }
        }

        fillPUM();

    }



    /** Iterates over LCM and inserts the boolean value of the logical connector and the
     * corresponding values from CMV.
     * returns void
     */
    void fillPUM(){
        Boolean var;

        for(int i=0; i < LCM.size(); i++){

            for(int j=0; j < LCM.get(i).size(); j++){
                if(LCM.get(i).get(j).equals("ANDD")){
                    var = (CMV.get(i) && CMV.get(j));
                }
                else if(LCM.get(i).get(j).equals("ORR")){
                    var = (CMV.get(i) || CMV.get(j));
                }
                else if(LCM.get(i).get(j).equals("NOTUSED")){
                    var = true;
                }
                else { var = false;
                    System.out.println("invalid LCM");  //No real error handling here
                }

                PUMM.get(i).set(j,var);
            }
        }

    }

    //Returns PUM calculated by CMV and LCM
    public ArrayList<ArrayList<Boolean>> getPUM(){
        return PUMM;
    }
}
