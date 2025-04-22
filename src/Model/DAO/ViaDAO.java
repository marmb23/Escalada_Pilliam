package Model.DAO;

import Model.ConnexioBD;
import Model.Objects.Via;

import java.sql.*;

public class ViaDAO {
    /**
     * Proveeix una connexió a la base de dades per a la classe ViaDAO. Aquest mètode estableix i torna un objecte
     * de connexió usant la classe ConnexioBD.
     *
     * @return un objecte Connection que representa la connexió a la base de dades.
     * @throws SQLException si passa un error d'accés a la base de dades.
     */
    public static Connection ViaDAO() throws SQLException {
        return ConnexioBD.getConnexio();
    }

    /**
     * Insereix un nou registre Via a la base de dades.
     *
     * @param via l'objecte Via que conté les dades a inserir a la base de dades. Inclou camps com nom, longitud,
     * dificultat, orientació, tipus, estat, tipus d'àncora, tipus de roca, ID de creador, ID d'escola i ID de sector.
     * @throws SQLException si es produeix un error d'accés a la base de dades durant el procés d'inserció.
     */
    public static void afegirVia(Via via) throws SQLException {
        String sql = "INSERT INTO vies (nom, llargada, grau_dificultat, orientacio, tipus, estat, ancoratges, tipus_roca, creador_id, sector_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        Connection con = Model.ConnexioBD.getConnexio();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, via.getNom());
            ps.setInt(2, via.getLlargada());
            ps.setString(3, via.getDificultat());
            ps.setString(4, via.getOrientacio());
            ps.setString(5, via.getTipus());
            ps.setString(6, via.getEstat());
            ps.setString(7, via.getAncoratge());
            ps.setString(8, via.getRoca());
            ps.setInt(9, via.getCreador_id());
            ps.setInt(10, via.getSector_id());

            Integer filesTornades = ps.executeUpdate();
            if (filesTornades >= 0) {
                System.out.println("Vía insertada correctament.");
            }

        } catch (SQLException e) {
            System.out.println("Error al afegir la via: " + e.getMessage());
        }
    }

    /**
     * Modifica un camp específic d'una via en la base de dades, identificat pel seu nom. Aquest mètode permet actualitzar
     * el valor d'un camp amb un nou valor proporcionat.
     *
     * @param nom el nom de la via que es vol modificar.
     * @param camp el nom del camp que es vol modificar (ha d'existir a la base de dades).
     * @param nouValor el nou valor que es vol establir per al camp especificat.
     * @throws SQLException si es produeix un error d'accés a la base de dades durant l'operació.
     */
    public static void modificarVia(String nom, String camp, String nouValor) throws SQLException {
        String query = "UPDATE vies SET " + camp + " = ? WHERE nom = ?";
        Connection con = Model.ConnexioBD.getConnexio();

        try (PreparedStatement ps = con.prepareStatement(query)) {

            switch (camp) {
                case "llargada":
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

            int files = ps.executeUpdate();
            if (files > 0) {
                System.out.println("El camp " + camp + " s'ha modificat correctament.");
            } else {
                System.out.println("No s'ha trobat cap via amb el nom" + nom);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Elimina un registre de la taula 'vies' a la base de dades basada en l'ID de la via proporcionada.
     * Si el registre específic existeix, serà eliminat. Si no existeix, es mostrarà un missatge indicant el contrari.
     *
     * @param id_via l'identificador de la via associada que es vol eliminar
     * @throws SQLException si es produeix un error d'accés a la base de dades durant l'operació
     */
    public static void eliminarVia(Integer id_escola) throws SQLException {
        Connection con = Model.ConnexioBD.getConnexio();
        String query = "DELETE FROM vies WHERE id_via = ?;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id_escola);
            Integer filasEliminadas = ps.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("La via ha estat eliminada.");
            } else {
                System.out.println("No s'ha trobat la via amb l'ID especificat.");
            }

        } catch (SQLException e) {
            System.out.println("Error en eliminar la via: " + e.getMessage());
        }
    }

    /**
     * Llista una única via de la base de dades basant-se en l'ID proporcionat.
     * Executa una consulta per recuperar informació detallada sobre una via incloent-hi les seves propietats
     *
     * @param id_via l'ID de la via que es vol veure.
     * @throws SQLException si es produeix un error durant l'accés a la base de dades o l'execució de la consulta
     */
    public static void llistarUnaVia(Integer id_escola) throws SQLException {
        Connection con = Model.ConnexioBD.getConnexio();

        String query = "SELECT " +
                        "v.id_via, " +
                        "v.nom," +
                        "v.llargada, " +
                        "v.grau_dificultat, " +
                        "v.orientacio, " +
                        "v.tipus, " +
                        "v.estat, " +
                        "v.data_canvi_estat, " +
                        "v.ancoratges, " +
                        "v.tipus_roca, " +
                        "v.temps_no_apte, " +
                        "s.nom AS nom_sector, " +
                        "e.nom AS nom_escola, " +
                        "a.nom AS nom_creador " +
                        "FROM vies v " +
                        "JOIN sectors s ON v.sector_id = s.id_sector " +
                        "JOIN escoles e ON s.escola_id = e.id_escola " +
                        "LEFT JOIN escaladors a ON v.creador_id = a.id_escalador;" +
                        "WHERE v.id_via = ?;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id_escola);
            Integer filesTornades = ps.executeUpdate();
            if (filesTornades <= 0) {
                System.out.println("No s'ha trobat la via amb l'ID especificat.");
            }

        } catch (SQLException e) {
            System.out.println("Error en llistar la via: " + e.getMessage());
        }
    }

    /**
     * Llista totes les vies emmagatzemades a la base de dades juntament amb els detalls associats.
     * Aquest mètode recupera registres de la base de dades que proporcionen tota la informació detallada sobre cada via.
     *
     * Les dades es mostren en una sortida formatada, amb les propietats de cada ruta mostrades de forma organitzada.
     *
     * @throws SQLException si es produeix un error d'accés a la base de dades o falla l'execució de la consulta.
     */
    public static void llistarTotesVies() throws SQLException {
        Connection con = Model.ConnexioBD.getConnexio();

        String query = "SELECT " +
                            "v.id_via, " +
                            "v.nom AS nom_via, " +
                            "v.llargada, " +
                            "v.grau_dificultat, " +
                            "v.orientacio, " +
                            "v.tipus, " +
                            "v.estat, " +
                            "v.data_canvi_estat, " +
                            "v.ancoratges, " +
                            "v.tipus_roca, " +
                            "v.temps_no_apte, " +
                            "s.nom AS nom_sector, " +
                            "e.nom AS nom_escola, " +
                            "a.nom AS nom_escalador " +
                            "FROM vies v " +
                            "JOIN sectors s ON v.sector_id = s.id_sector " +
                            "JOIN escoles e ON s.escola_id = e.id_escola " +
                            "LEFT JOIN escaladors a ON v.creador_id = a.id_escalador;";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet resultat = ps.executeQuery()) {

            while (resultat.next()) {
                Integer id_via = resultat.getInt("id_via");
                String nom = resultat.getString("nom_via");
                String llargada = resultat.getString("llargada");
                String dificultat = resultat.getString("grau_dificultat");
                String orientacio = resultat.getString("orientacio");
                String tipus = resultat.getString("tipus");
                String estat = resultat.getString("estat");
                Date data_canvi_estat = resultat.getDate("data_canvi_estat");
                String ancoratge = resultat.getString("ancoratges");
                String roca = resultat.getString("tipus_roca");
                String nom_sector = resultat.getString("nom_sector");
                String nom_escola = resultat.getString("nom_escola");
                String nom_escalador = resultat.getString("nom_escalador");
                Integer temps_noApte = resultat.getInt("temps_no_apte");

                String format = "| %-17s | %-30s |\n";
                System.out.println("+-------------------+--------------------------------+");
                System.out.printf(format, "ID Via", id_via);
                System.out.printf(format, "Nom", nom);
                System.out.printf(format, "Llargada", llargada);
                System.out.printf(format, "Dificultat", dificultat);
                System.out.printf(format, "Orientacio", orientacio);
                System.out.printf(format, "Tipus", tipus);
                System.out.printf(format, "Estat", estat);
                System.out.printf(format, "Data Canvi Estat", data_canvi_estat);
                System.out.printf(format, "Ancoratges", ancoratge);
                System.out.printf(format, "Tipus Roca", roca);
                System.out.printf(format, "Sector", nom_sector);
                System.out.printf(format, "Escola", nom_escola);
                System.out.printf(format, "Escalador/a", nom_escalador);
                System.out.printf(format, "Temps no apte", temps_noApte);
                System.out.println("+-------------------+--------------------------------+");
            }
            Integer filesTornades = ps.executeUpdate();
            if (filesTornades >= 0) {
                System.out.println("Totes les vies mostrades correctament.");
            }

        } catch (SQLException e) {
            System.out.println("Error en llistar les vies: " + e.getMessage());
        }
    }
}