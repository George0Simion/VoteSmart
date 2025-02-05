package Tema1;

import java.util.*;

/**
 * Class which keeps data about each alegere, and the stuff each alegere needs
 */
public class ItemiAlegeri {
    final private String id;
    final private String name;
    private String status;
    final private Map<String, Circumscriptii> circum;
    final private Map<String, Candidat> candidat;
    final private Map<String, Votant> votant;
    final private Deque<Fraude> frauda;

    /**
     * Constructor for ItemiAlegeri.
     *
     * @param id   Election ID.
     * @param name Election name.
     */
    public ItemiAlegeri(String id, String name) {
        this.id = id;
        this.name = name;
        this.status = "NEINCEPUT";
        this.circum = new HashMap<>();
        this.candidat = new HashMap<>();
        this.votant = new HashMap<>();
        this.frauda = new ArrayDeque<>();
    }

    /* Getters and Setters */
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Circumscriptii getCircum(String nume_circumscriptii) {
        return circum.get(nume_circumscriptii);
    }
    public Candidat getCandidat(String nume_candidat) {
        return candidat.get(nume_candidat);
    }
    public Candidat getCNP(String CNP) {
        return candidat.get(CNP);
    }

    /* Methods for the alegere */

    /**
     * Records a fraud attempt by a voter.
     *
     * @param numeCircumscriptie the name of the circumscriptie.
     * @param CNPVotant          the CNP of the voter.
     * @param numeVotant         the name of the voter.
     */
    public void storeFrauda(String numeCircumscriptie, String CNPVotant, String numeVotant) {
        Fraude fr= new Fraude(numeCircumscriptie, CNPVotant, numeVotant);
        frauda.push(fr);
    }

    /**
     * Adds a circumscriptie to the election.
     *
     * @param numeCircumscriptii the name of the circumscriptie.
     * @param regiune            the region of the circumscriptie.
     * @return a success message if the circumscriptie was added, or an error message if it already exists.
     */
    public String adaugaCircumscriptii(String numeCircumscriptii, String regiune) {
        if (circum.containsKey(numeCircumscriptii)) {
            return "EROARE: Deja exista o circumscriptie cu numele " + numeCircumscriptii;
        }

        Circumscriptii circumscriptie = new Circumscriptii(numeCircumscriptii, regiune);
        circum.put(numeCircumscriptii, circumscriptie);
        return "S-a adaugat circumscriptia " + numeCircumscriptii;
    }

    /**
     * Removes a circumscriptie from the election.
     *
     * @param numeCircumscriptii the name of the circumscriptie.
     * @return a success message if the circumscriptie was removed, or an error message if it doesn't exist.
     */
    public String eliminareCircumscriptii(String numeCircumscriptii) {
        if (circum.containsKey(numeCircumscriptii)) {
            circum.remove(numeCircumscriptii);
            return "S-a sters circumscriptia " + numeCircumscriptii;
        }

        return "EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptii;
    }

    /**
     * Adds a candidate to the election.
     *
     * @param CNP   the CNP of the candidate.
     * @param varsta the age of the candidate.
     * @param nume  the name of the candidate.
     * @return a success message if the candidate was added, or an error message if there were validation errors.
     */
    public String adaugaCandidat(String CNP, int varsta, String nume) {
        if (this.status.equals("NEINCEPUT")) {
            return "EROARE: Nu este perioada de votare";
        }
        if (CNP.length() != 13) {
            return "EROARE: CNP invalid";
        }
        if (varsta < 35) {
            return "EROARE: Varsta invalida";
        }
        if (candidat.containsKey(CNP)) {
            Candidat existent = candidat.get(CNP);
            return "EROARE: Candidatul " + existent.getNume() + " are deja acelasi CNP";
        }

        Candidat nou = new Candidat(CNP, varsta, nume);
        candidat.put(CNP, nou);
        return "S-a adaugat candidatul " + nume;
    }

    /**
     * Removes a candidate from the election.
     *
     * @param CNP the CNP of the candidate to remove.
     * @return a success message if the candidate was removed, or an error message if the candidate doesn't exist.
     */
    public String eliminareCandidat(String CNP) {
        if (candidat.containsKey(CNP)) {
            Candidat elim = candidat.get(CNP);
            candidat.remove(CNP);
            return "S-a sters candidatul " + elim.getNume();
        }

        return "EROARE: Nu exista un candidat cu CNP-ul " + CNP;
    }

    /**
     * Adds a voter to the election.
     *
     * @param numeCircumscriptie the name of the constituency the voter belongs to.
     * @param CNP                the CNP of the voter.
     * @param varsta             the age of the voter.
     * @param mana               the hand preference of the voter (left or right-handed).
     * @param nume               the name of the voter.
     * @return a success message if the voter was added, or an error message if there were validation errors.
     */
    public String adaugareVotant(String numeCircumscriptie, String CNP, int varsta, String mana, String nume) {
        if (CNP.length() != 13) {
            return "EROARE: CNP invalid";
        }
        if (varsta < 18) {
            return "EROARE: Varsta invalida";
        }
        if (!circum.containsKey(numeCircumscriptie)) {
            return "EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie;
        }
        if (votant.containsKey(CNP)) {
            Votant existent = votant.get(CNP);
            return "EROARE: Votantul " + existent.getNume() + " are deja acelasi CNP";
        }

        Votant nou = new Votant(nume, varsta, CNP, mana, circum.get(numeCircumscriptie));
        votant.put(CNP, nou);
        return "S-a adaugat votantul " + nume;
    }

    /**
     * Lists all candidates in the election.
     *
     * @return a string containing a list of all candidates, or a message indicating no candidates exist.
     */
    public String listaCandidati() {
        if (candidat.isEmpty()) {
            return "GOL: Nu sunt candidati";
        }

        ArrayList<Candidat> listaCandidati = new ArrayList<>(candidat.values());
        Collections.sort(listaCandidati);

        String res = "Candidatii:\n";
        for (Candidat c : listaCandidati) {
            res += c.printLista();
        }

        return res;
    }

    /**
     * Lists all voters in a specific constituency.
     *
     * @param numeCircumscriptie the name of the circumscriptie.
     * @return a string containing a list of voters in the specified circumscriptie, or an error message if the constituency doesn't exist.
     */
    public String listaVotanti(String numeCircumscriptie) {
        if (!circum.containsKey(numeCircumscriptie)) {
            return "EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie;
        }

        ArrayList<Votant> listaVotanti = new ArrayList<>();
        for (Votant v : votant.values()) {
            if (v.getCircumscriptie().getNumeCircumscriptie().equals(numeCircumscriptie)) {
                listaVotanti.add(v);
            }
        }

        if (listaVotanti.isEmpty()) {
            return "GOL: Nu sunt votanti in " + numeCircumscriptie;
        }

        Collections.sort(listaVotanti);

        String res = "Votantii din " + numeCircumscriptie + ":\n";
        for (Votant v : listaVotanti) {
            res += v.printLista();
        }

        return res;
    }

    /**
     * Records a vote from a voter to a candidate in a specific ccircumscriptie.
     *
     * @param numeCircumscriptie the name of the circumscriptie.
     * @param CNPVotant          the CNP of the voter.
     * @param CNPCandidat        the CNP of the candidate.
     * @return a message indicating success, or an error/fraud message if there are issues with the vote.
     */
    public String votare(String numeCircumscriptie, String CNPVotant, String CNPCandidat) {
        if (!circum.containsKey(numeCircumscriptie)) {
            return "EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie;
        }
        if (!votant.containsKey(CNPVotant)) {
            return "EROARE: Nu exista un votant cu CNP-ul " + CNPVotant;
        }
        if (!candidat.containsKey(CNPCandidat)) {
            return "EROARE: Nu exista un candidat cu CNP-ul " + CNPCandidat;
        }

        if (votant.get(CNPVotant).getVot()) {
            storeFrauda(numeCircumscriptie, CNPVotant, votant.get(CNPVotant).getNume());
            return "FRAUDA: Votantul cu CNP-ul "
                    + CNPVotant
                    + " a incercat sa comita o frauda. Votul a fost anulat";
        }
        if (!votant.get(CNPVotant).getCircumscriptie().getNumeCircumscriptie().equals(numeCircumscriptie)) {
            storeFrauda(numeCircumscriptie, CNPVotant, votant.get(CNPVotant).getNume());
            return "FRAUDA: Votantul cu CNP-ul "
                    + CNPVotant
                    + " a incercat sa comita o frauda. Votul a fost anulat";
        }

        votant.get(CNPVotant).setVot(true);

        if (votant.get(CNPVotant).getIndemanatic().equals("nu")) {
            Candidat candidatVotat = candidat.get(CNPCandidat);
            candidatVotat.addVote(numeCircumscriptie);
        }

        return votant.get(CNPVotant).getNume() + " a votat pentru " + candidat.get(CNPCandidat).getNume();
    }

    /**
     * Generates a report of votes for a specific constituency.
     *
     * @param numeCircumscriptie the name of the constituency.
     * @return a string containing the vote report, or a message indicating no votes were cast in the constituency.
     */
    public String raportVoturiPerCircumscriptie(String numeCircumscriptie) {
        if (!circum.containsKey(numeCircumscriptie)) {
            return "EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie;
        }

        ArrayList<Candidat> candidatiVoturi = new ArrayList<>();
        for (Candidat c : candidat.values()) {
            int votes = c.getVoturiPerCircumscriptie(numeCircumscriptie);
            if (votes > 0) {
                candidatiVoturi.add(c);
            }
        }

        if (candidatiVoturi.isEmpty()) {
            return "GOL: Lumea nu isi exercita dreptul de vot in " + numeCircumscriptie;
        }

        candidatiVoturi.sort(new Comparator<Candidat>() {
            public int compare(Candidat c1, Candidat c2) {
                int votes1 = c1.getVoturiPerCircumscriptie(numeCircumscriptie);
                int votes2 = c2.getVoturiPerCircumscriptie(numeCircumscriptie);

                if (votes1 != votes2) {
                    return votes2 - votes1;
                } else {
                    return c2.getCNP().compareTo(c1.getCNP());
                }
            }
        });

        String res = "Raport voturi " + numeCircumscriptie + ":\n";
        for (Candidat c : candidatiVoturi) {
            int votes = c.getVoturiPerCircumscriptie(numeCircumscriptie);
            res += c.getNume() + " " + c.getCNP() + " - " + votes + "\n";
        }

        return res;
    }

    /**
     * Generates a national vote report for the election.
     *
     * @param idAlegere the ID of the election.
     * @return a string containing the national vote report, or a message indicating no votes were cast.
     */
    public String raportVoturiNational(String idAlegere) {
        ArrayList<Candidat> candidati = new ArrayList<>();
        for (Candidat c : candidat.values()) {
            int votes = c.getTotalVoturi();
            if (votes > 0) {
                candidati.add(c);
            }
        }

        if (candidati.isEmpty()) {
            return "GOL: Lumea nu isi exercita dreptul de vot in Romania";
        }

        candidati.sort(new Comparator<Candidat>() {
            public int compare(Candidat c1, Candidat c2) {
                int votes1 = c1.getTotalVoturi();
                int votes2 = c2.getTotalVoturi();

                if (votes1 != votes2) {
                    return votes2 - votes1;
                } else {
                    return c2.getCNP().compareTo(c1.getCNP());
                }
            }
        });

        String res = "Raport voturi Romania:\n";
        for (Candidat c : candidati) {
            int votes = c.getTotalVoturi();
            res += c.getNume() + " " + c.getCNP() + " - " + votes + "\n";
        }

        return res;
    }

    /**
     * Analyzes the voting results for a specific constituency.
     *
     * @param numeCircumscriptie the name of the constituency.
     * @return a detailed analysis of the voting results in the constituency,
     *         or a message indicating no votes were cast.
     */
    public String analizaPerCircumscriptie(String numeCircumscriptie) {
        if (!circum.containsKey(numeCircumscriptie)) {
            return "EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie;
        }

        ArrayList<Candidat> candidatiVoturi = new ArrayList<>();
        int voturiCircum = 0;
        Candidat maxCandidatCircum = null;

        /* Iteram prin fiecare candidat si adunam voturile din fiecare circumscriptie */
        for (Candidat c : candidat.values()) {
            int votes = c.getVoturiPerCircumscriptie(numeCircumscriptie);
            if (votes > 0) {
                voturiCircum += votes;
                candidatiVoturi.add(c);

                /* Adaugam candidatul in lista ^
                    si updatam candidatul cu cele mai multe voturi v */

                if (maxCandidatCircum == null) {
                    maxCandidatCircum = c;
                } else if (votes >= maxCandidatCircum.getVoturiPerCircumscriptie(numeCircumscriptie)) {
                    maxCandidatCircum = c;
                }
            }
        }

        if (candidatiVoturi.isEmpty()) {
            return "GOL: Lumea nu isi exercita dreptul de vot in " + numeCircumscriptie;
        }

        /* Calculam numarul total de voturi */
        ArrayList<Candidat> candidati = new ArrayList<>();
        int voturiTotal = 0;
        for (Candidat c : candidat.values()) {
            int votes = c.getTotalVoturi();
            if (votes > 0) {
                voturiTotal += votes;
                candidati.add(c);
            }
        }

        return "In " + numeCircumscriptie + " au fost " + voturiCircum + " voturi din " + voturiTotal + "."
                + " Adica " + voturiCircum * 100 / voturiTotal + "%. Cele mai multe voturi au fost stranse de "
                + maxCandidatCircum.getCNP() + " " + maxCandidatCircum.getNume()
                + ". Acestea constituie " + maxCandidatCircum.getVoturiPerCircumscriptie(numeCircumscriptie) * 100 / voturiCircum
                + "% din voturile circumscriptiei.";
    }

    /**
     * Analyzes the national voting results.
     *
     * @param idAlegeri the ID of the election.
     * @return a detailed analysis of the national voting results,
     *         or a message indicating no votes were cast.
     */
    public String analizaNationala(String idAlegeri) {

        /* Clasa ajutatoare sa retinem voturile pe regiune */
        class InfoRegiune implements Comparable<InfoRegiune> {
            String regiune;
            int voturi = 0;
            String CNP_topCandidat = null;
            String Nume_topCandidat = null;
            int maxVoturi = 0;
            Map<String, Integer> voturiCandidat = new HashMap<>();

            public int compareTo(InfoRegiune infoRegiune) {
                return this.regiune.compareTo(infoRegiune.regiune);
            }
        }

        ArrayList<InfoRegiune> regiuni = new ArrayList<>();
        int totalVoturi = 0;

        /* Iteram prin fiecare circumscriptie si adunam voturile pe regiuni */
        for (Circumscriptii circumscriptii : circum.values()) {
            String regiune = circumscriptii.getRegiune();
            String numeCircumscriptie = circumscriptii.getNumeCircumscriptie();

            /* Cream / Gasim un nou obiect de tip regiuni */
            InfoRegiune infoRegiune = null;
            for (InfoRegiune r : regiuni) {
                if (r.regiune.equals(regiune)) {
                    infoRegiune = r;
                    break;
                }
            }
            if (infoRegiune == null) {
                infoRegiune = new InfoRegiune();
                infoRegiune.regiune = regiune;
                regiuni.add(infoRegiune);
            }

            /* Adunam voturile din fiecare circumscriptie */
            for (Candidat c : candidat.values()) {
                int votes = c.getVoturiPerCircumscriptie(numeCircumscriptie);

                if (votes > 0) {
                    totalVoturi += votes;
                    infoRegiune.voturi += votes;

                    /* Updatam voturile totale pentru candidat din regiune */
                    int voturiTotalCandidat = infoRegiune.voturiCandidat.getOrDefault(c.getCNP(), 0) + votes;
                    infoRegiune.voturiCandidat.put(c.getCNP(), voturiTotalCandidat);

                    /* Updatam candidatul cu cele mai multe voturi din regiune */
                    if (voturiTotalCandidat > infoRegiune.maxVoturi || (voturiTotalCandidat == infoRegiune.maxVoturi && (infoRegiune.CNP_topCandidat == null || c.getCNP().compareTo(infoRegiune.CNP_topCandidat) > 0))) {
                        infoRegiune.maxVoturi = voturiTotalCandidat;
                        infoRegiune.CNP_topCandidat = c.getCNP();
                        infoRegiune.Nume_topCandidat = c.getNume();
                    }
                }
            }
        }

        if (totalVoturi == 0) {
            return "GOL: Lumea nu isi exercita dreptul de vot in Romania";
        }

        /* Sortam */
        Collections.sort(regiuni);

        /* Output */
        String res = "In Romania au fost " + totalVoturi + " voturi.\n";
        for (InfoRegiune r : regiuni) {
            int procentajRegiune = r.voturi * 100 / totalVoturi;
            int procentajCandidate = r.maxVoturi * 100 / r.voturi;

            res += "In " + r.regiune + " au fost " + r.voturi + " voturi din " + totalVoturi
                    + ". Adica " + procentajRegiune + "%. Cele mai multe voturi au fost stranse de "
                    + r.CNP_topCandidat + " " + r.Nume_topCandidat + ". Acestea constituie "
                    + procentajCandidate + "% din voturile regiunii.\n";
        }

        return res;
    }

    /**
     * Generates a report of detected voting fraud attempts.
     *
     * @return a string listing all recorded fraud attempts, or a message indicating no fraud was detected.
     */
    public String raportFrauda() {
        if (frauda.isEmpty()) {
            return "GOL: Romanii sunt cinstiti";
        }

        String res = "Fraude comise:\n";
        Iterator<Fraude> it = frauda.descendingIterator();
        while (it.hasNext()) {
            Fraude f = it.next();
            res += "In " + f.getNumeCircumscriptie() + ": " + f.getCNP() + " " + f.getNume() + "\n";
        }

        return res;
    }
}
