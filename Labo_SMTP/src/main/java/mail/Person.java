package mail;

/**
 * Class representing a person
 */
public class Person {

    private String surname;
    private String name;
    private String address;

    /**
     * Constructor
     * @param surname surname of the person
     * @param name name of the person
     * @param adress email adress of the person
     */
    public Person(String surname, String name, String adress) {
        this.surname = surname;
        this.name = name;
        this.address = adress;
    }

    /**
     * Gets the surname of the person
     * @return surname of the person
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Gets the name of the person
     * @return name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email adress of the person
     * @return email adress of the person
     */
    public String getAddress() {
        return address;
    }
}
