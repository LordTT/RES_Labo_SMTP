package prank;

import config.ConfigurationManager;
import mail.Mail;
import mail.Person;
import smtp.SmtpClient;

import java.util.ArrayList;

/**
 * This class contains all that is needed to send a prank
 */
public class Prank {
    private Person sender;
    private ArrayList<Person> victims = new ArrayList<>();

    //No constructor: this class wil be create in the PrankGenerator class

    /**
     * Sets the sender of the prank
     *
     * @param sender Person that will send the prank mail
     */
    public void setSender(Person sender) {
        this.sender = sender;
    }

    /**
     * Sets the victims of the prank
     *
     * @param victims An arrayList of Persons containing the victims of the prank
     */
    public void setVictims(ArrayList<Person> victims) {
        this.victims = victims;
    }

    /**
     * Plays the pranks : generates a mail with a random message from messages.json ans sends it to the MTP server
     */
    public void play() {
        Mail mail = new Mail();

        mail.setFrom(sender.getAddress());

        String[] to = new String[victims.size()];

        for (int i = 0; i < victims.size(); i++) {
            to[i] = victims.get(i).getAddress();
        }

        mail.setTo(to);

        //Gets a random message from messages.json
        mail.setMessage(new ConfigurationManager().getRandomMessage() + "\n\n" + sender.getSurname() + " " + sender.getName());

        SmtpClient.getInstance().send(mail);

    }
}
