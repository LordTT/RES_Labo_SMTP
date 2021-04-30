package prank;

import config.ConfigurationManager;
import mail.Mail;
import mail.Person;
import smtp.SmtpClient;

import java.util.ArrayList;
import java.util.Collections;

public class Prank {
    private Person sender;
    private ArrayList<Person> victims = new ArrayList<>();


    //pas de constructeur car généré dans prankGenerator

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public ArrayList<Person> getVictims() {
        return victims;
    }

    public void setVictims(ArrayList<Person> victims){
        this.victims = victims;
    }

    public void play(){
        Mail mail = new Mail();

        mail.setFrom(sender.getAddress());

        String[] to = new String[getVictims().size()];

        for(int i = 0; i < getVictims().size(); i++){
            to[i] = getVictims().get(i).getAddress();
        }

//récupéere un message random
        mail.setMessage("uwu" + "\n\n" + sender.getSurname() + " " + sender.getName());

        SmtpClient.getInstance().send(mail);

    }
}
