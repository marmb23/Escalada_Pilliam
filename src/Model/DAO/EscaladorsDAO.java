package Model.DAO;

import Model.ConnexioBD;
import Model.Objects.Escaladors;
import Model.Objects.Sector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EscaladorsDAO {

    public static Connection EscaladorsDAO() throws SQLException {
        return ConnexioBD.getConnexio();
    }

    public static void afegirEscalador(Escaladors escalador) throws SQLException {
        String sql = "INSERT INTO escaladors (nom, alies, edat, nivell_maxim, estil_preferit, fita) VALUES (?,?,?,?,?,?)";
        Connection con = Model.ConnexioBD.getConnexio();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, escalador.getNom());
            ps.setString(2, escalador.getAlies());
            ps.setInt(3, escalador.getEdat());
            ps.setString(4, escalador.getNivell());
            ps.setString(5, escalador.getEstil_preferit());
            ps.setString(6, escalador.getFita());

            Integer filesTornades = ps.executeUpdate();
            if (filesTornades >= 0) {
                System.out.println("Escalador insertat correctament.");
            }
        } catch (SQLException e) {
            System.out.println("Error al afegir l'escalador: " + e.getMessage());
        }
    }

    /**
     * Actualitza un camp específic d'un escalador de la base de dades, identificat pel nom.
     *
     * @param nom El nom del escalador que cal actualitzar.
     * @param camp El camp del escalador que cal actualitzar.
     * @param nouValor El nou valor a assignar al camp especificat.
     * @throws SQLException Si es produeix un error d'accés a la base de dades.
     */
    public static void modificarEscalador(String nom, String camp, String nouValor) throws SQLException {
        String query = "UPDATE escaladors SET " + camp + " = ? WHERE nom = ?";
        Connection con = Model.ConnexioBD.getConnexio();

        try (PreparedStatement ps = con.prepareStatement(query)) {

            switch (camp) {
                case "edat":
                    try {
                        ps.setInt(1, Integer.parseInt(nouValor));
                    } catch (NumberFormatException e) {
                        System.out.println("El camp " + camp + " ha de ser un nombre enter.");
                        return;
                    }
                    break;
                default:
                    ps.setString(1, nouValor);
            }
            ps.setString(2, nom);

            Integer files = ps.executeUpdate();
            if (files > 0) {
                System.out.println("El camp " + camp + " s'ha modificat correctament.");
            } else {
                System.out.println("No s'ha trobat cap escalador amb el nom" + nom);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Elimina un escalador de la base de dades en funció de l'ID.
     *
     * @param id_sector L'ID de l'escalador a esborrar.
     * @throws SQLException Si es produeix un error d'accés a la base de dades durant el procés d'esborrament.
     */
    public static void eliminarEscalador(Integer id_sector) throws SQLException {
        Connection con = Model.ConnexioBD.getConnexio();
        String query = "DELETE FROM escaladors WHERE id_escalador = ?;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id_sector);
            Integer filesEliminadas = ps.executeUpdate();

            if (filesEliminadas > 0) {
                System.out.println("L'escalador ha estat eliminat.");
            } else {
                System.out.println("No s'ha trobat l'escalador amb l'ID especificat.");
            }

        } catch (SQLException e) {
            System.out.println("Error en eliminar el sector: " + e.getMessage());
        }
    }

    /**
     * Recupera i mostra detalls d'un escalador específic de la base de dades basant-se en el vostre ID.
     * Si no es troba la informació de l'escalador, notifica a l'usuari.
     *
     * @param id_sector L'ID de l'escalador a recuperar.
     * @throws SQLException Si es produeix un error d'accés a la base de dades.
     */
    public static void llistarUnEscalador(Integer id_sector) throws SQLException {
        Connection con = Model.ConnexioBD.getConnexio();
        String query = "SELECT " +
                            "e.id_escalador, " +
                            "e.nom, " +
                            "e.alias, " +
                            "e.edat, " +
                            "e.nivell_maxim, " +
                            "e.estil_preferit, " +
                            "e.fita, " +
                            "v.nom AS nom_via " +
                            "FROM escaladors e " +
                            "JOIN vies v ON e.viamaxim_id = v.id_via" +
                            "WHERE e.id_escalador = ?;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id_sector);
            Integer filesTornades = ps.executeUpdate();

            if (filesTornades <= 0) {
                System.out.println("No s'ha trobat el sector amb l'ID especificat.");
            }

        } catch (SQLException e) {
            System.out.println("Error en llistar el sector: " + e.getMessage());
        }
    }

    /**
     * Recupera i mostra tots els escaladors existents a la base de dades juntament amb els seus detalls.
     * La informació de cada escalador inclou el seu ID, nom, alias, edat, nivell màxim, estil preferit,
     * fita i la via màxima.
     *
     * Els resultats s'imprimeixen a la consola en una taula formatada.
     *
     * @throws SQLException si es produeix un error d'accés a la base de dades durant la recuperació dels sectors.
     */
    public static void llistarTotsEscaladors() throws SQLException {
        Connection con = Model.ConnexioBD.getConnexio();
        String query = "SELECT " +
                            "e.id_escalador, " +
                            "e.nom, " +
                            "e.alias, " +
                            "e.edat, " +
                            "e.nivell_maxim, " +
                            "e.estil_preferit, " +
                            "e.fita, " +
                            "v.nom AS nom_via " +
                            "FROM escaladors e " +
                            "JOIN vies v ON e.viamaxim_id = v.id_via;";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet resultat = ps.executeQuery()) {
            while (resultat.next()) {
                Integer id_escalador = resultat.getInt("id_escalador");
                String nom = resultat.getString("nom");
                String alias = resultat.getString("alias");
                Integer edat = resultat.getInt("edat");
                String nivell_maxim = resultat.getString("nivell_maxim");
                String estil_preferit = resultat.getString("estil_preferit");
                String fita = resultat.getString("fita");
                String nom_via = resultat.getString("nom_via");

                String format = "| %-17s | %-30s |\n";
                System.out.println("+-------------------+--------------------------------+");
                System.out.printf(format, "ID Sector", id_escalador);
                System.out.printf(format, "Nom", nom);
                System.out.printf(format, "Alias", alias);
                System.out.printf(format, "Edat", edat);
                System.out.printf(format, "Nivell Màxim", nivell_maxim);
                System.out.printf(format, "Estil preferit", estil_preferit);
                System.out.printf(format, "Fita", fita);
                System.out.printf(format, "Nombre de la vía màxima", nom_via);
                System.out.println("+-------------------+--------------------------------+");
            }

        } catch (SQLException e) {
            System.out.println("Error en llistar els sectors: " + e.getMessage());
        }
    }
}
