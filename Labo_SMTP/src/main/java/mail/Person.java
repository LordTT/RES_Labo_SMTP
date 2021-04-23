package mail;

public class Person {

    private String surname;
    private String name;
    private String adress;

    public Person(String surname, String name, String adress){
        this.surname = surname;
        this.name = name;
        this.adress = adress;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }
}
