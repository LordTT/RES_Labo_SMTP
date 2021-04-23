package prank;

import mail.Mail;
import mail.Person;

import java.util.ArrayList;

public class Prank {
    private Person sender;
    private final ArrayList<Person> victims = new ArrayList<>();
    private String message;

    //pas de constructeur car généré dans parnkGénérator

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public ArrayList<Person> getVictims() {
        return victims;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {//ici on crée le mail a ensuite envoyer par smtp
        this.message = message;
    }

    public Mail createMail(){
        Mail mail = new Mail();

        mail.setFrom(sender.getAdress());

        String[] to = new String[victims.size()];

        for(int i = 0; i < victims.size(); i++){
            to[i] = victims.get(i).getAdress();
        }

        mail.setMessage(message + "\n\n" + sender.getSurname() + " " + sender.getName());

        return mail;

    }
}
