package mauri4real.crochetique;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Create {
    Scanner scanner = Main.InputUtil.SCANNER; // Creiamo lo scanner per l'input

    public void create() throws SQLException {
        boolean continueCreating = true;
        while (continueCreating) {
            // Crea una nuova connessione per ogni operazione (o utilizza un pool)
            Connection conn = sqlConnection.connectdb();
            if (conn != null && conn.isValid(2)) {
                System.out.println("Connessione stabilita con successo!");
            } else {
                System.out.println("Connessione fallita o non valida!");
                System.exit(0);
            }
            PreparedStatement pst=null;
            System.out.println("CRUD - Create - Catalogo");

            // Lettura dell'input dell'utente
            System.out.print("Name => ");
            String name = scanner.nextLine();

            System.out.print("Stagione => ");
            String stagione = scanner.nextLine();

            System.out.print("Anno => ");
            int anno = -1;
            while (true) {
                try {
                    anno = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Errore: Per favore inserisci un numero valido per l'anno.");
                }
            }

            String sqlQuery = "INSERT INTO Catalogo (nome, stagione, anno) VALUES (?, ?, ?)";

            try {
                pst = conn.prepareStatement(sqlQuery);
                pst.setString(1, name);
                pst.setString(2, stagione);
                pst.setInt(3, anno);

                // Esecuzione dell'insert
                if (pst.executeUpdate() == 1) {
                    System.out.println("Catalogo inserito con successo!");
                } else {
                    System.out.println("Inserimento fallito!");
                }
            } catch (SQLException e) {
                System.out.println("Errore durante l'inserimento: " + e.getMessage());
            }

            // Chiedi se si vuole inserire un altro catalogo
            System.out.println("Inserire un altro Catalogo? (Press Y - yes or N - no)");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("Y")) {
                continueCreating = false;
            }
        }
        // Torna al menù principale
        new CRUDselection().select();
    }
}
