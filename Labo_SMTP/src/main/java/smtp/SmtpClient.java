package smtp;

import config.ConfigurationManager;
import mail.Mail;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;

/**
 * An SMTP client, this calss is a singleton
 */
public class SmtpClient {

    static final Logger LOG = Logger.getLogger( SmtpClient.class.getName());

    private String serverAdress = "217.0.0.1";
    private int serverPort = 25;

    private static SmtpClient instance;


    /**
     * Private constructor for the singleton pattern
     */
    private SmtpClient() {
        ConfigurationManager configurationManager = new ConfigurationManager();
        this.serverAdress = configurationManager.getPropValue("smtpServerAddress");
        this.serverPort = Integer.parseInt(configurationManager.getPropValue("smtpServerPort"));
    }

    /**
     * This method sends a mail to the smtp server
     *
     * @param mail Mail so send
     */
    public void send(Mail mail) {
        Socket clientSocket = null;
        OutputStream os = null;
        InputStream is = null;

        try {
            LOG.log(Level.INFO, "Sending mail...");

            clientSocket = new Socket(serverAdress, serverPort);

            os = clientSocket.getOutputStream();
            is = clientSocket.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(is));

            PrintWriter out = new PrintWriter(new OutputStreamWriter(os));

            if (in == null || out == null) {
                throw new IOException("Failed stream opening");
            }

            LOG.log(Level.INFO, in.readLine()); // lis le premier message

            out.println("HELO local");
            out.flush();

            String SMTPconfig = in.readLine(); // lit et affiches la config SMTP
            LOG.log(Level.INFO, SMTPconfig); // lis le premier message
            while (SMTPconfig.contentEquals("250-")) {
                SMTPconfig = in.readLine();
                LOG.log(Level.INFO, SMTPconfig); // lis le premier message
            }

            out.println("MAIL From:<" + mail.getFrom() + ">");
            out.flush();
            LOG.log(Level.INFO, in.readLine());


            for (String to : mail.getTo()) {
                out.println("RCPT TO:<" + to + ">");
                out.flush();
                LOG.log(Level.INFO, in.readLine());
            }

            out.println("DATA");
            out.flush();
            LOG.log(Level.INFO, in.readLine());


            out.write("Content-Type: text/plain; charset=\"utf-8\"\r\n");

            out.write("From: " + mail.getFrom() + "\r\n");

            out.write("To: " + mail.getTo()[0]);

            for (int i = 1; i < mail.getTo().length; ++i) {
                out.write(", " + mail.getTo()[i]);
            }

            out.write("\r\n");

            out.println(mail.getMessage());

            out.println(".");
            out.flush();

            LOG.log(Level.INFO, in.readLine());

            out.println("QUIT");
            out.flush();

            LOG.log(Level.INFO, "Mail sent");


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the instance of SmtpClient or creates one if there is none
     *
     * @return
     */
    public static SmtpClient getInstance() {
        if (instance == null) {
            instance = new SmtpClient();
        }

        return instance;
    }

}
