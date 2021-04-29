package smtp;

import mail.Mail;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.SocketHandler;

public class SmtpClient {

    private String serverAdress = "217.0.0.1";
    private int serverPort = 25;

    public SmtpClient(String smtpServeurAdresse, int smtpServeurPort ){
        this.serverAdress = smtpServeurAdresse;
        this.serverPort = smtpServeurPort;
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

            String SMTPconfig = in.readLine(); // lit et affiches la config SMTP
            System.out.println(SMTPconfig);
            while( SMTPconfig.contentEquals("250-") ){
                SMTPconfig = in.readLine();
                System.out.println(SMTPconfig);
            }

            out.println("MAIL From:<" + mail.getFrom() + ">");
            System.out.println(in.readLine());
            out.flush();

            for (String to : mail.getTo()){
                out.println("RCPT TO:<" + to + ">");
                System.out.println(in.readLine());
            }
            out.flush();

            out.println("DATA");
            System.out.println(in.readLine());


            out.write( "Content-Type: text/plain; charset=\"utf-8\"\r\n" );

            out.write( "From: " + mail.getFrom() + "\r\n" );

            out.write( "To: " + mail.getTo()[0] );

            for (int i =1; i < mail.getTo().length;++i){
                out.write( ", " + mail.getTo()[i] );
            }

            out.write( "\r\n" );


            out.println(mail.getMessage());

            System.out.println(in.readLine());


            out.println("QUIT");

            System.out.println(in.readLine());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
