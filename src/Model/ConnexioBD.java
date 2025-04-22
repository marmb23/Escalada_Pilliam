package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexioBD {


    private static final String URL = "jdbc:postgresql://192.168.0.205:5432/escalada_pilliam";
    private static final String USER = "mmanzano";
    private static final String PASSWORD = "0323";

    /**
     * Estableix i torna una connexió a la base de dades utilitzant les credencials especificades.
     * URL, usuari i contrasenya especificats.
     *
     * @return un objecte {@code Connection} per interactuar amb la base de dades.
     * @throws SQLException si es produeix un error d'accés a la base de dades o la URL de la base de dades no és vàlida.
     */
    public static Connection getConnexio() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Tanca la connexió a la base de dades proporcionada si no és nul·la.
     *
     * @param connexio l'objecte {@code Connexió} a tancar. Si és nul, no es fa cap acció.
     * @throws SQLException si es produeix un error en tancar la connexió.
     */
    public static void closeConnexio(Connection connexio) throws SQLException{
        if(connexio != null){
            connexio.close();
        }
    }
}