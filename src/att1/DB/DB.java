package att1.DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    // funcao para pegar/criar conexao com o banco de dados
    public static Connection getConnection() {
        if(conn == null) {
            try {
                // chama a funcao para pegar as propriedades do banco de dados
                Properties prop = loadProperties();
                String url = prop.getProperty("dburl");
                // usando o DriverManager, cria uma conexao com a url e as demais propriedades
                conn = DriverManager.getConnection(url, prop);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // se ja existir uma conexao ele apenas retorna uma Connection
        return conn;
    }

    public static void closeConnection() {
        if(conn != null) {
            try {
                // fecha a conexao
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // funcao pra pegar as propriedades da conexao
    private static Properties loadProperties() {
        try(FileInputStream fs = new FileInputStream("db.properties")) {
            // pegar as propriedades escritas no arquivo db.properties
            Properties prop = new Properties();
            // carrega as propriedades na Properties prop
            prop.load(fs);
            // retorna a Propertie
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
