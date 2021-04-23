package mail;

import java.util.ArrayList;

public class Group {
    private final ArrayList<Person> persons = new ArrayList<Person>();

    //pas de constructeur car généré dans prankGénérator

    public ArrayList<Person> getGroup() {
        return persons;
    }

    public void addPerson(Person person) {
        this.persons.add( person ) ;
    }
}
