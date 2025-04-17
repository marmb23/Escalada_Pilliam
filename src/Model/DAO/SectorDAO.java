package Model.DAO;

import Model.ConnexioBD;
import Model.Objects.Sector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SectorDAO {

    /**
     * Estableix i torna una connexió a la base de dades utilitzant la classe d'utilitat ConnexioBD.
     *
     * @return un objecte {@code Connection} per interactuar amb la base de dades.
     * @throws SQLException si es produeix un error d'accés a la base de dades.
     */
    public static Connection SectorDAO() throws SQLException {
        return ConnexioBD.getConnexio();
    }

    /**
     * Insereix un nou sector a la base de dades.
     *
     * @param sector L'objecte {@code Sector} que conté les dades a inserir,
     * incloent el seu nom, coordenades, enfocament, popularitat, restriccions,
     * i ID d'escola associat.
     * @throws SQLException si es produeix un error d'accés a la base de dades durant el procés d'inserció.
     */
    public static void afegirSector(Sector sector) throws SQLException {
        String sql = "INSERT INTO sectors (nom, coordenades, aproximacio, popularitat, restriccions, escola_id) VALUES (?,?,?,?,?,?)";
        Connection con = Model.ConnexioBD.getConnexio();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sector.getNom());
            ps.setString(2, sector.getCoordenades());
            ps.setString(3, sector.getAproximacio());
            ps.setString(4, sector.getPopularitat());
            ps.setString(5, sector.getRestriccions());
            ps.setInt(6, sector.getEscola_id());

            Integer filesTornades = ps.executeUpdate();
            if (filesTornades >= 0) {
                System.out.println("Sector insertat correctament.");
            }
        } catch (SQLException e) {
            System.out.println("Error al afegir el sector: " + e.getMessage());
        }
    }

    /**
     * Actualitza un camp específic d'un sector de la base de dades, identificat pel nom.
     *
     * @param nom El nom del sector que cal actualitzar.
     * @param camp El camp del sector que cal actualitzar.
     * @param nouValor El nou valor a assignar al camp especificat.
     * @throws SQLException Si es produeix un error d'accés a la base de dades.
     */
    public static void modificarSector(String nom, String camp, String nouValor) throws SQLException {
        String query = "UPDATE sectors SET " + camp + " = ? WHERE nom = ?";
        Connection con = Model.ConnexioBD.getConnexio();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nouValor);
            ps.setString(2, nom);

            Integer files = ps.executeUpdate();
            if (files > 0) {
                System.out.println("El camp " + camp + " s'ha modificat correctament.");
            } else {
                System.out.println("No s'ha trobat cap sector amb el nom" + nom);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Elimina un sector de la base de dades en funció del vostre ID.
     *
     * @param id_sector L'ID del sector a esborrar.
     * @throws SQLException Si es produeix un error d'accés a la base de dades durant el procés d'esborrament.
     */
    public static void eliminarSector(Integer id_sector) throws SQLException {
        Connection con = Model.ConnexioBD.getConnexio();
        String query = "DELETE FROM sectors WHERE id_sector = ?;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id_sector);
            Integer filesEliminadas = ps.executeUpdate();

            if (filesEliminadas > 0) {
                System.out.println("El sector ha estat eliminat.");
            } else {
                System.out.println("No s'ha trobat el sector amb l'ID especificat.");
            }

        } catch (SQLException e) {
            System.out.println("Error en eliminar el sector: " + e.getMessage());
        }
    }

    /**
     * Recupera i mostra detalls d'un sector específic de la base de dades basant-se en el vostre ID.
     * Si no es troba la informació del sector, notifica a l'usuari.
     *
     * @param id_sector L'ID del sector a recuperar.
     * @throws SQLException Si es produeix un error d'accés a la base de dades.
     */
    public static void llistarUnSector(Integer id_sector) throws SQLException {
        Connection con = Model.ConnexioBD.getConnexio();
        String query = "SELECT " +
                "s.id_sector, " +
                "s.nom, " +
                "s.coordenades, " +
                "s.aproximacio, " +
                "s.popularitat, " +
                "s.restriccions, " +
                "s.num_vies, " +
                "e.nom AS nom_escola " +
                "FROM sectors s " +
                "JOIN escoles e ON s.escola_id = e.id_escola" +
                "WHERE s.id_sector = ?;";

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
     * Recupera i mostra tots els sectors existents a la base de dades juntament amb els seus detalls.
     * La informació de cada sector inclou el seu ID, nom, coordenades, aproximació, popularitat, restriccions,
     * nombre de rutes i el nom de l'escola associada.
     *
     * Els resultats s'imprimeixen a la consola en una taula formatada.
     *
     * @throws SQLException si es produeix un error d'accés a la base de dades durant la recuperació dels sectors.
     */
    public static void llistarTotsSectors() throws SQLException {
        Connection con = Model.ConnexioBD.getConnexio();
        String query = "SELECT " +
                            "s.id_sector, " +
                            "s.nom, " +
                            "s.coordenades, " +
                            "s.aproximacio, " +
                            "s.popularitat, " +
                            "s.restriccions, " +
                            "s.num_vies, " +
                            "e.nom AS nom_escola " +
                        "FROM sectors s " +
                        "JOIN escoles e ON s.escola_id = e.id_escola;";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet resultat = ps.executeQuery()) {
            while (resultat.next()) {
                Integer id_sector = resultat.getInt("id_sector");
                String nom = resultat.getString("nom");
                String coordenades = resultat.getString("coordenades");
                String aproximacio = resultat.getString("aproximacio");
                String popularitat = resultat.getString("popularitat");
                String restriccions = resultat.getString("restriccions");
                Integer num_vies = resultat.getInt("num_vies");
                String nom_escola = resultat.getString("nom_escola");

                String format = "| %-17s | %-30s |\n";
                System.out.println("+-------------------+--------------------------------+");
                System.out.printf(format, "ID Sector", id_sector);
                System.out.printf(format, "Nom", nom);
                System.out.printf(format, "Coordenades", coordenades);
                System.out.printf(format, "Aproximació", aproximacio);
                System.out.printf(format, "Popularitat", popularitat);
                System.out.printf(format, "Restriccions", restriccions);
                System.out.printf(format, "Escola", nom_escola);
                System.out.printf(format, "Nombre de vies", num_vies);
                System.out.println("+-------------------+--------------------------------+");
            }

        } catch (SQLException e) {
            System.out.println("Error en llistar els sectors: " + e.getMessage());
        }
    }

}
