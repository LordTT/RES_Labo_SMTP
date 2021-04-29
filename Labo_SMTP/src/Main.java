import config.ConfigurationManager;
import prank.Prank;
import prank.PrankGenerator;
import smtp.SmtpClient;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

   public static void main(String[] args){
       ConfigurationManager configurationManager;


       configurationManager = new ConfigurationManager();


       SmtpClient client = new SmtpClient("217.0.0.1",  25); //TODO récupérer depuis config

       PrankGenerator prankGenerator = new PrankGenerator(configurationManager);
       ArrayList<Prank> pranks = prankGenerator.createAllPranks();

       for (Prank prank : pranks) {
           client.send( prank.createMail());
       }
   }

}
