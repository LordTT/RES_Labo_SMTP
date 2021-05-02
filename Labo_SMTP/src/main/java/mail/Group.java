package mail;

import java.util.ArrayList;

/**
 * Class representing a group of people
 */
public class Group {
    private final ArrayList<Person> persons = new ArrayList<>();

    /**
     * Gets the members of the group
     *
     * @return Gets the members of the group
     */
    public ArrayList<Person> getGroup() {
        return persons;
    }

    /**
     * Adds a person the the group
     *
     * @param person Person to add to the group
     */
    public void addPerson(Person person) {
        this.persons.add(person);
    }
}
