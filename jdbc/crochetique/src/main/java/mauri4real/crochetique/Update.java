package mauri4real.crochetique;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Update {
    Connection conn = sqlConnection.connectdb();
    PreparedStatement pst;
    Scanner scanner = Main.InputUtil.SCANNER; // Creiamo lo scanner per l'input

    public void update(){
        System.out.println("CRUD - Update - Catalogo");
        System.out.println("id da modificare => ");
        int id = scanner.nextInt();
        String sqlQuery = "UPDATE Catalogo set nome=?, anno=?, stagione=? WHERE id = ?";
        try{
            pst = conn.prepareStatement(sqlQuery);
            System.out.println("Inserire nuovo nome");
            pst.setString(1, scanner.next());
            System.out.println("Inserire anno");
            pst.setString(2, scanner.next());
            System.out.println("Inserire stagione");
            pst.setString(3, scanner.next());
            pst.setInt(4, id);
            if (pst.executeUpdate() == 1){
                System.out.println("Update catalogo effettutato");
                System.out.println("Modificare un altro Catalogo? (Press Y - yes or N - no)");
                if (scanner.nextLine().equalsIgnoreCase("Y")){
                    update();
                }
                else new CRUDselection().select();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
