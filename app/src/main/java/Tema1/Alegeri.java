package Tema1;

import java.util.*;

/**
 * Alegeri class -> mainly parses through the input and handles errors
 */
public class Alegeri {
    /* Map which contains the id of alegere and the items of it */
    final private Map<String, ItemiAlegeri> alegeri;

    /**
     * Constructor for Alegeri.
     */
    public Alegeri() {
        this.alegeri = new HashMap<>();
    }

    /* All the methods of parsing commands and managind elections */

    /**
     * Parses input for creating elections.
     *
     * @param arguments the input string.
     * @return message indicating success or failure.
     */
    public String parseAlegeri(String arguments) {
        String[] alegere = arguments.split(" ", 2);
        return creareAlegeri(alegere[0], alegere[1]);
    }

    /**
     * Creates a new election.
     *
     * @param idAlegeri the ID of the election.
     * @param numeAlegeri the name of the election.
     * @return succes / error.
     */
    public String creareAlegeri(String idAlegeri, String numeAlegeri) {
        if (alegeri.containsKey(idAlegeri)) {
            return "EROARE: Deja exista alegeri cu id " + idAlegeri;
        } else {
            ItemiAlegeri itemi = new ItemiAlegeri(idAlegeri, numeAlegeri);
            alegeri.put(idAlegeri, itemi);
            return "S-au creat alegerile " + numeAlegeri;
        }
    }

    /**
     * Starts an election.
     *
     * @param idAlegeri the ID of the election.
     * @return succes / error.
     */
    public String pornire_alegeri(String idAlegeri) {
        ItemiAlegeri item = alegeri.get(idAlegeri);
        if (item == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }

        if (item.getStatus().equals("NEINCEPUT")) {
            item.setStatus("IN_CURS");
            return "Au pornit alegerile " + item.getName();
        } else {
            return "EROARE: Alegerile deja au inceput";
        }
    }

    /**
     * Parses input for adding circumscriptie to an election.
     *
     * @param arguments the input string.
     * @return succes / error message.
     */
    public String parseCircumscriptii(String arguments) {
        String[] param = arguments.split(" ", 3);
        String idAlegeri = param[0];
        String numeCircumscriptie = param[1];
        String regiune = param[2];

        ItemiAlegeri itemi = alegeri.get(idAlegeri);
        if (itemi == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }
        if (!itemi.getStatus().equals("IN_CURS")) {
            return "EROARE: Nu este perioada de votare";
        }

        return itemi.adaugaCircumscriptii(numeCircumscriptie, regiune);
    }

    /**
     * Parses input for removing circumscriptie from an election.
     *
     * @param arguments the input string.
     * @return succes / error message.
     */
    public String parseCircumscriptiiEliminare(String arguments) {
        String[] params = arguments.split(" ", 2);
        String idAlegeri = params[0];
        String numeCircumscriptie = params[1];

        ItemiAlegeri itemi = alegeri.get(idAlegeri);
        if (itemi == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }
        if (!itemi.getStatus().equals("IN_CURS")) {
            return "EROARE: Nu este perioada de votare";
        }

        return itemi.eliminareCircumscriptii(numeCircumscriptie);
    }

    /**
     * Parses input for adding a candidate to an election.
     *
     * @param arguments the input string.
     * @return succes / error message.
     */
    public String parseCandidat(String arguments) {
        String[] param = arguments.split(" ", 4);
        String idAlegeri = param[0];
        String CNP = param[1];
        int varsta = Integer.parseInt(param[2]);
        String nume = param[3];

        ItemiAlegeri itemi = alegeri.get(idAlegeri);
        if (itemi == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }

        return itemi.adaugaCandidat(CNP, varsta, nume);
    }

    /**
     * Parses input for removing a candidate from an election.
     *
     * @param arguments the intput string.
     * @return success / error message.
     */
    public String parseCandidatEliminare(String arguments) {
        String[] params = arguments.split(" ", 2);
        String idAlegeri = params[0];
        String CNP = params[1];

        ItemiAlegeri itemi = alegeri.get(idAlegeri);
        if (itemi == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }
        if (!itemi.getStatus().equals("IN_CURS")) {
            return "EROARE: Nu este perioada de votare";
        }

        return itemi.eliminareCandidat(CNP);
    }

    /**
     * Parses input for adding a voter to an election.
     *
     * @param arguments the input string.
     * @return success / error message.
     */
    public String parseVotant(String arguments) {
        String[] params = arguments.split(" ", 6);
        String idAlegeri = params[0];
        String numeCircumscriptie = params[1];
        String CNP = params[2];
        int varsta = Integer.parseInt(params[3]);
        String mana = params[4];
        String nume = params[5];

        ItemiAlegeri itemi = alegeri.get(idAlegeri);
        if (itemi == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }
        if (!itemi.getStatus().equals("IN_CURS")) {
            return "EROARE: Nu este perioada de votare";
        }

        return itemi.adaugareVotant(numeCircumscriptie, CNP, varsta, mana, nume);
    }

    /**
     * Parses input for listing all candidates in an election.
     *
     * @param arguments the ID of the election.
     * @return a list of candidates or an error message.
     */
    public String parseListaCandidati(String arguments) {
        ItemiAlegeri itemi = alegeri.get(arguments);
        if (itemi == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }
        if (!(itemi.getStatus().equals("IN_CURS") || itemi.getStatus().equals("TERMINAT"))) {
            return "EROARE: Inca nu au inceput alegerile";
        }

        return itemi.listaCandidati();
    }

    /**
     * Parses input for listing voters in a specific circumscriptie.
     *
     * @param arguments the input string.
     * @return a list of voters or an error message.
     */
    public String parseListaVotanti(String arguments) {
        String[] params = arguments.split(" ", 2);
        String idAlegeri = params[0];
        String numeCircumscriptie = params[1];

        ItemiAlegeri itemi = alegeri.get(idAlegeri);
        if (itemi == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }
        if (!(itemi.getStatus().equals("IN_CURS") || itemi.getStatus().equals("TERMINAT"))) {
            return "EROARE: Inca nu au inceput alegerile";
        }

        return itemi.listaVotanti(numeCircumscriptie);
    }

    /**
     * Parses input for recording a vote.
     *
     * @param arguments the input string.
     * @return success / error message.
     */
    public String parseVotare(String arguments) {
        String[] params = arguments.split(" ", 4);
        String idAlegeri = params[0];
        String nume_Circumscriptie = params[1];
        String CNPVotant = params[2];
        String CNPCandidat = params[3];

        ItemiAlegeri itemi = alegeri.get(idAlegeri);
        if (itemi == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }
        if (!itemi.getStatus().equals("IN_CURS")) {
            return "EROARE: Nu este perioada de votare";
        }

        return itemi.votare(nume_Circumscriptie, CNPVotant, CNPCandidat);
    }

    /**
     * Stops an election.
     *
     * @param arguments the ID of the election to stop.
     * @return a success / error message.
     */
    public String oprireAlegeri(String arguments) {
        ItemiAlegeri itemi = alegeri.get(arguments);
        if (itemi == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }
        if (!itemi.getStatus().equals("IN_CURS")) {
            return "EROARE: Nu este perioada de votare";
        }

        itemi.setStatus("TERMINAT");
        return "S-au terminat alegerile " + itemi.getName();
    }

    /**
     * Generates a report for a specific circumscriptie in an election.
     *
     * @param arguments the input string.
     * @return a detailed report for the circumscriptie or an error message.
     */
    public String parseRapotPerCircumscirptie(int command, String arguments) {
        String[] params = arguments.split(" ", 2);
        String idAlegeri = params[0];
        String numeCircumscriptie = params[1];

        ItemiAlegeri itemi = alegeri.get(idAlegeri);
        if (itemi == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }
        if (itemi.getStatus().equals("IN_CURS")) {
            return "EROARE: Inca nu s-a terminat votarea";
        }

        if (command == 11) {
            return itemi.raportVoturiPerCircumscriptie(numeCircumscriptie);
        } else {
            return itemi.analizaPerCircumscriptie(numeCircumscriptie);
        }
    }

    /**
     * Generates a national report for an election.
     *
     * @param arguments the ID of the election.
     * @return a detailed national report, or an error message.
     */
    public String raportVoturiNational(int command, String arguments) {
        ItemiAlegeri itemi = alegeri.get(arguments);
        if (itemi == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }
        if (!itemi.getStatus().equals("TERMINAT")) {
            return "EROARE: Inca nu s-a terminat votarea";
        }

        if(command == 12) {
            return itemi.raportVoturiNational(arguments);
        } else {
            return itemi.analizaNationala(arguments);
        }
    }

    /**
     * Checks for possible fraud in an election.
     *
     * @param arguments the ID of the election.
     * @return a detailed fraud report, or an error message.
     */
    public String parseFrauda(String arguments) {
        ItemiAlegeri itemi = alegeri.get(arguments);
        if (itemi == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }
        if (!itemi.getStatus().equals("TERMINAT")) {
            return "EROARE: Inca nu s-a terminat votarea";
        }

        return itemi.raportFrauda();
    }

    /**
     * Deletes an election from the system.
     *
     * @param arguments the ID of the election.
     * @return success / error message.
     */
    public String stergereAlegeri(String arguments) {
        ItemiAlegeri itemi = alegeri.get(arguments);
        if (itemi == null) {
            return "EROARE: Nu exista alegeri cu acest id";
        }

        alegeri.remove(arguments);
        return "S-au sters alegerile " + itemi.getName();
    }

    /**
     * Lists all the elections in the system.
     *
     * @return a list of elections.
     */
    public String listareAlegeri() {
        if (alegeri.isEmpty()) {
            return "GOL: Nu sunt alegeri";
        }

        String res = "Alegeri:\n";
        for (Map.Entry<String, ItemiAlegeri> entry : alegeri.entrySet()) {
            res += entry.getKey() + " " + entry.getValue().getName() + "\n";
        }

        return res;
    }
}