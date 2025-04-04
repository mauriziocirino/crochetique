package mauri4real.crochetique;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CRUDselection {
    public void select() {
        Scanner scan = Main.InputUtil.SCANNER; // Creiamo lo scanner per l'input
        int select = -1;

        // Loop continuo per il menù
        do {
            System.out.println("Selezionare un'operazione CRUD");
            System.out.println("1. Create");
            System.out.println("2. Select");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("0. Exit");

            try {
                select = Integer.parseInt(scan.nextLine()); // Prendi l'input dell'utente
                switch (select) {
                    case 5 -> System.out.println("Ciao\n");
                    case 1 -> new Create().create();  // Chiamata al metodo create
                    case 2 -> new Select().select();// Chiamata al metodo select
                    case 3 -> new Update().update();  // Chiamata al metodo update
                    case 4 -> new Delete().delete();  // Chiamata al metodo delete
                    case 0 ->{
                        System.out.println("Uscita dal programma...");
                        System.exit(0); // Uscita dal programma
                        }
                    default -> System.out.println("Opzione non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Errore: Per favore inserisci un numero valido.");
                scan.nextLine();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } while (select!=0);
    }
}
