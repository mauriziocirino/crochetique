package mauri4real.crochetique;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlConnection {
    public static Connection connectdb() {
        String sqlUsername = "root";
        String sqlPassword = "root";
        String dbURL = "jdbc:mysql://127.0.0.1:3306/crochetique";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, sqlUsername, sqlPassword);
            return conn;
        } catch (SQLException|ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

}
