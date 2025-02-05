package Tema1;

/**
 *  Human class -> parent for Candidat and Votant classes
 * Contains basic information about a human
 */
public abstract class Human implements Comparable<Human> {
    protected String nume;
    protected int varsta;
    protected String CNP;

    /**
     * Constructor for Human.
     *
     * @param CNP   CNP.
     * @param nume  Name of the person.
     * @param varsta Age of the person.
     */
    public Human(String CNP, String nume, int varsta) {
        this.CNP = CNP;
        this.nume = nume;
        this.varsta = varsta;
    }

    /* Getters */
    public String getNume() {
        return nume;
    }
    public String getCNP() {
        return CNP;
    }
    public int getVarsta() {
        return varsta;
    }

    /**
     * Override of the compareTo method for sorting.
     *
     * @param o Other Human object to compare to.
     * @return Comparison result based on CNP.
     */
    public int compareTo(Human o) {
        return this.CNP.compareTo(o.CNP);
    }

    /**
     * Method which prints the list of candidates / voters
     *
     * @return Formatted string with name, CNP, and age.
     */
    public String printLista() {
        return nume + " " + CNP + " " + varsta + "\n";
    }
}
