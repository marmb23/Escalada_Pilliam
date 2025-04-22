package Controlador;

import Model.Objects.Escaladors;
import Model.Objects.Escola;
import Model.Objects.Sector;
import Model.Objects.Via;

import java.sql.SQLException;
import java.util.Scanner;
import static Model.DAO.EscolaDAO.modificarEscola;
import static Model.DAO.SectorDAO.modificarSector;
import static Model.DAO.ViaDAO.modificarVia;

public class Menus_Demanar_Dades {
    private static final Scanner scanner = new Scanner(System.in);

    ///#####################################################################################################################################################################################///
    ///--------------------------------------------------------------------------- ESCOLES -------------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///
    /**
     * Crea un nou objecte Escola després d'obtenir les dades necessàries com el nom, municipi, aproximació, popularitat
     * i restriccions de l'escola a través d'entrades de l'usuari.
     *
     * @return un objecte Escola amb les dades introduïdes.
     */
    public static Escola crearEscola() {
        System.out.println("+----------------- CREAR UNA ESCOLA -----------------+");
        System.out.println("+----------------------------------------------------+");

        // Solicitar les dades necessàries a l'usuari per crear l'objecte d'Escola.
        String nom = solicitarString("Introdueix el nom de l'escola:");
        String municipi = solicitarString("Introdueix el municipi de l'escola:");
        String aproximacio = solicitarString("Descriu breument com s'arriba:");
        String popularitat = solicitarString("Introdueix la popularitat de l'escola (baixa, mitjana, alta):").toLowerCase();
        String restriccions = solicitarString("Introdueix les restriccions de l'escola:");

        return new Escola(nom, municipi, aproximacio, popularitat, restriccions);
    }

    /**
     * Modifica les dades d'una escola a la base de dades segons la informació proporcionada per l'usuari.
     *
     * Aquesta funció permet a l'usuari seleccionar una escola pel seu nom i modificar un camp específic de les seves dades.
     * La funció valida el camp introduït abans d'intentar realitzar la modificació. Si el camp no és vàlid o no es troba
     * l'escola a la base de dades, es notifica a l'usuari.
     *
     * @throws SQLException Error en accedir o modificar la base de dades.
     */
    public static void editarEscola() throws SQLException {
        System.out.println("+--------------- MODIFICAR UNA ESCOLA ---------------+");
        System.out.println("+----------------------------------------------------+");

        // Solicitar les dades necessàries a l'usuari per modificar la base de dades.
        String nom = solicitarString("Quina escola vols modificar?");
        System.out.println("Camps disponibles per modificar:");
        System.out.println("• Nom");
        System.out.println("• Municipi");
        System.out.println("• Aproximació");
        System.out.println("• Popularitat");
        System.out.println("• Restriccions");
        String camp = solicitarString("Quin camp vols modificar?").toLowerCase();
        String nouValor = solicitarString("Què vols posar?");

        // Validar el campo ingresado por el usuario
        switch (camp) {
            case "nom":
            case "municipi":
            case "aproximacio":
            case "popularitat":
            case "restriccions":
                modificarEscola(nom, camp, nouValor);
                break;
            default:
                System.out.println("El camp introduït no és vàlid. Operació cancel·lada.");
        }
    }

    /**
     * Sol·licita l'ID d'una escola a esborrar i retorna l'ID donat com a nombre enter.
     * @return l'ID de l'escola que es vol eliminar, introduït com a string per l'usuari i convertit a un nombre sencer.
     */
    public static Integer esborrarEscola() {
        System.out.println("+---------------- ELIMINAR UNA ESCOLA ---------------+");
        System.out.println("+----------------------------------------------------+");
        return Integer.valueOf(solicitarString("Introdueix la ID de l'escola que vols eliminar: "));
    }

    /**
     * Mostra un missatge demanant a l'usuari que especifiqui quina escola vol veure i recupera l'ID de l'escola com a
     * nombre enter.
     *
     * @return l'ID de l'escola seleccionada proporcionada per l'usuari, convertida d'un string a un nombre enter.
     */
    public static Integer llistarEscola() {
        return Integer.valueOf(solicitarString("Quina escola vols veure?"));
    }


    ///#####################################################################################################################################################################################///
    ///--------------------------------------------------------------------------- SECTORS -------------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///
    /**
     * Crea un nou objecte Sector després d'obtenir les dades necessàries com el nom, coordenades, aproximació, popularitat
     * i restriccions de l'escola a través d'entrades de l'usuari.
     *
     * @return un objecte Sector amb les dades introduïdes.
     */
    public static Sector crearSector() {
        System.out.println("+------------------ CREAR UN SECTOR -----------------+");
        System.out.println("+----------------------------------------------------+");

        // Solicitar les dades necessàries a l'usuari per crear l'objecte Sector.
        String nom = solicitarString("Introdueix el nom del sector:");
        String coordenades = solicitarString("Introdueix les coordenades del sector:");
        String aproximacio = solicitarString("Descriu breument com s'arriba:");
        String popularitat = solicitarString("Introdueix el popularitat del sector (baixa, mitjana, alta):").toLowerCase();
        String restriccions = solicitarString("Introdueix les restriccions del sector:");
        Integer id_escola = Integer.valueOf(solicitarString("Introdueix l'ID de l'escola al que pertany el sector: "));

        return new Sector(nom, coordenades, aproximacio, popularitat, restriccions, id_escola);
    }

    /**
     * Modifica les dades d'un sector a la base de dades segons la informació proporcionada per l'usuari.
     *
     * Aquesta funció permet a l'usuari seleccionar un sector pel seu nom i modificar un camp específic de les seves dades.
     * La funció valida el camp introduït abans d'intentar realitzar la modificació. Si el camp no és vàlid o no es troba
     * el sector a la base de dades, es notifica a l'usuari.
     *
     * @throws SQLException Error en accedir o modificar la base de dades.
     */
    public static void editarSector() throws SQLException {
        System.out.println("+---------------- MODIFICAR UN SECTOR ---------------+");
        System.out.println("+----------------------------------------------------+");

        // Solicitar les dades necessàries a l'usuari per modificar la base de dades.
        String nom = solicitarString("Quin sector vols modificar?");
        System.out.println("Camps disponibles per modificar:");
        System.out.println("• Nom");
        System.out.println("• Coordenades");
        System.out.println("• Aproximació");
        System.out.println("• Popularitat");
        System.out.println("• Restriccions");
        String camp = solicitarString("Quin camp vols modificar?").toLowerCase();
        String nouValor = solicitarString("Què vols posar?");

        // Validar que el camp introduït per l'usuari és un camp vàlid
        switch (camp) {
            case "nom":
            case "coordenades":
            case "aproximacio":
            case "popularitat":
            case "restriccions":
                modificarSector(nom, camp, nouValor);
                break;
            default:
                System.out.println("El camp introduït no és vàlid. Operació cancel·lada.");
        }
    }

    /**
     * Sol·licita l'ID d'un sector a esborrar i retorna l'ID donat com a nombre enter
     *
     * @return l'ID del centre que es vol eliminar, introduït com a string per l'usuari i convertit a un nombre sencer.
     */
    public static Integer esborrarSector() {
        System.out.println("+---------------- ELIMINAR UN SECTOR ----------------+");
        System.out.println("+----------------------------------------------------+");
        return Integer.valueOf(solicitarString("Introdueix la ID del sector que vols eliminar: "));
    }

    /**
     * Mostra un missatge demanant a l'usuari que especifiqui quin sector vol veure i recupera l'ID del sector com a
     * nombre enter.
     *
     * @return l'ID del sector seleccionat proporcionat per l'usuari, convertit d'un string a un nombre enter.
     */
    public static Integer llistarSector() {
        return Integer.valueOf(solicitarString("Quin sector vols veure (Escriu la ID)?"));
    }

    ///#####################################################################################################################################################################################///
    ///----------------------------------------------------------------------------- VIES --------------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///
    /**
     * Crea un nou objecte Via després d'obtenir les dades necessàries com el nom, llargada, dificultat, orientació,
     * tipus, estat, ancoratge, roca, creador_id i sector_id de la via a través d'entrades de l'usuari.
     *
     * @return un objecte Via amb les dades introduïdes.
     */
    public static Via crearVia() {
        System.out.println("+----------------------------- CREAR UNA VIA ------------------------------+");
        System.out.println("+--------------------------------------------------------------------------+");

        // Solicitar les dades necessàries a l'usuari per crear l'objecte.
        String nom = solicitarString("Introdueix el nom de la via:");
        Integer llargada = Integer.valueOf(solicitarString("Introdueix la llargada de la via (en metres):"));
        String dificultat = solicitarString("Introdueix la dificultat de la via (4,4+,5,5+,6a,6a+,6b,6b+,6c,6c+,7a,7a+,7b,7b+,7c,7c+,8a,8a+,8b,8b+,8c,8c+,9a,9a+,9b,9b+,9c,9c+):").toLowerCase();
        String orientacio = solicitarString("Digues la orientació (N,NE,NO,SE,SO,E,O,S):");
        String tipus = solicitarString("Introdueix el tipus de la via (clàssica, gel, esportives):");
        String estat = solicitarString("Introdueix l'estat de la via (apte, construccio, tancada):");
        String ancoratge = solicitarString("Introdueix l'ancoratge de la via:");
        String roca = solicitarString("Introdueix el tipus de roca:");
        Integer creador_id = Integer.valueOf(solicitarString("Introdueix l'ID de l'usuari que ha creat la via: "));
        Integer sector_id = Integer.valueOf(solicitarString("Introdueix l'ID del sector al que pertany la via: "));

        return new Via(nom, llargada, dificultat, orientacio, tipus, estat, ancoratge, roca, creador_id, sector_id);
    }

    /**
     * Modifica les dades d'una via a la base de dades segons la informació proporcionada per l'usuari.
     *
     * Aquesta funció permet a l'usuari seleccionar una via pel seu nom i modificar un camp específic de les seves dades.
     * La funció valida el camp introduït abans d'intentar realitzar la modificació. Si el camp no és vàlid o no es troba
     * la via a la base de dades, es notifica a l'usuari.
     *
     * @throws SQLException Error en accedir o modificar la base de dades.
     */
    public static void editarVia() throws SQLException {
        System.out.println("+----------------- MODIFICAR UNA VIA ----------------+");
        System.out.println("+----------------------------------------------------+");

        // Solicitar les dades necessàries a l'usuari per modificar la base de dades.
        String nom = solicitarString("Quina via vols modificar?");
        System.out.println("Camps disponibles per modificar:");
        System.out.println("• Nom.");
        System.out.println("• Llargada.");
        System.out.println("• Dificultat.");
        System.out.println("• Orientació.");
        System.out.println("• Tipus.");
        System.out.println("• Estat.");
        System.out.println("• Ancoratge.");
        System.out.println("• Roques.");
        String camp = solicitarString("Quin camp vols modificar?").toLowerCase();
        String nouValor = solicitarString("Què vols posar?");

        // Validar que el camp introduït per l'usuari és un camp vàlid
        switch (camp) {
            case "nom":
            case "dificultat":
            case "orientació":
            case "tipus":
            case "estat":
            case "ancoratge":
            case "roques":
            case "llargada":
                modificarVia(nom, camp, nouValor);
                break;
            default:
                System.out.println("El camp introduït no és vàlid. Operació cancel·lada.");
        }
    }

    /**
     * Mostra un missatge interactiu per a eliminar una via, sol·licitant l'ID de la via que es desitja eliminar.
     *
     * @return l'ID de la via a eliminar, introduït com a string per l'usuari i convertit a un nombre enter.
     */
    public static Integer esborrarVia() {
        System.out.println("+--------------------- ELIMINAR UNA VIA ---------------------+");
        System.out.println("+------------------------------------------------------------+");
        return Integer.valueOf(solicitarString("Introdueix la ID de la via que vols eliminar:"));
    }

    /**
     * Mostra un missatge interactiu per a llistar una via, sol·licitant l'introducció de la ID de la via que es vol
     * veure.
     * @return l'ID de la via seleccionada proporcionat per l'usuari, convertit d'un string a un nombre enter.
     */
    public static Integer llistarVia() {
        return Integer.valueOf(solicitarString("Quina via vols veure (Escriu la ID)?"));
    }

    ///#####################################################################################################################################################################################///
    ///-------------------------------------------------------------------------- ESCALADORS -----------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///

    /**
     * Crea un nou objecte Escalador després d'obtenir les dades necessàries com el nom, l'àlies, l'edat, el nivell màxim,
     * l'estil preferit, fita i l'ID de la via a través d'entrades de l'usuari.
     *
     * @return un objecte Escalador amb les dades introduïdes.
     */
    public static Escaladors crearEscalador() {
        System.out.println("+---------------- CREAR UN ESCALADOR ----------------+");
        System.out.println("+----------------------------------------------------+");

        // Solicitar les dades necessàries a l'usuari per crear l'objecte.
        String nom = solicitarString("Introdueix el nom de l'escalador:");
        String alias = solicitarString("Introdueix l'àlies de l'escalador:");
        Integer edat = Integer.valueOf(solicitarString("Introdueix l'edat de l'escalador (en anys):"));
        String nivell_maxim = solicitarString("Introdueix el nivell màxim de l'escalador (4,4+,5,5+,6a,6a+,6b,6b+,6c,6c+,7a,7a+,7b,7b+,7c,7c+,8a,8a+,8b,8b+,8c,8c+,9a,9a+,9b,9b+,9c,9c+):").toLowerCase();
        String estil_preferit = solicitarString("Digues l'estil preferit (clàssica, gel, esportives):").toLowerCase();
        String fita = solicitarString("Escriu la fita: ");
        Integer viamaxima_id = Integer.valueOf(solicitarString("Digues l'ID de la via que més lluny a arribat: "));

        return new Escaladors(nom, alias, edat, nivell_maxim, estil_preferit, fita, viamaxima_id);
    }

    /**
     * Modifica les dades d'un escalador a la base de dades segons la informació proporcionada per l'usuari.
     *
     * Aquesta funció permet a l'usuari seleccionar un escalador pel seu nom i modificar un camp específic de les seves dades.
     * La funció valida el camp introduït abans d'intentar realitzar la modificació. Si el camp no és vàlid o no es troba
     * l'escalador a la base de dades, es notifica a l'usuari.
     *
     * @throws SQLException Error en accedir o modificar la base de dades.
     */
    public static void editarEscalador() throws SQLException {
        System.out.println("+-------------- MODIFICAR UN ESCALADOR --------------+");
        System.out.println("+----------------------------------------------------+");

        // Solicitar les dades necessàries a l'usuari per modificar la base de dades.
        String nom = solicitarString("Quin escalador vols modificar?");
        System.out.println("Camps disponibles per modificar:");
        System.out.println("• Nom");
        System.out.println("• Alias");
        System.out.println("• Edat");
        System.out.println("• Nivell_maxim");
        System.out.println("• Estil_preferit");
        System.out.println("• Fita");
        String camp = solicitarString("Quin camp vols modificar?").toLowerCase();
        String nouValor = solicitarString("Què vols posar?");

        // Validar el campo ingresado por el usuario
        switch (camp) {
            case "nom":
            case "alias":
            case "edat":
            case "nivell_maxim":
            case "estil_preferit":
            case "fita":
                modificarEscola(nom, camp, nouValor);
                break;
            default:
                System.out.println("El camp introduït no és vàlid. Operació cancel·lada.");
        }
    }

    /**
     * Demana a l'usuari que introdueixi l'ID d'un escalador a eliminar.
     *
     * @return Sol·licitar la ID de l'escalador que es vol esborrar.
     */
    public static Integer esborrarEscalador() {
        System.out.println("+--------------- ELIMINAR UN ESCALADOR --------------+");
        System.out.println("+----------------------------------------------------+");
        return Integer.valueOf(solicitarString("Introdueix la ID de l'escalador que vols eliminar: "));
    }

    /**
     * Demana a l'usuari que introdueixi l'identificador de l'escalador que es vol veure.
     *
     * @return Sol·licitar l'ID de l'escalador que vols veure.
     */
    public static Integer llistarEscalador() {
        return Integer.valueOf(solicitarString("Quin escalador vols veure?"));
    }

    ///#####################################################################################################################################################################################///
    ///------------------------------------------------------------------------- ESTADÍSTIQUES ---------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///
    /**
     * Solicita el nom d'una escola a l'usuari per veure les vies disponibles associades a aquesta escola.
     *
     * @return El nom de l'escola introduït per l'usuari.
     */
    public static String viesDisponibles () {
        return solicitarString("De quina escola vols veure les vies disponibles? (Escriu el nom)");
    }

    /**
     * Solicita a l'usuari que introdueixi una dificultat mínima i una dificultat màxima i retorna aquests dos
     * valors com un array d'strings.
     *
     * @return Un array de dos strings que corresponen a la dificultat mínima i la dificultat màxima introduïdes per
     * l'usuari.
     */
    public static String[] viesPerDificultat() {
        String minDif = solicitarString("Introdueix la mínima dificultat: ");
        String maxDif = solicitarString("Introdueix la màxima dificultat: ");
        return new String[]{minDif, maxDif};
    }

    /**
     * Solicita a l'usuari que introdueixi un estat per buscar les vies amb aquest estat.
     *
     * @return un string que és l'estat que ha introduït l'usuari passat a minúscules.
     */
    public static String viesPerEstat() {
        return solicitarString("Buscar vies pel seu estat (tancada, construcció, apte):").toLowerCase();
    }

    /**
     * Solicita a l'usuari un número per filtrar sectors amb més rutes disponibles que el número introduït.
     *
     * @return el número enter que ha introduit l'usuari.
     */
    public static Integer sectorsViesDisponibles() {
        return Integer.valueOf(solicitarString("Mostrar els sectors amb més d'X vies disponibles, escriu un número:"));
    }

    /**
     * Solicita a l'usuari que introdueixi el nom d'una escola per mostrar les vies més llargues d'aquesta.
     *
     * @return l'string que és el nom de l'escola que vol cercar.
     */
    public static String viesLlarguesEscola() {
        return solicitarString("De quina escola vols veure les vies més llargues? (Escriu el nom");
    }

    ///#####################################################################################################################################################################################///
    ///--------------------------------------------------------------------------- INPUTS --------------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///
    /**
     * És una funció que permet introduir un missatge que vulguis a l'hora de demanar un input a l'usuari.
     *
     * @param missatge El missatge mostrat a l'usuari per sol·licitar l'entrada.
     * @return L'string introduïda per l'usuari.
     */
    public static String solicitarString(String missatge) {
        System.out.println(missatge);
        return scanner.nextLine();
    }
}
