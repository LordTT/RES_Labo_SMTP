package prank;

import config.ConfigurationManager;
import mail.Mail;
import mail.Person;
import smtp.SmtpClient;

import java.util.ArrayList;

public class Prank {
    private Person sender;
    private ArrayList<Person> victims = new ArrayList<>();
    private String message;

    //pas de constructeur car généré dans parnkGénérator

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public ArrayList<Person> getVictims() {
        return victims;
    }

    public void setVictims(ArrayList<Person> victims){
        this.victims = victims;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {//ici on crée le mail a ensuite envoyer par smtp
        this.message = message;
    }

    public void play(){
        Mail mail = new Mail();

        mail.setFrom(sender.getAddress());

        String[] to = new String[getVictims().size()];

        for(int i = 0; i < getVictims().size(); i++){
            to[i] = getVictims().get(i).getAddress();
        }

        mail.setTo(to);

        mail.setMessage(message + "\n\n" + sender.getSurname() + " " + sender.getName());

        //récupéere un message random
        mail.setMessage(new ConfigurationManager().getRandomMessage() + "\n\n" + sender.getSurname() + " " + sender.getName());

        SmtpClient.getInstance().send(mail);

    }
}
