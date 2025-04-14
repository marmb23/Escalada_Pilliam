package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexioBD {

    // Parámetros de conexión
    private static final String URL = "jdbc:mariadb://192.168.0.205:3306/escalada_pilliam"; // Cambia la URL de acuerdo con tu base de datos
    private static final String USER = "mmanzano";  // Cambia el usuario
    private static final String PASSWORD = "0323";  // Cambia la contraseña

    // Esta variable contiene la conexión
    private static Connection connexio = null;

    // Método para obtener la conexión a la base de datos
    public static Connection getConnexio() {
        if (connexio == null) {
            try {
                // Cargar el driver JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establecer la conexión
                connexio = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexió amb la base de dades establerta.");
            } catch (ClassNotFoundException e) {
                System.out.println("Error: No es pot carregar el driver JDBC.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Error: No es pot establir la connexió amb la base de dades.");
                e.printStackTrace();
            }
        }
        return connexio;
    }

    // Método para cerrar la conexión (opcional, por si quieres cerrar la conexión manualmente)
    public static void tancarConnexio() {
        if (connexio != null) {
            try {
                connexio.close();
                connexio = null;
                System.out.println("Connexió tancada.");
            } catch (SQLException e) {
                System.out.println("Error al tancar la connexió.");
                e.printStackTrace();
            }
        }
    }
}
