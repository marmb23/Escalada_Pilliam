package Controlador;

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
     * Crea un nou objecte Escola després d'obtenir les dades necessàries com el nom, municipi, aproximació,
     * popularitat i restriccions de l'escola a través d'entrades de l'usuari.
     *
     * @return un objecte de tipus Escola amb les propietats inicialitzades segons les dades introduïdes.
     */
    public static Escola crearEscola() {
        System.out.println("+----------------- CREAR UNA ESCOLA -----------------+");
        System.out.println("+----------------------------------------------------+");

        // Solicitar les dades necessàries a l'usuari per crear l'objecte.
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
     * La funció valida el camp introduït abans d'intentar realitzar la modificació.
     * Si el camp no és vàlid o no es troba l'escola a la base de dades, es notifica a l'usuari.
     *
     * @throws SQLException si ocorre un error en accedir o modificar la base de dades.
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
     * Mostra un avís a l'usuari sol·licitant l'ID d'una escola a esborrar
     * i retorna l'ID donat com a nombre enter.
     * @return l'ID del centre que es vol eliminar, introduït com a cadena per l'usuari
     * i convertit a un nombre sencer.
     */
    public static Integer esborrarEscola() {
        System.out.println("+---------------- ELIMINAR UNA ESCOLA ---------------+");
        System.out.println("+----------------------------------------------------+");
        return Integer.valueOf(solicitarString("Introdueix la ID de l'escola que vols eliminar: "));
    }

    /**
     * Mostra un missatge demanant a l'usuari que especifiqui quina escola vol veure
     * i recupera l'ID de l'escola com a nombre enter.
     *
     * @return l'ID de l'escola seleccionada proporcionada per l'usuari, convertida d'una cadena a un nombre enter.
     */
    public static Integer llistarEscola() {
        return Integer.valueOf(solicitarString("Quina escola vols veure?"));
    }


    ///#####################################################################################################################################################################################///
    ///--------------------------------------------------------------------------- SECTORS -------------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///
    /**
     * Crea un nou objecte de tipus Sector després d'obtenir les dades necessàries mitjançant l'entrada de l'usuari.
     * Les dades inclouen el nom del sector, coordenades, aproximació, popularitat, restriccions i l'ID de l'escola al qual pertany.
     *
     * @return un objecte de tipus Sector amb les propietats inicialitzades segons les dades proporcionades per l'usuari.
     */
    public static Sector crearSector() {
        System.out.println("+------------------ CREAR UN SECTOR -----------------+");
        System.out.println("+----------------------------------------------------+");

        // Solicitar les dades necessàries a l'usuari per crear l'objecte.
        String nom = solicitarString("Introdueix el nom del sector:");
        String coordenades = solicitarString("Introdueix les coordenades del sector:");
        String aproximacio = solicitarString("Descriu breument com s'arriba:");
        String popularitat = solicitarString("Introdueix el popularitat del sector (baixa, mitjana, alta):").toLowerCase();
        String restriccions = solicitarString("Introdueix les restriccions del sector:");
        Integer id_escola = Integer.valueOf(solicitarString("Introdueix l'ID de l'escola al que pertany el sector: "));

        return new Sector(nom, coordenades, aproximacio, popularitat, restriccions, id_escola);
    }

    /**
     * Permet modificar un sector existent a la base de dades segons la informació proporcionada per l'usuari.
     *
     * Aquesta funció mostra un menú interactiu que permet a l'usuari especificar:
     * - El nom del sector a modificar.
     * - El camp específic que es desitja actualitzar.
     * - El nou valor que es vol assignar al camp seleccionat.
     *
     * Una vegada confirmats el nom, el camp, i el nou valor, es crida a la funció `modificarSector` per
     * realitzar els canvis a la base de dades. Si el camp introduït és invàlid, la funció notifica que
     * l'operació ha estat cancel·lada.
     *
     * @throws SQLException Si ocorre un error en accedir o modificar la base de dades.
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
     * Solicita a l'usuari la identificació d'un sector que es desitja eliminar
     * i retorna aquesta identificació convertida a un nombre enter.
     *
     * @return l'ID del sector a eliminar, introduït com a cadena per l'usuari
     * i convertit a un nombre enter.
     */
    public static Integer esborrarSector() {
        System.out.println("+---------------- ELIMINAR UN SECTOR ----------------+");
        System.out.println("+----------------------------------------------------+");
        return Integer.valueOf(solicitarString("Introdueix la ID del sector que vols eliminar: "));
    }

    /**
     * Solicita a l'usuari que introdueixi la identificació d'un sector que es vol consultar
     * i retorna aquesta identificació convertida a un nombre enter.
     *
     * @return l'ID del sector seleccionat proporcionat per l'usuari, convertit d'una cadena a un nombre enter.
     */
    public static Integer llistarSector() {
        return Integer.valueOf(solicitarString("Quin sector vols veure (Escriu la ID)?"));
    }

    ///#####################################################################################################################################################################################///
    ///----------------------------------------------------------------------------- VIES --------------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///
    /**
     * Crea un nou objecte de tipus Via després d'obtenir totes les dades necessàries mitjançant
     * interacció amb l'usuari. Aquestes dades inclouen informació com el nom, llargada, dificultat,
     * orientació, tipus, estat, ancoratge, tipus de roca, i associacions a creador, escola i sector.
     *
     * @return un objecte de tipus Via amb les propietats inicialitzades segons les dades introduïdes per l'usuari.
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
        Integer escola_id = Integer.valueOf(solicitarString("Introdueix l'ID de l'escola al que pertany la via: "));
        Integer sector_id = Integer.valueOf(solicitarString("Introdueix l'ID del sector al que pertany la via: "));

        return new Via(nom, llargada, dificultat, orientacio, tipus, estat, ancoratge, roca, creador_id, escola_id, sector_id);
    }

    /**
     * Permet modificar les dades d'una via existent en la base de dades segons la informació proporcionada
     * per l'usuari. Aquesta funció proporciona un menú interactiu on l'usuari pot:
     * - Seleccionar la via a modificar pel seu nom.
     * - Escollir el camp específic que desitja modificar (nom, llargada, dificultat, orientació, tipus,
     *   estat, ancoratge o roques).
     * - Especificar el nou valor del camp seleccionat.
     *
     * La funció valida que el camp seleccionat per l'usuari sigui vàlid i, en cas afirmatiu, crida
     * a la funció `modificarVia` per executar els canvis corresponents a la base de dades.
     * Si el camp introduït és invàlid, l'operació es cancel·la i s'informa l'usuari.
     *
     * @throws SQLException si es produeix un error en accedir o modificar la base de dades.
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
     * Mostra un missatge interactiu per a eliminar una via, sol·licitant l'introducció
     * de l'ID de la via que es desitja eliminar. Retorna l'ID introduït per l'usuari
     * com un nombre enter.
     *
     * @return l'ID de la via a eliminar, introduït com a cadena per l'usuari i convertit a un nombre enter.
     */
    public static Integer esborrarVia() {
        System.out.println("+--------------------- ELIMINAR UNA VIA ---------------------+");
        System.out.println("+------------------------------------------------------------+");
        return Integer.valueOf(solicitarString("Introdueix la ID de la via que vols eliminar:"));
    }

    /**
     * Solicita a l'usuari que introdueixi la identificació d'una via que vol consultar
     * i retorna aquesta identificació com un nombre enter.
     *
     * @return l'ID de la via seleccionada proporcionat per l'usuari, convertit d'una cadena a un nombre enter.
     */
    public static Integer llistarVia() {
        return Integer.valueOf(solicitarString("Quina via vols veure (Escriu la ID)?"));
    }

    ///#####################################################################################################################################################################################///
    ///-------------------------------------------------------------------------- ESCALADORS -----------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///



    ///#####################################################################################################################################################################################///
    ///--------------------------------------------------------------------------- INPUTS --------------------------------------------------------------------------------------------------///
    ///#####################################################################################################################################################################################///
    /**
     * Demana a l'usuari un missatge i recupera una cadena d'entrada de la consola.
     *
     * @param missatge El missatge mostrat a l'usuari per sol·licitar l'entrada.
     * @return La cadena introduïda per l'usuari.
     */
    public static String solicitarString(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }
}
