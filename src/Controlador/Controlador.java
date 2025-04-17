package Controlador;
import java.sql.SQLException;
import static Controlador.Menus_Demanar_Dades.*;
import static Model.DAO.EscolaDAO.*;
import static Model.DAO.SectorDAO.*;
import static Model.DAO.ViaDAO.*;
import static Vista.Vista.*;

public class Controlador {

    ///#####################################################################################################################################################################################///
    ///----------------------------------------------------------------------- MENU PRINCIPAL ----------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///
    /**
     * Mostra el menú principal de l'aplicació, permetent a l'usuari seleccionar diferents opcions
     * i navegar als submenús. El menú funciona amb bucle fins que l'usuari selecciona l'opció de sortida.
     *
     * El mètode maneja l'entrada de l'usuari a través d'una sèrie d'indicacions de menú, processa les seleccions a través d'una sentència switch i crida submenús específics.
     * i truca a mètodes específics del submenú basant-se en l'elecció de l'usuari. Les entrades no vàlides sol·licitaran
     * que el menú es mostri de nou.
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
                        menuEscaladors();
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
     * Si els usuaris seleccionen una opció no vàlida, el menú reapareixerà fins que s'introdueixi una opció vàlida o se seleccioneu l'opció sortir.
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
     * Maneja el flux de menús per a la gestió de sectors, permetent a l'usuari realitzar diverses
     * operacions CRUD relacionades amb els sectors.
     * Aquest mètode pot implicar interaccions amb la base de dades i llança una excepció en cas derrors SQL durant qualsevol de les operacions relacionades amb la base de dades.
     * cas d'errors SQL durant qualsevol de les operacions relacionades amb la base de dades.
     *
     * @throws SQLException Si es produeix un error en accedir a la base de dades.
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
     * Mostra i gestiona el menú d'operacions Via. Aquest mètode permet a l'usuari
     * realitzar diverses operacions CRUD relacionades amb Via a través de diferents opcions.
     * @throws SQLException si es produeix un error en accedir a la base de dades durant alguna
     * de les operacions «Via».
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
                    menuSectors();
            }
        } while (opcio != "-1");
    }

}