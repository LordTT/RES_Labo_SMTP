
import prank.Prank;
import prank.PrankGenerator;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        //Generates all the pranks from the json files
        PrankGenerator prankGenerator = new PrankGenerator();
        ArrayList<Prank> pranks = prankGenerator.createAllPranks();

        //plays all the pranks
        for (Prank prank : pranks) {
            prank.play();
        }
    }

}
