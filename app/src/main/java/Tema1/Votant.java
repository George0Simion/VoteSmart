package Tema1;

/**
 * Class representing a voter, extends Human.
 */
public class Votant extends Human {
    private String indemanatic;
    private Circumscriptii circumscriptie;
    private boolean aVotat;

    /**
     * Constructor for Votant.
     *
     * @param nume            Name of the voter.
     * @param varsta          Age of the voter.
     * @param CNP             Unique identification number.
     * @param indemanatic     Whether the voter is able to vote.
     * @param circumscriptie  Circumscription the voter belongs to.
     */
    public Votant(String nume, int varsta, String CNP, String mana, Circumscriptii circumscriptie) {
        super(CNP, nume, varsta);
        this.indemanatic = mana;
        this.circumscriptie = circumscriptie;
        this.aVotat = false;
    }

    /* Getters and Setters */
    public void setNume(String nume) {
        this.nume = nume;
    }
    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }
    public void setCNP(String CNP) {
        this.CNP = CNP;
    }
    public void setIndemanatic(String mana) {
        this.indemanatic = mana;
    }
    public void setCircumscriptie(Circumscriptii circumscriptie) {
        this.circumscriptie = circumscriptie;
    }
    public Circumscriptii getCircumscriptie() {
        return circumscriptie;
    }
    public void setVot(boolean vot) {
        this.aVotat = vot;
    }
    public boolean getVot() {
        return aVotat;
    }
    public String getIndemanatic() {
        return indemanatic;
    }

    /**
     * Returns a string representation of the voter.
     *
     * @return Formatted string with name, CNP, and age.
     */
    @Override
    public String printLista() {
        return super.printLista();
    }
}
