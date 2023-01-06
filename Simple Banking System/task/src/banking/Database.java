package banking;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private String dbName;
    public Database(String dbName){
        this.dbName = dbName;
    };
    public String getDBName(){
        return dbName;
    };
    public static void createNewDatabase(String dbName) {

        String url = "jdbc:sqlite:C:/sqlite/db/simpleBankSystem/" + dbName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create a new table in the test database
     *
     */
    public static void createNewTable(String dbName) {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/simpleBankSystem/" + dbName;

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS card (\n"
                + "	id integer,\n"
                + "	number text,\n"
                + "	pin TEXT\,n"
                + " balance INTEGER DEFAULT 0);";


        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}

    public static void connect(String dbName) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/sqlite/db/simpleBankSystem/" + dbName;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Retrying..");
            createNewDatabase(dbName);

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
