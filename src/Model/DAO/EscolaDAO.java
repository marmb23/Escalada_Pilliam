package Model.DAO;

import Model.ConnexioBD;
import Model.Objects.Escola;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EscolaDAO {

    /**
     * Estableix i torna una connexió de base de dades per al sistema Escola.
     * Utilitza la utilitat ConnexioBD per iniciar la connexió.
     *
     * @return Un objecte Connection que representa la connexió a la base de dades establerta.
     * @throws SQLException Si es produeix un error d'accés a la base de dades en intentar connectar-se.
     */
    public static Connection EscolaDAO() throws SQLException {
        return ConnexioBD.getConnexio();
    }

    /**
     * Afegeix una entrada d'escola a la base de dades amb els detalls proporcionats.
     *
     * @param escola L'objecte Escola que conté els detalls de l'escola que s'hi afegirà.
     * @throws SQLException Si es produeix un error d'accés a la base de dades.
     */
    public static void afegirEscola(Escola escola) throws SQLException {
        String sql = "INSERT INTO escoles (nom, municipi, aproximacio, popularitat, restriccions) VALUES (?,?,?,?,?)";
        Connection con = Model.ConnexioBD.getConnexio();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, escola.getNom());
            ps.setString(2, escola.getMunicipi());
            ps.setString(3, escola.getAproximacio());
            ps.setString(4, escola.getPopularitat());
            ps.setString(5, escola.getRestriccions());

            Integer filesTornades = ps.executeUpdate();
            if (filesTornades >= 0) {
                System.out.println("Vía insertada correctament.");
            }

        } catch (SQLException e) {
            System.out.println("Error al afegir l'escola: " + e.getMessage());
        }
    }

    /**
     * Modifica un camp específic d'una escola a la base de dades en funció del seu nom.
     * Actualitza la informació proporcionada amb el nou valor especificat.
     *
     * @param nom El nom de l'escola que es vol modificar.
     * @param camp El nom del camp de l'escola que es vol actualitzar (exemple: "adreça", "telèfon").
     * @param nouValor El nou valor que es vol establir per al camp especificat.
     * @throws SQLException Si es produeix un error d'accés o execució de la consulta contra la base de dades.
     */
    public static void modificarEscola(String nom, String camp, String nouValor) throws SQLException {
        String query = "UPDATE escoles SET " + camp + " = ? WHERE nom = ?";
        Connection con = Model.ConnexioBD.getConnexio();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nouValor);
            ps.setString(2, nom);

            Integer files = ps.executeUpdate();
            if (files > 0) {
                System.out.println("El camp " + camp + " s'ha modificat correctament.");
            } else {
                System.out.println("No s'ha trobat cap escola amb el nom" + nom);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Elimina una escola de la base de dades segons l'ID proporcionat.
     * Aquest mètode executa una consulta per eliminar el registre de la base de dades
     * que correspon a l'ID especificat.
     *
     * @param id_escola L'ID de l'escola que es vol eliminar.
     * @throws SQLException Si es produeix un error d'accés o execució de la consulta
     *                      contra la base de dades.
     */
    public static void eliminarEscola(Integer id_escola) throws SQLException {
        Connection con = Model.ConnexioBD.getConnexio();
        String query = "DELETE FROM escoles WHERE id_escola = ?;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id_escola);
            Integer filesEliminadas = ps.executeUpdate();

            if (filesEliminadas > 0) {
                System.out.println("L'escola ha estat eliminada.");
            } else {
                System.out.println("No s'ha trobat l'escola amb l'ID especificat.");
            }

        } catch (SQLException e) {
            System.out.println("Error en eliminar l'escola: " + e.getMessage());
        }
    }

    /**
     * Llista els detalls d'una escola específica de la base de dades basant-se en l'ID d'escola proporcionada.
     * Executa una consulta per recuperar informació de l'escola corresponent a l'ID donat.
     *
     * @param id_escola L'ID del col·legi a llistar.
     * @throws SQLException Si hi ha un error accedint o executant la consulta contra la base de dades.
     */
    public static void llistarUnaEscola(Integer id_escola) throws SQLException {
        Connection con = Model.ConnexioBD.getConnexio();
        String query = "SELECT * FROM escoles WHERE escoles.id_escola = ?;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id_escola);

            Integer filesTornades = ps.executeUpdate();
            if (filesTornades <= 0) {
                System.out.println("No s'ha trobat l'escola amb l'ID especificat.");
            }

        } catch (SQLException e) {
            System.out.println("Error en llistar l'escola: " + e.getMessage());
        }
    }

    /**
     * Llista totes les escoles de la base de dades juntament amb els seus detalls.
     * Executa una consulta per obtenir tots els registres de la taula "escoles" i mostra la seva informació.
     * Els detalls de cada escola inclouen l'ID, nom, municipi, accessibilitat, popularitat,
     * restriccions i nombre de vies.
     *
     * Aquest mètode recupera i mostra les dades en una estructura formatada a la sortida estàndard.
     *
     * @throws SQLException Si es produeix un error en accedir a la base de dades.
     */
    public static void llistarTotesEscoles() throws SQLException {
        Connection con = Model.ConnexioBD.getConnexio();
        String query = "SELECT * FROM escoles;";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet resultat = ps.executeQuery()) {
            while (resultat.next()) {
                int id_escola = resultat.getInt("id_escola");
                String nom = resultat.getString("nom");
                String municipi = resultat.getString("municipi");
                String aproximacio = resultat.getString("aproximacio");
                String popularitat = resultat.getString("popularitat");
                String restriccions = resultat.getString("restriccions");
                Integer num_vies = resultat.getInt("num_vies");

                String format = "| %-17s | %-30s |\n";
                System.out.println("+-------------------+--------------------------------+");
                System.out.printf(format, "ID Escola", id_escola);
                System.out.printf(format, "Nom", nom);
                System.out.printf(format, "Municipi", municipi);
                System.out.printf(format, "Aproximació", aproximacio);
                System.out.printf(format, "Popularitat", popularitat);
                System.out.printf(format, "Restriccions", restriccions);
                System.out.printf(format, "Nombre de vies", num_vies);
                System.out.println("+-------------------+--------------------------------+");
            }

        } catch (SQLException e) {
            System.out.println("Error en llistar les escoles: " + e.getMessage());
        }
    }

}
