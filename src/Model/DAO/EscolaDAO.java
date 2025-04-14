package Model.DAO;

import Model.Objects.Escola;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EscolaDAO {
    private static Connection con;

    public EscolaDAO(Connection con) {
        this.con = con;
    }

    public void afegirEscola(Escola escola) {
        String sql = "INSERT INTO escola (nom, municipi, aproximacio, popularitat, restriccions) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, escola.getNom());
            ps.setString(2, escola.getMunicipi());
            ps.setString(3, escola.getAproximacio());
            ps.setString(4, escola.getPopularitat());
            ps.setString(5, escola.getRestriccions());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al afegir l'escola: " + e.getMessage());
        }
    }

    public static void modificarEscola(String nom, String camp, String nouValor) {
        String query = "UPDATE escoles SET " + camp + " = ? WHERE " + nom + " = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nouValor);
            ps.setString(2, nom);

            int files = ps.executeUpdate();
            if (files > 0) {
                System.out.println("El camp " + camp + " s'ha modificat correctament.");
            } else {
                System.out.println("No s'ha trobat cap escola amb el nom" + nom);
            }
        } catch (SQLException e) {
            System.out.println("Error en modificar el camp: " + e.getMessage());
        }
    }

    public void eliminarEscola(Integer id_escola) {
        String query = "DELETE FROM escola WHERE id_escola = ?;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id_escola);
            int filasEliminadas = ps.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("L'escola ha estat eliminada.");
            } else {
                System.out.println("No s'ha trobat l'escola amb l'ID especificat.");
            }
        } catch (SQLException e) {
            System.out.println("Error en eliminar l'escola: " + e.getMessage());
        }
    }

    public static void llistarUnaEscola(Integer id_escola) {
        String query = "SELECT * FROM escola WHERE escola.id_escola = ?;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id_escola);
            int filesTornades = ps.executeUpdate();
            if (filesTornades <= 0) {
                System.out.println("No s'ha trobat l'escola amb l'ID especificat.");
            }
        } catch (SQLException e) {
            System.out.println("Error en llistar l'escola: " + e.getMessage());
        }
    }

    public static void llistarTotesEscoles() {
        String query = "SELECT * FROM escola;";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet resultat = ps.executeQuery()) {
            while (resultat.next()) {
                int id_escola = resultat.getInt("id_escola");
                String nom = resultat.getString("nom");
                String municipi = resultat.getString("municipi");
                String aproximacio = resultat.getString("aproximacio");
                String popularitat = resultat.getString("popularitat");
                String restriccions = resultat.getString("restriccions");
                int num_vies = resultat.getInt("num_vies");

                System.out.println("ID Escola: " + id_escola + '\'' +
                                        "Nom: " + nom + '\'' +
                                        "Municipi: " + municipi + '\'' +
                                        "Aproximació: " + aproximacio + '\'' +
                                        "Popularitat: " + popularitat + '\'' +
                                        "Restriccions; " + restriccions + '\'' +
                                        "Nombre de vies: " + num_vies);
            }
        } catch (SQLException e) {
            System.out.println("Error en llistar les escoles: " + e.getMessage());
        }
    }
}
