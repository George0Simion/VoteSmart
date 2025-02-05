package Tema1;

/**
 * Fraude class -> stores the voters which commited frauda
 */
public class Fraude {
    String numeCircumscriptie;
    String CNP;
    String nume;

    /**
     * Constructor for Fraude.
     *
     * @param numeCircumscriptie Name of the circumscription.
     * @param CNP                Unique identification number of the voter.
     * @param nume               Name of the voter.
     */
    public Fraude(String numeCircumscriptie, String CNP, String nume) {
        this.numeCircumscriptie = numeCircumscriptie;
        this.CNP = CNP;
        this.nume = nume;
    }

    /* Getters */
    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }
    public String getCNP() {
        return CNP;
    }
    public String getNume() {
        return nume;
    }
}
