package Vista;
import java.util.Scanner;

public class Vista {

    /**
     * Mostra el menú principal de l'aplicació, proporcionant a l'usuari opcions per
     * navegar per diverses seccions o funcionalitats.
     * Cada opció correspon a un submenú o funcionalitat específica dins de laplicació.
     * Aquest mètode només mostra la interfície del menú i no gestiona les entrades de l'usuari.
     */
    public static void menuPrincipal() {
        System.out.println("+---------- BENVINGUT/DA A ESCALADA PILLIAM ---------+");
        System.out.println("+----------------------------------------------------+");
        System.out.println("+----------------------- INICI ----------------------+");
        System.out.println("+----------------------------------------------------+");
        System.out.println("Escull que vols modificar/mostrar:");
        System.out.println("1. Escoles.");
        System.out.println("2. Sectors.");
        System.out.println("3. Vies.");
        System.out.println("4. Escaladors.");
        System.out.println("5. Estadístiques diverses.");
    }

    /**
     * Mostra el menú principal associat amb la gestió d'escoles.
     * Aquest mètode s'encarrega exclusivament de presentar les diferents opcions disponibles
     * per a l'usuari en relació amb les operacions sobre escoles.
     * El mètode només genera la interfície visual del menú i no gestiona l'entrada de l'usuari.
     */
    public static void menuEscoles() {
        System.out.println("+---------------------- ESCOLES ---------------------+");
        System.out.println("+----------------------------------------------------+");
        System.out.println("Què vols fer?");
        System.out.println("1. Crear una escola.");
        System.out.println("2. Modificar una escola.");
        System.out.println("3. Llistar una escola.");
        System.out.println("4. Llistar totes les escoles.");
        System.out.println("5. Eliminar una escola.");
    }

    /**
     * Mostra el menú d'operacions relacionades amb el sector. Aquest mètode presenta l'usuari
     * amb una llista d'opcions rellevants per a la gestió de sectors, com crear, modificar
     * crear, modificar, llistar o esborrar un sector.
     * Aquest mètode serveix únicament per presentar la interfície del menú. No processa
     * No processa les entrades de l'usuari ni executa les operacions directament.
     * i la lògica corresponent es gestiona en un altre lloc.
     */
    public static void menuSectors() {
        System.out.println("+---------------------- SECTORS ---------------------+");
        System.out.println("+----------------------------------------------------+");
        System.out.println("Què vols fer?");
        System.out.println("1. Crear un sector.");
        System.out.println("2. Modificar un sector.");
        System.out.println("3. Llistar un sector.");
        System.out.println("4. Llistar tots els sectors.");
        System.out.println("5. Eliminar un sector.");
    }

    /**
     * Mostra el menú «VIES» a l'usuari. Aquest mètode proporciona una interfície textual
     * que presenta diverses opcions relacionades amb les operacions sobre les entitats «via». Cada opció del menú
     * opció del menú correspon a una funcionalitat específica, com crear, modificar
     * llistar o suprimir una «via».
     *
     * Aquest mètode només mostra les opcions del menú i no maneja l'entrada de l'usuari
     * ni executa les operacions seleccionades.
     */
    public static void menuVies() {
        System.out.println("+------------------------ VIES ----------------------+");
        System.out.println("+----------------------------------------------------+");
        System.out.println("Què vols fer?");
        System.out.println("1. Crear una via.");
        System.out.println("2. Modificar una via.");
        System.out.println("3. Llistar una via.");
        System.out.println("4. Llistar totes les vies.");
        System.out.println("5. Eliminar una via.");
    }

    /**
     * Mostra el menú principal associat amb la gestió d'escaladors. Aquest mètode
     * proporciona una interfície textual per interactuar amb operacions relacionades amb escaladors.
     * Presenta diverses opcions a l'usuari, com ara crear, modificar, llistar o eliminar escaladors.
     *
     * Aquest mètode només genera i mostra les opcions del menú, sense gestionar
     * l'entrada de l'usuari ni executar les operacions associades.
     */
    public static void menuEscaladors() {
        System.out.println("+--------------------- ESCALADORS -------------------+");
        System.out.println("+----------------------------------------------------+");
        System.out.println("Què vols fer?");
        System.out.println("1. Crear un escalador.");
        System.out.println("2. Modificar un escalador.");
        System.out.println("3. Llistar un escalador.");
        System.out.println("4. Llistar tots els escaladors.");
        System.out.println("5. Eliminar un escalador.");
    }

    /**
     * Mostra el menú d'estadístiques a l'usuari. Aquest mètode proporciona una interfície textual
     * que presenta diverses opcions relacionades amb la consulta d'estadístiques dins de l'aplicació.
     * Cada opció del menú correspon a una funcionalitat específica, com ara cercar vies per dificultat,
     * consultar escoles amb restriccions actives, o mostrar informació relacionada amb escaladors o vies.
     *
     * Aquest mètode únicament genera i presenta la interfície visual del menú d'estadístiques.
     * No processa l'entrada de l'usuari ni executa les operacions seleccionades.
     */
    public static void menuEstadistiques() {
        System.out.println("+-------------- ESTADÍSTIQUES DIVERSES --------------+");
        System.out.println("+----------------------------------------------------+");
        System.out.println("Què vols fer?");
        System.out.println("1. Mostra les vies d'una Escola que es troben disponibles.");
        System.out.println("2. Cerca una via per dificultat a un rang específic.");
        System.out.println("3. Cercar vies segons estat (apte, construcció o tancada).");
        System.out.println("4. Consultar escoles amb restriccions actives actualment.");
        System.out.println("5. Mostrar sectors amb més de X vies disponibles.");
        System.out.println("6. Mostrar escaladors amb el mateix nivell màxim assolit.");
        System.out.println("7. Mostrar les vies que han passat a 'apte' recentment.");
        System.out.println("8. Mostrar les vies més llargues d'una escola determinada.");
    }



    




}