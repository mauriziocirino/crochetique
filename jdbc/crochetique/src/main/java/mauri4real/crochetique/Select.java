package mauri4real.crochetique;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Select {
    Connection conn = sqlConnection.connectdb();
    PreparedStatement pst;
    public void select(){
        System.out.println("CRUD - Select - Catalogo");
        String sqlQuery = "SELECT * FROM Catalogo";
        try{
            pst = conn.prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                System.out.println("id: "+rs.getInt(1));
                System.out.println("nome: "+rs.getString(2));
                System.out.println("stagione: "+rs.getString(3));
                System.out.println("anno: "+rs.getInt(4));
            }
            new CRUDselection().select();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
