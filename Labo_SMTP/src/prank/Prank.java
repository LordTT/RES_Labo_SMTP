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

    public void setMessage(String message) {
        this.message = message;
    }

    //public Mail createMail(){} //ici on crée le mail a ensuite envoyer par smtp
}
