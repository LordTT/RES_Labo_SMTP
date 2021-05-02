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

    private String serverAdress;
    private int serverPort;

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
        Socket clientSocket;
        OutputStream os;
        InputStream is;

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

            //Read the first line
            LOG.log(Level.INFO, in.readLine());

            //EHLO
            out.println("EHLO pranker");
            out.flush();

            //Read server infos
            String SMTPconfig = in.readLine();
            LOG.log(Level.INFO, SMTPconfig);
            while (SMTPconfig.contentEquals("250-")) {
                SMTPconfig = in.readLine();
                LOG.log(Level.INFO, SMTPconfig);
            }

            //sender
            out.println("MAIL From:<" + mail.getFrom() + ">");
            out.flush();
            LOG.log(Level.INFO, in.readLine());

            //receivers
            for (String to : mail.getTo()) {
                out.println("RCPT TO:<" + to + ">");
                out.flush();
                LOG.log(Level.INFO, in.readLine());
            }

            //Start data writing
            out.println("DATA");
            out.flush();
            LOG.log(Level.INFO, in.readLine());

            //encoding
            out.write("Content-Type: text/plain; charset=\"utf-8\"\r\n");

            //sender
            out.write("From: " + mail.getFrom() + "\r\n");

            //receivers
            out.write("To: " + mail.getTo()[0]);
            for (int i = 1; i < mail.getTo().length; ++i) {
                out.write(", " + mail.getTo()[i]);
            }
            out.write("\r\n");

            //Message body and subject
            out.println(mail.getMessage());
            out.println(".");
            out.flush();
            LOG.log(Level.INFO, in.readLine());

            //Quit
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
     * @return the instance of the SmtpClient class
     */
    public static SmtpClient getInstance() {
        if (instance == null) {
            instance = new SmtpClient();
        }

        return instance;
    }

}
