package prank;

import config.ConfigurationManager;

import java.util.logging.Logger;

public class PrankGenerator {

    static final Logger LOG = Logger.getLogger( PrankGenerator.class.getName());

    private ConfigurationManager configurationMananger;


    public PrankGenerator(ConfigurationManager configurationManager){
        this.configurationMananger = configurationManager;
    }

    //Ici on peut générer les groupes de personnes et les pranks en allant les chercher dans la config
}
