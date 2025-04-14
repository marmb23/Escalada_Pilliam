package Vista;
import java.util.Scanner;

public class Vista {
    /// MENUS ///
    public static void menuPrincipal() {
        System.out.println("----BENVINGUT/DA A ESCALADA PILLIAM----");
        System.out.println("---------------------------------------");
        System.out.println("---------------- INICI ----------------");
        System.out.println("---------------------------------------");
        System.out.println("Escull que vols modificar/mostrar:");
        System.out.println("1. Escoles.");
        System.out.println("2. Sectors.");
        System.out.println("3. Vies.");
        System.out.println("4. Escaladors.");
        System.out.println("5. Estadístiques diverses.");
        System.out.println("Entra una opció:");
    }

    public static void menuEscoles() {
        System.out.println("----------------------------------");
        System.out.println("------------- ESCOLES ------------");
        System.out.println("----------------------------------");
        System.out.println("Què vols fer?");
        System.out.println("1. Crear una escola.");
        System.out.println("2. Modificar una escola.");
        System.out.println("3. Llistar una escola.");
        System.out.println("4. Llistar totes les escoles.");
        System.out.println("5. Eliminar una escola.");
        System.out.println("Entra una opció:");
    }

    public static void menuSectors() {
        System.out.println("-------------------------------");
        System.out.println("----------- SECTORS -----------");
        System.out.println("-------------------------------");
        System.out.println("Què vols fer?");
        System.out.println("1. Crear un sector.");
        System.out.println("2. Modificar un sector.");
        System.out.println("3. Llistar un sector.");
        System.out.println("4. Llistar tots els sectors.");
        System.out.println("5. Eliminar un sector.");
        System.out.println("Entra una opció:");
    }

    public static void menuVies() {
        System.out.println("-------------------------------");
        System.out.println("------------- VIES ------------");
        System.out.println("-------------------------------");
        System.out.println("Què vols fer?");
        System.out.println("1. Crear una via.");
        System.out.println("2. Modificar una via.");
        System.out.println("3. Llistar una via.");
        System.out.println("4. Llistar totes les vies.");
        System.out.println("5. Eliminar una via.");
        System.out.println("Entra una opció:");
    }

    public static void menuEscaladors() {
        System.out.println("---------------------------------------");
        System.out.println("-------------- ESCALADORS -------------");
        System.out.println("---------------------------------------");
        System.out.println("Què vols fer?");
        System.out.println("1. Crear un escalador.");
        System.out.println("2. Modificar un escalador.");
        System.out.println("3. Llistar un escalador.");
        System.out.println("4. Llistar tots els escaladors.");
        System.out.println("5. Eliminar un escalador.");
        System.out.println("Entra una opció:");
    }

    public static void menuEstadistiques() {
        System.out.println("----------------------------------------------------------");
        System.out.println("----------------- ESTADÍSTIQUES DIVERSES -----------------");
        System.out.println("----------------------------------------------------------");
        System.out.println("Què vols fer?");
        System.out.println("1. Mostra les vies d'una Escola que es troben disponibles.");
        System.out.println("2. Cerca una via per dificultat a un rang específic.");
        System.out.println("3. Cercar vies segons estat (apte, construcció o tancada).");
        System.out.println("4. Consultar escoles amb restriccions actives actualment.");
        System.out.println("5. Mostrar sectors amb més de X vies disponibles.");
        System.out.println("6. Mostrar escaladors amb el mateix nivell màxim assolit.");
        System.out.println("7. Mostrar les vies que han passat a 'apte' recentment.");
        System.out.println("8. Mostrar les vies més llargues d'una escola determinada.");
        System.out.println("Entra una opció:");
    }



    




}