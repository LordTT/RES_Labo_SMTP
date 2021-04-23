package smtp;

import mail.Mail;

public class SmtpClient {

    private String serverAdress = "17.0.0.1";
    private int serverPort = 25;

    public SmtpClient(String smtpServeurAdresse, int smtpServeurPort ){
        this.serverAdress = smtpServeurAdresse;
        this.serverPort = smtpServeurPort;
    }

    public boolean send (Mail mail){
        return false;
    }
}
