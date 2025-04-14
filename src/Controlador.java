import Model.DAO.EscolaDAO;
import Model.Objects.Escola;
import java.util.Scanner;

import static Model.DAO.EscolaDAO.*;
import static Vista.Vista.*;

public class Controlador {
    private static final Scanner scanner = new Scanner(System.in);

    public static void Menu() {
        do {
            menuPrincipal();
            int opcio = solicitarInt("Introdueix el número de l'elecció:");
            switch (opcio) {
                case 1:
                    escolesMenu();
                    break;
                case 2:
                    sectorsMenu();
                    break;
                case 3:
                    menuVies();
                    break;
                case 4:
                    menuEscaladors();
                    break;
                case 5:
                    menuEstadistiques();
                    break;
                case 0:
                    System.out.println("Fins aviat!");
                    break;
                default:
                    System.out.println("Opció no vàlida.");
                    menuPrincipal();
            }
        } while (opcio != -1);
    }

    public static void escolesMenu() {
        do {
            menuEscoles();
            int opcio = solicitarInt("Introdueix el número de l'elecció:");
            switch (opcio) {
                case 1:
                    crearEscola();
                    break;
                case 2:
                    editarEscola();
                    break;
                case 3:
                    llistarUnaEscola(llistarEscola());
                    break;
                case 4:
                    llistarTotesEscoles();
                    break;
                case 5:
                    eliminarEscola();
                    break;
                case 0:
                    System.out.println("Tornant enrere...");
                    return;
                default:
                    System.out.println("Opció no vàlida.");
                    menuEscoles();
            }
        } while (opcio != -1);
    }

    public static void sectorsMenu() {
        do {
            menuSectors();
            int opcio = solicitarInt("Introdueix el número de l'elecció:");
            switch (opcio) {
                case 1:
                    crearSector();
                    break;
                case 2:
                    editarSector();
                    break;
                case 3:
                    llistarUnSector(llistarSector());
                    break;
                case 4:
                    llistarTotsSectors();
                    break;
                case 5:
                    eliminarSector();
                    break;
                case 0:
                    System.out.println("Tornant enrere...");
                    return;
                default:
                    System.out.println("Opció no vàlida.");
                    menuSectors();
            }
        } while (opcio != -1);
    }


































    ///---------------------------------------------------------------------///
    ///----------------------- PARTE DE LAS ESCUELAS -----------------------///
    ///---------------------------------------------------------------------///
    public static Escola crearEscola() {
        System.out.println("-----------------------------------");
        System.out.println("--------- CREAR UNA ESCOLA --------");
        System.out.println("-----------------------------------");
        String nom = solicitarString("Introdueix el nom de l'escola:");
        String municipi = solicitarString("Introdueix el municipi de l'escola:");
        String aproximacio = solicitarString("Descriu breument com s'arriba:");
        String popularitat = solicitarString("Introdueix el popularitat de l'escola (baixa, mitjana, alta):");
        String restriccions = solicitarString("Introdueix les restriccions de l'escola:");

        return new Escola(nom, municipi, aproximacio, popularitat, restriccions);
    }

    public static void editarEscola() {
        System.out.println("---------------------------------------");
        System.out.println("--------- MODIFICAR UNA ESCOLA --------");
        System.out.println("---------------------------------------");

        // Solicitar datos al usuario
        String nom = solicitarString("Quina escola vols modificar?");
        System.out.println("Camps disponibles per modificar:");
        System.out.println("- nom");
        System.out.println("- municipi");
        System.out.println("- aproximacio");
        System.out.println("- popularitat");
        System.out.println("- restriccions");
        String camp = solicitarString("Quin camp vols modificar?");
        String nouValor = solicitarString("Què vols posar?");

        // Validar el campo ingresado por el usuario
        switch (camp.toLowerCase()) {
            case "nom":
            case "municipi":
            case "aproximacio":
            case "popularitat":
            case "restriccions":
                // Llamar al método DAO para ejecutar la actualización
                modificarEscola(nom, camp, nouValor);
                break;
            default:
                System.out.println("El camp introduït no és vàlid. Operació cancel·lada.");
        }
    }

    public static int eliminarEscola() {
        System.out.println("--------------------------------------");
        System.out.println("--------- ELIMINAR UNA ESCOLA --------");
        System.out.println("--------------------------------------");
        Integer id = solicitarInt("Introdueix la id de l'escola que vols eliminar: ");

        return id;
    }

    public static int llistarEscola() {
        Integer id = solicitarInt("Quina escola vols veure?");
        return id;
    }

    ///---------------------------------------------------------------------///
    ///----------------------- PARTE DE LOS SECTORES -----------------------///
    ///---------------------------------------------------------------------///
    public static Escola crearSector() {
        System.out.println("-----------------------------------");
        System.out.println("--------- CREAR UNA ESCOLA --------");
        System.out.println("-----------------------------------");
        String nom = solicitarString("Introdueix el nom de l'escola:");
        String municipi = solicitarString("Introdueix el municipi de l'escola:");
        String aproximacio = solicitarString("Descriu breument com s'arriba:");
        String popularitat = solicitarString("Introdueix el popularitat de l'escola (baixa, mitjana, alta):");
        String restriccions = solicitarString("Introdueix les restriccions de l'escola:");

        return new Escola(nom, municipi, aproximacio, popularitat, restriccions);
    }

    public static void editarSector() {
        System.out.println("---------------------------------------");
        System.out.println("--------- MODIFICAR UNA ESCOLA --------");
        System.out.println("---------------------------------------");

        // Solicitar datos al usuario
        String nom = solicitarString("Quina escola vols modificar?");
        System.out.println("Camps disponibles per modificar:");
        System.out.println("- nom");
        System.out.println("- municipi");
        System.out.println("- aproximacio");
        System.out.println("- popularitat");
        System.out.println("- restriccions");
        String camp = solicitarString("Quin camp vols modificar?");
        String nouValor = solicitarString("Què vols posar?");

        // Validar el campo ingresado por el usuario
        switch (camp.toLowerCase()) {
            case "nom":
            case "municipi":
            case "aproximacio":
            case "popularitat":
            case "restriccions":
                // Llamar al método DAO para ejecutar la actualización
                modificarEscola(nom, camp, nouValor);
                break;
            default:
                System.out.println("El camp introduït no és vàlid. Operació cancel·lada.");
        }
    }

    public static int eliminarSector() {
        System.out.println("--------------------------------------");
        System.out.println("--------- ELIMINAR UNA ESCOLA --------");
        System.out.println("--------------------------------------");
        Integer id = solicitarInt("Introdueix la id de l'escola que vols eliminar: ");

        return id;
    }

    public static int llistarSector() {
        Integer id = solicitarInt("Quina escola vols veure?");
        return id;
    }






    ///------------------------------------------------------------------------///
    ///----------------------- DEMANAR DADES A L'USUARI -----------------------///
    ///------------------------------------------------------------------------///
    public static String solicitarString(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    public static Integer solicitarInt(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextInt();
    }
}
