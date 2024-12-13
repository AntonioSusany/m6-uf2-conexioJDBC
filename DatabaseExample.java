import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseExample {

    private static final Scanner scanner = new Scanner(System.in); 

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/LigaFutbol";
        String user = "root";
        String password = "jupiter";

        while (true) {
            System.out.println("\n--MENU--");
            System.out.println("1. Creació de la base de dades");
            System.out.println("2. Inserir registre a la taula");
            System.out.println("3. Mostrar registres de la taula");
            System.out.println("4. Creació XML dels registres de la taula");
            System.out.println("5. Mostrar registre a partir d'un id");
            System.out.println("6. Mostrar registre a partir d'un nom");
            System.out.println("7. Modificar registre de la taula");
            System.out.println("8. Esborrar registre a partir d'un id");
            System.out.println("9. Sortir");
            System.out.print("Seleccioneu una opció: ");

            int opcio = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcio) {
                case 1:
                    System.out.println("Funcionalitat no implementada encara.");
                    break;
                case 2:
                    System.out.println("Funcionalitat no implementada encara.");
                    break;
                case 3:
                    veureEquips(url, user, password);
                    break;
                case 5:
                    veureEquipAmbID(url, user, password); 
                    break;
                case 6:
                    veureEquipAmbNom(url, user, password);
                    break;
                case 9:
                    System.out.println("Has sortit del programa");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    public static void veureEquips(String url, String user, String password) {
        String query = "SELECT * FROM Equipo";

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query)
        ) {
            System.out.println("\n--- Lista de la taula Equipo ---");
            while (rs.next()) {
                System.out.println("IdEquip: " + rs.getInt("IdEquipo"));
                System.out.println("NomEquip: " + rs.getString("NombreEquipo"));
                System.out.println("Ciutat: " + rs.getString("Ciudad"));
                System.out.println("Fundació: " + rs.getInt("Fundacion"));
                System.out.println("----------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar o executar la consulta: " + e.getMessage());
        }
    }

    public static void veureEquipAmbID(String url, String user, String password) {
        System.out.print("Per quina ID vols buscar?: ");
        int id = scanner.nextInt(); 

        String query = "SELECT * FROM Equipo WHERE IdEquipo = " + id;

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query)
        ) {
            System.out.println("\n--- Equip amb Id: " + id + " ---");
            if (rs.next()) {
                System.out.println("IdEquip: " + rs.getInt("IdEquipo"));
                System.out.println("NomEquip: " + rs.getString("NombreEquipo"));
                System.out.println("Ciutat: " + rs.getString("Ciudad"));
                System.out.println("Fundació: " + rs.getInt("Fundacion"));
                System.out.println("----------------------");
            } else {
                System.out.println("No s'ha trobat cap equip amb aquesta ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar o executar la consulta: " + e.getMessage());
        }
    }

    public static void veureEquipAmbNom(String url, String user, String password){

        System.out.print("Per quin nom vols buscar?: ");
        String nom = scanner.nextLine(); 

        String query = "SELECT * FROM Equipo WHERE NombreEquipo LIKE " + nom;

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query)
        ) {
            System.out.println("\n--- Equip amb Nom: " + nom + " ---");
            if (rs.next()) {
                System.out.println("IdEquip: " + rs.getInt("IdEquipo"));
                System.out.println("NomEquip: " + rs.getString("NombreEquipo"));
                System.out.println("Ciutat: " + rs.getString("Ciudad"));
                System.out.println("Fundació: " + rs.getInt("Fundacion"));
                System.out.println("----------------------");
            } else {
                System.out.println("No s'ha trobat cap equip amb aquesta ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar o executar la consulta: " + e.getMessage());
        }
    }



    }
