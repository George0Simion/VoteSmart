package Tema1;

/**
 * Circumscriptii class
 */
public class Circumscriptii {
    private String numeCircumscriptie;
    private String regiune;

    /**
     * Constructor for Circumscriptii.
     *
     * @param numeCircumscriptie Name of the circumscription.
     * @param regiune            Region of the circumscription.
     */
    public Circumscriptii(String nume, String regiune) {
        this.numeCircumscriptie = nume;
        this.regiune = regiune;
    }

    /* Getters and Setters */
    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }
    public void setNumeCircumscriptie(String numeCircumscriptie) {
        this.numeCircumscriptie = numeCircumscriptie;
    }
    public String getRegiune() {
        return regiune;
    }
    public void setRegiune(String regiune) {
        this.regiune = regiune;
    }
}
