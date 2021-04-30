import config.ConfigurationManager;
import prank.Prank;
import prank.PrankGenerator;
import smtp.SmtpClient;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        ConfigurationManager configurationManager;


        configurationManager = new ConfigurationManager();
        String serverAddress = configurationManager.getPropValue("smtpServerAddress");
        int serverPort = Integer.parseInt(configurationManager.getPropValue("smtpServerPort"));

        SmtpClient client = new SmtpClient(serverAddress, serverPort);

        PrankGenerator prankGenerator = new PrankGenerator();
        ArrayList<Prank> pranks = prankGenerator.createAllPranks();

        for (Prank prank : pranks) {
            client.send( prank.createMail());
        }
    }

}
