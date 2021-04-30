import config.ConfigurationManager;
import prank.Prank;
import prank.PrankGenerator;
import smtp.SmtpClient;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        PrankGenerator prankGenerator = new PrankGenerator();
        ArrayList<Prank> pranks = prankGenerator.createAllPranks();

        for (Prank prank : pranks) {
            prank.play();
        }
    }

}
