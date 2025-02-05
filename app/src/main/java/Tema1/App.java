package Tema1;

import java.io.*;
import java.util.*;

public class App {
    private Scanner scanner;
    private Alegeri alegeri;

    public App(InputStream input) {
        this.scanner = new Scanner(input);
        this.alegeri = new Alegeri();
    }

    public void run() {
        while (true) {
            int command = scanner.nextInt();
            scanner.nextLine();

            /* The command interface */
            switch (command) {
                case 0:
                    System.out.println(alegeri.parseAlegeri(scanner.nextLine()));
                    break;
                case 1:
                    System.out.println(alegeri.pornire_alegeri(scanner.nextLine()));
                    break;
                case 2:
                    System.out.println(alegeri.parseCircumscriptii(scanner.nextLine()));
                    break;
                case 3:
                    System.out.println(alegeri.parseCircumscriptiiEliminare(scanner.nextLine()));
                    break;
                case 4:
                    System.out.println(alegeri.parseCandidat(scanner.nextLine()));
                    break;
                case 5:
                    System.out.println(alegeri.parseCandidatEliminare(scanner.nextLine()));
                    break;
                case 6:
                    System.out.println(alegeri.parseVotant(scanner.nextLine()));
                    break;
                case 7:
                    System.out.println(alegeri.parseListaCandidati(scanner.nextLine()));
                    break;
                case 8:
                    System.out.println(alegeri.parseListaVotanti(scanner.nextLine()));
                    break;
                case 9:
                    System.out.println(alegeri.parseVotare(scanner.nextLine()));
                    break;
                case 10:
                    System.out.println(alegeri.oprireAlegeri(scanner.nextLine()));
                    break;
                case 11:
                    System.out.println(alegeri.parseRapotPerCircumscirptie(command, scanner.nextLine()));
                    break;
                case 12:
                    System.out.println(alegeri.raportVoturiNational(command, scanner.nextLine()));
                    break;
                case 13:
                    System.out.println(alegeri.parseRapotPerCircumscirptie(command, scanner.nextLine()));
                    break;
                case 14:
                    System.out.println(alegeri.raportVoturiNational(command, scanner.nextLine()));
                    break;
                case 15:
                    System.out.println(alegeri.parseFrauda(scanner.nextLine()));
                    break;
                case 16:
                    System.out.println(alegeri.stergereAlegeri(scanner.nextLine()));
                    break;
                case 17:
                    System.out.println(alegeri.listareAlegeri());
                    break;
                case 18:
                    System.out.println("exit");
                    return;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}