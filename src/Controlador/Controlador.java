package Controlador;
import java.sql.SQLException;
import static Controlador.Menus_Demanar_Dades.*;
import static Model.DAO.EscaladorsDAO.*;
import static Model.DAO.EscolaDAO.*;
import static Model.DAO.EstadistiquesVariesDAO.*;
import static Model.DAO.SectorDAO.*;
import static Model.DAO.ViaDAO.*;
import static Vista.Vista.*;

public class Controlador {

    ///#####################################################################################################################################################################################///
    ///----------------------------------------------------------------------- MENU PRINCIPAL ----------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///
    /**
     * Mostra el menú principal de l'aplicació, permetent a l'usuari seleccionar diferents opcions i navegar als submenús.
     * El menú funciona amb bucle fins que l'usuari selecciona l'opció de sortida.
     *
     * El mètode maneja l'entrada de l'usuari a través d'una sèrie d'indicacions de menú, processa les seleccions a
     * través d'una sentència switch i truca a mètodes específics del submenú basant-se en l'elecció de l'usuari.
     * Les entrades no vàlides sol·licitaran que el menú es mostri de nou.
     *
     * En cas d'excepcions, el missatge d'error s'imprimeix a la consola.
     */
    public static void Menu() {
        try {
            String opcio;
            do {
                menuPrincipal();
                opcio = solicitarString("Introdueix el número de l'elecció:");
                switch (opcio) {
                    case "1":
                        escolesMenu();
                        break;
                    case "2":
                        sectorsMenu();
                        break;
                    case "3":
                        viesMenu();
                        break;
                    case "4":
                        escaladorsMenu();
                        break;
                    case "5":
                        menuEstadistiques();
                        break;
                    case "0":
                        System.out.println("Fins aviat!");
                        break;
                    default:
                        System.out.println("Opció no vàlida.");
                        menuPrincipal();
                }
            } while (opcio != "-1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    ///#####################################################################################################################################################################################///
    ///-------------------------------------------------------------------- MENU DEL LES ESCOLES -------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///
    /**
     * Mostra i gestiona el menú d'operacions relacionades amb escoles.
     * Si els usuaris seleccionen una opció no vàlida, el menú reapareixerà fins que s'introdueixi una opció vàlida o
     * se seleccioni l'opció sortir. Aquest mètode pot implicar interaccions amb la base de dades.
     *
     * @throws SQLException si es produeix algun error a la base de dades durant operacions com la creació, actualització o llistat d'escoles.
     */
    public static void escolesMenu() throws SQLException {
        String opcio;
        do {
            menuEscoles();
            opcio = solicitarString("Introdueix el número de l'elecció:");
            switch (opcio) {
                case "1":
                    afegirEscola(crearEscola());
                    break;
                case "2":
                    editarEscola();
                    break;
                case "3":
                    llistarUnaEscola(llistarEscola());
                    break;
                case "4":
                    llistarTotesEscoles();
                    break;
                case "5":
                    eliminarEscola(esborrarEscola());
                    break;
                case "0":
                    System.out.println("Tornant enrere...");
                    return;
                default:
                    System.out.println("Opció no vàlida.");
                    menuEscoles();
            }
        } while (opcio != "-1");
    }

    ///#####################################################################################################################################################################################///
    ///--------------------------------------------------------------------- MENU DELS SECTORS ---------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///
    /**
     * Mostra i gestiona el menú d'operacions relacionades amb sectors.
     * Si els usuaris seleccionen una opció no vàlida, el menú reapareixerà fins que s'introdueixi una opció vàlida o
     * se seleccioni l'opció sortir. Aquest mètode pot implicar interaccions amb la base de dades.
     *
     * @throws SQLException si es produeix algun error a la base de dades durant operacions com la creació, actualització o llistat de sectors.
     */
    public static void sectorsMenu() throws SQLException {
        String opcio;
        do {
            menuSectors();
            opcio = solicitarString("Introdueix el número de l'elecció:");
            switch (opcio) {
                case "1":
                    afegirSector(crearSector());
                    break;
                case "2":
                    editarSector();
                    break;
                case "3":
                    llistarUnSector(llistarSector());
                    break;
                case "4":
                    llistarTotsSectors();
                    break;
                case "5":
                    eliminarSector(esborrarSector());
                    break;
                case "0":
                    System.out.println("Tornant enrere...");
                    return;
                default:
                    System.out.println("Opció no vàlida.");
                    menuSectors();
            }
        } while (opcio != "-1");
    }

    ///#####################################################################################################################################################################################///
    ///---------------------------------------------------------------------- MENU DE LES VIES ---------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///
    /**
     * Mostra i gestiona el menú d'operacions relacionades amb vies.
     * Si els usuaris seleccionen una opció no vàlida, el menú reapareixerà fins que s'introdueixi una opció vàlida o
     * se seleccioni l'opció sortir. Aquest mètode pot implicar interaccions amb la base de dades.
     *
     * @throws SQLException si es produeix algun error a la base de dades durant operacions com la creació, actualització o llistat de vies.
     */
    public static void viesMenu() throws SQLException {
        String opcio;
        do {
            menuVies();
            opcio = solicitarString("Introdueix el número de l'elecció:");
            switch (opcio) {
                case "1":
                    afegirVia(crearVia());
                    break;
                case "2":
                    editarVia();
                    break;
                case "3":
                    llistarUnaVia(llistarVia());
                    break;
                case "4":
                    llistarTotesVies();
                    break;
                case "5":
                    eliminarVia(esborrarVia());
                    break;
                case "0":
                    System.out.println("Tornant enrere...");
                    return;
                default:
                    System.out.println("Opció no vàlida.");
                    menuVies();
            }
        } while (opcio != "-1");
    }

    /**
     * Mostra i gestiona el menú d'operacions relacionades amb escaladors.
     * Si els usuaris seleccionen una opció no vàlida, el menú reapareixerà fins que s'introdueixi una opció vàlida o
     * se seleccioni l'opció sortir. Aquest mètode pot implicar interaccions amb la base de dades.
     *
     * @throws SQLException si es produeix algun error a la base de dades durant operacions com la creació, actualització o llistat d'escaladors.
     */
    public static void escaladorsMenu() throws SQLException {
        String opcio;
        do {
            menuEscaladors();
            opcio = solicitarString("Introdueix el número de l'elecció:");
            switch (opcio) {
                case "1":
                    afegirEscalador(crearEscalador());
                    break;
                case "2":
                    editarEscalador();
                    break;
                case "3":
                    llistarUnEscalador(llistarEscalador());
                    break;
                case "4":
                    llistarTotsEscaladors();
                    break;
                case "5":
                    eliminarEscalador(esborrarEscalador());
                    break;
                case "0":
                    System.out.println("Tornant enrere...");
                    return;
                default:
                    System.out.println("Opció no vàlida.");
                    menuEscaladors();
            }
        } while (opcio != "-1");
    }

    /**
     * Mostra i gestiona el menú d'operacions relacionades amb estadístiques més variades.
     * Si els usuaris seleccionen una opció no vàlida, el menú reapareixerà fins que s'introdueixi una opció vàlida o
     * se seleccioni l'opció sortir. Aquest mètode pot implicar interaccions amb la base de dades.
     *
     * @throws SQLException si es produeix algun error a la base de dades durant les operacions de SELECT.
     */
    public static void estadistiquesMenu() throws SQLException {
        String opcio;
        do {
            menuEscaladors();
            opcio = solicitarString("Introdueix el número de l'elecció:");
            switch (opcio) {
                case "1":
                    mostrarViesDisponibles(viesDisponibles());
                    break;
                case "2":
                    String[] dificultats = viesPerDificultat();
                    cercarViesPerDificultat(dificultats[0], dificultats[1]);
                    break;
                case "3":
                    cercarViesPerEstat(viesPerEstat());
                    break;
                case "4":
                    escolesAmbRestriccions();
                    break;
                case "5":
                    sectorsViesDisponibles(sectorsViesDisponibles());
                    break;
                case "6":
                    escaladorsAmbMateixNivell();
                    break;
                case "7":
                    viesRecentmentApte();
                    break;
                case "8":
                    viesMesLlarguesPerEscola(viesLlarguesEscola());
                    break;
                case "0":
                    System.out.println("Tornant enrere...");
                    return;
                default:
                    System.out.println("Opció no vàlida.");
                    menuEscaladors();
            }
        } while (opcio != "-1");
    }
}