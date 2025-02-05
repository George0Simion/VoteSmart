package Tema1;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a candidate, extends Human.
 */
public class Candidat extends Human {
    /**
     * Map containing the number of votes per circumscription.
     * Key: circumscription name, Value: number of votes.
     */
    final private Map<String, Integer> voturiPerCircumscriptie;

    /**
     * Constructor for Candidat.
     *
     * @param CNP   Unique identification number.
     * @param varsta Age of the candidate.
     * @param nume  Name of the candidate.
     */
    public Candidat(String CNP, int varsta, String nume) {
        super(CNP, nume, varsta);
        this.voturiPerCircumscriptie = new HashMap<>();
    }

    /* Setters */
    public void setCNP(String CNP) {
        this.CNP = CNP;
    }
    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Overriding the printing method in Human class
     *
     * @return Formatted string with name, CNP, and age.
     */
    @Override
    public String printLista() {
        return super.printLista();
    }

    /**
     *  Adding a vote for the respective candidate in the respective circumscriptie
     */
    public void addVote(String numeCircum) {
        voturiPerCircumscriptie.merge(numeCircum, 1, Integer::sum);
    }

    /**
     * Returns the number of votes a candidate has in the current circumscriptie
     */
    public int getVoturiPerCircumscriptie(String numeCircum) {
        Integer votes = voturiPerCircumscriptie.get(numeCircum);
        return votes != null ? votes : 0;
    }

    /**
     * Returns the total number of votes of a candidate
     */
    public int getTotalVoturi() {
        return voturiPerCircumscriptie.values().stream().mapToInt(Integer::intValue).sum();
    }
}
