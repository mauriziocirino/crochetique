package mauri4real.crochetique;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
    Connection conn = sqlConnection.connectdb();
    PreparedStatement pst;
    Scanner scanner = Main.InputUtil.SCANNER; // Creiamo lo scanner per l'input

    public void delete(){
        System.out.println("CRUD - Delete - Catalogo");
        System.out.println("id => ");
        int id = scanner.nextInt();
        String sqlQuery = "DELETE FROM Catalogo WHERE id = ?";
        try{
            pst = conn.prepareStatement(sqlQuery);
            pst.setInt(1, id);
            if (pst.executeUpdate() == 1){
                System.out.println("Eliminato catalogo");
                System.out.println("Eliminare un altro Catalogo? (Press Y - yes or N - no)");
                if (scanner.nextLine().equalsIgnoreCase("Y")){
                    delete();
                }
                else new CRUDselection().select();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
