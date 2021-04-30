package smtp;

import config.ConfigurationManager;
import mail.Mail;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.SocketHandler;

public class SmtpClient {

    private String serverAdress = "217.0.0.1";
    private int serverPort = 25;

    private static SmtpClient instance;


    private SmtpClient(){
        ConfigurationManager configurationManager = new ConfigurationManager();
        this.serverAdress = configurationManager.getPropValue("smtpServerAddress");
        this.serverPort = Integer.parseInt(configurationManager.getPropValue("smtpServerPort"));
    }

    public void send (Mail mail){
        Socket clientSocket = null;
        OutputStream os = null;
        InputStream is = null;

        try{
            clientSocket = new Socket(serverAdress, serverPort);

            os = clientSocket.getOutputStream();
            is = clientSocket.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(is));

            PrintWriter out = new PrintWriter(new OutputStreamWriter(os));

            if (in == null || out == null){
                throw new IOException("Failed stream opening");
            }

            System.out.println(in.readLine()); // lis le premier message

            out.println("HELO local");
            out.flush();

            String SMTPconfig = in.readLine(); // lit et affiches la config SMTP
            System.out.println(SMTPconfig);
            while( SMTPconfig.contentEquals("250-") ){
                SMTPconfig = in.readLine();
                System.out.println(SMTPconfig);
            }

            out.println("MAIL From:<" + mail.getFrom() + ">");
            out.flush();
            System.out.println(in.readLine());


            for (String to : mail.getTo()){
                out.println("RCPT TO:<" + to + ">");
                out.flush();
                System.out.println(in.readLine());
            }

            out.println("DATA");
            out.flush();
            System.out.println(in.readLine());


            out.write( "Content-Type: text/plain; charset=\"utf-8\"\r\n" );

            out.write( "From: " + mail.getFrom() + "\r\n" );

            out.write( "To: " + mail.getTo()[0] );

            for (int i =1; i < mail.getTo().length;++i){
                out.write( ", " + mail.getTo()[i] );
            }

            out.write( "\r\n" );

            out.println(mail.getMessage());

            out.println(".");
            out.flush();

            System.out.println(in.readLine());

            out.println("QUIT");
            out.flush();


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static SmtpClient getInstance(){
        if (instance == null) {
            instance = new SmtpClient();
        }

        return instance;
    }

}
