package Model.DAO;

import Model.ConnexioBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstadistiquesVariesDAO {

    public static Connection EstadistiquesVariesDAO() throws SQLException {
        return ConnexioBD.getConnexio();
    }

    ///----------------- Mostrar les vies d'una Escola que es troben disponibles (estat = 'apte') -----------------///
    /**
     * Mostra les vies disponibles d'una escola específica que estan marcades com a "apte".
     * La informació de les vies, incloent el nom de la via i el nom de l'escola, es recuperarà de la base de dades
     * i es mostrarà a la consola.
     *
     * @param nomEscola el nom de l'escola de la qual es volen consultar les vies disponibles.
     * @throws SQLException si es produeix un error en la connexió o interacció amb la base de dades.
     */
    public static void mostrarViesDisponibles(String nomEscola) throws SQLException {
        Connection con = Model.ConnexioBD.getConnexio();
        String query = "SELECT " +
                "v.nom AS via, " +
                "e.nom AS escola " +
                "FROM vies v " +
                "JOIN sectors s ON v.sector_id = s.id_sector " +
                "JOIN escoles e ON s.escola_id = e.id_escola " +
                "WHERE v.estat = 'apte' AND e.nom = ?;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nomEscola);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                System.out.printf("Via: %s (Escola: %s)\n", resultat.getString("via"), resultat.getString("escola"));
            }
        } catch (SQLException e) {
            System.out.println("Error en mostrar les vies disponibles: " + e.getMessage());
        }
    }

    ///----------------------------- Cerca una via per dificultat a un rang específic -----------------------------///

    /**
     * Cerca i mostra les vies d'escalada que es troben dins d'un rang específic de dificultat.
     * Els resultats inclouen el nom de la via i el grau de dificultat, i es mostren ordenats per dificultat.
     *
     * @param dificultatMin la dificultat mínima que es vol considerar en la cerca.
     * @param dificultatMax la dificultat màxima que es vol considerar en la cerca.
     * @throws SQLException si es produeix un error en la connexió o interacció amb la base de dades.
     */
    public static void cercarViesPerDificultat(String dificultatMin, String dificultatMax) throws SQLException {
        Connection con = ConnexioBD.getConnexio();
        String query = "SELECT nom, grau_dificultat " +
                            "FROM vies " +
                            "WHERE grau_dificultat >= ? AND grau_dificultat <= ? " +
                            "ORDER BY grau_dificultat;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, dificultatMin);
            ps.setString(2, dificultatMax);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                System.out.printf("Via: %s (Dificultat: %s)\n", resultat.getString("nom"), resultat.getString("grau_dificultat"));
            }
        } catch (SQLException e) {
            System.out.println("Error en cercar vies per dificultat: " + e.getMessage());
        }
    }

    ///--------------------------------------- Cercar vies segons l'Estat ----------------------------------------///

    /**
     * Cerca i mostra les vies d'escalada que tenen un estat específic a la base de dades.
     * Els resultats inclouen el nom de la via i el seu estat, els quals es mostren per consola.
     *
     * @param estat l'estat de les vies a cercar. Aquest valor s'utilitza com a criteri per filtrar els resultats.
     * @throws SQLException si es produeix un error en establir la connexió amb la base de dades o en executar la consulta.
     */
    public static void cercarViesPerEstat(String estat) throws SQLException {
        Connection con = ConnexioBD.getConnexio();
        String query = "SELECT nom, estat FROM vies WHERE estat = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, estat);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                System.out.printf("Via: %s (Estat: %s)\n", resultat.getString("nom"), resultat.getString("estat"));
            }
        } catch (SQLException e) {
            System.out.println("Error en cercar vies per estat: " + e.getMessage());
        }
    }

    ///------------------------------- Consultar escoles amb restriccions actives --------------------------------///
    /**
     * Mostra una llista de les escoles que tenen restriccions actives. Les dades inclouen l'identificador de l'escola,
     * el nom, el municipi i les restriccions associades, que es recuperen de la base de dades.
     *
     * @throws SQLException si es produeix un error en la connexió o interacció amb la base de dades.
     */
    public static void escolesAmbRestriccions() throws SQLException {
        Connection con = ConnexioBD.getConnexio();
        String query = "SELECT id_escola, nom, municipi, restriccions FROM escoles WHERE restriccions IS NOT NULL AND restriccions <> ''";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                System.out.printf("Escola: %s (%s) - Restriccions: %s\n",
                        resultat.getString("nom"),
                        resultat.getString("municipi"),
                        resultat.getString("restriccions"));
            }
        } catch (SQLException e) {
            System.out.println("Error en consultar escoles amb restriccions actives: " + e.getMessage());
        }
    }

    ///------------------------------- Mostrar sectors amb més de X vies disponibles ------------------------------///
    /**
     * Mostra els sectors que tenen un nombre de vies disponibles superior al valor especificat.
     * Les dades s'obtenen des de la base de dades i inclouen el nom del sector i el nombre de vies aptes disponibles.
     * Els resultats es mostren per consola.
     *
     * @param x el nombre mínim de vies disponibles per sector per a ser inclòs en els resultats.
     * @throws SQLException si es produeix un error en la connexió, preparació, execució de la consulta o manipulació
     *                      dels resultats de la base de dades.
     */
    public static void sectorsViesDisponibles(Integer x) throws SQLException {
        Connection con = ConnexioBD.getConnexio();
        String query = "SELECT s.nom AS sector, COUNT(*) AS num_vies_aptes " +
                            "FROM vies v " +
                            "JOIN sectors s ON v.sector_id = s.id_sector " +
                            "WHERE v.estat = 'apte' " +
                            "GROUP BY s.nom " +
                            "HAVING COUNT(*) > ?;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, x);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                System.out.printf("Sector: %s (Vies disponibles: %d)\n", resultat.getString("sector"), resultat.getInt("num_vies_aptes"));
            }
        } catch (SQLException e) {
            System.out.println("Error en consultar sectors amb" + x + "vies disponibles: " + e.getMessage());
        }
    }

    ///-------------------------- Mostrar escaladors amb el mateix nivell màxim assolit ---------------------------///
    /**
     * Mostra una llista de nivells màxims d'escaladors i els noms dels escaladors agrupats per nivell. Només s'inclouen
     * aquells nivells en què existeixen més d'un escalador.
     * Els resultats es mostren per la consola amb el nivell màxim i els noms dels escaladors associats.
     *
     * @throws SQLException si es produeix un error en la connexió o interacció amb la base de dades.
     */
    public static void escaladorsAmbMateixNivell() throws SQLException {
        Connection con = ConnexioBD.getConnexio();
        String query = "SELECT nivell_maxim, STRING_AGG(nom, ', ') AS escaladors " +
                            "FROM escaladors " +
                            "GROUP BY nivell_maxim " +
                            "HAVING COUNT(*) > 1;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                System.out.printf("Nivell: %s - Escaladors: %s\n", resultat.getString("nivell_maxim"), resultat.getString("escaladors"));
            }
        } catch (SQLException e) {
            System.out.println("Error en consultar escaladors amb el mateix nivell: " + e.getMessage());
        }
    }

    ///--------------------------- Mostrar les vies que han passat a "apte" recentment ----------------------------///
    /**
     * Consulta les vies d'escalada de la base de dades que han estat recentment marcades com a "apte".
     * Les dades inclouen el nom de la via i la data del canvi d'estat, i es mostren per consola ordenades de manera
     * descendent segons la data de canvi d'estat.
     *
     * @throws SQLException si es produeix un error en la connexió o interacció amb la base de dades.
     */
    public static void viesRecentmentApte() throws SQLException {
        Connection con = ConnexioBD.getConnexio();
        String query = "SELECT nom, data_canvi_estat " +
                        "FROM vies " +
                        "WHERE estat = 'apte' " +
                        "ORDER BY data_canvi_estat DESC;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                System.out.printf("Via: %s (Data canvi: %s)\n", resultat.getString("nom"), resultat.getTimestamp("data_canvi_estat"));
            }
        } catch (SQLException e) {
            System.out.println("Error en consultar vies recentment aptes: " + e.getMessage());
        }
    }

    ///-------------------------- Mostrar les vies més llargues d'una escola determinada --------------------------///
    /**
     * Mostra les 5 vies més llargues d'una escola específica. La informació de les vies, incloent el nom de la via i
     * la seva llargada, es recupera de la base de dades i es mostra per consola.
     *
     * @param nomEscola el nom de l'escola de la qual es volen consultar les 5 vies més llargues.
     * @throws SQLException si es produeix un error en la connexió o interacció amb la base de dades.
     */
    public static void viesMesLlarguesPerEscola(String nomEscola) throws SQLException {
        Connection con = ConnexioBD.getConnexio();
        String query = "SELECT v.nom, v.llargada " +
                            "FROM vies v " +
                            "JOIN sectors s ON v.sector_id = s.id_sector " +
                            "JOIN escoles e ON s.escola_id = e.id_escola " +
                            "WHERE e.nom = ? " +
                            "ORDER BY v.llargada DESC;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nomEscola);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                System.out.printf("Via: %s (Llargada: %d m)\n", resultat.getString("nom"), resultat.getInt("llargada"));
            }
        } catch (SQLException e) {
            System.out.println("Error en consultar vies mes llargues per escola: " + e.getMessage());
        }
    }
}
