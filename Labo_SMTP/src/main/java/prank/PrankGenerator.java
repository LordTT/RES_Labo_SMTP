package prank;

import config.ConfigurationManager;
import mail.Group;
import mail.Person;

import java.util.ArrayList;
import java.util.Collections;

public class PrankGenerator {
    private ConfigurationManager configurationMananger;

    public PrankGenerator(ConfigurationManager configurationManager) {
        this.configurationMananger = configurationManager;
    }

    public ArrayList<Prank> createAllPranks() {
        ArrayList<Prank> pranks = new ArrayList<>();

        int nbGroups = Integer.parseInt(configurationMananger.getPropValue("nbGroups"));

        ArrayList<Person> persons = (ArrayList<Person>) configurationMananger.getVictims();

        ArrayList<Group> groups = createAllGroups(persons, nbGroups);

        for (Group group : groups) {
            Prank prank = new Prank();
            ArrayList<Person> targets = group.getGroup();
            Collections.shuffle(targets);
            prank.setSender(targets.remove(0));
            prank.setVictims(targets);

            prank.setMessage("sdalkgémlkgrsdakmléàgsraméklà"); //TODO récupérer un message au hazard de la config

            pranks.add(prank);

        }

        return pranks;
    }

    public ArrayList<Group> createAllGroups(ArrayList<Person> persons, int nbGroups) {

        if (persons.size() / nbGroups < 3) {
            throw new RuntimeException("Pas assez de personnes par groupe (Au moins 3 par groupe)");
        }

        Collections.shuffle(persons);

        ArrayList<Group> groups = new ArrayList<>();
        for (int i = 0; i < nbGroups; ++i) {
            groups.add(new Group());
        }

        int i = 0;
        while (persons.size() > 0) {
            groups.get(i).addPerson(persons.remove(0));
            i = (i + 1) % nbGroups;
        }

        return groups;
    }
}
