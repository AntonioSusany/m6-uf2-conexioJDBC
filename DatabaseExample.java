import java.sql.*;
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
                    inserirRegistre(url, user, password);
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
                case 7:
                    modificarRegistre(url, user, password);
                    break;
                case 8:
                    eliminarRegistre(url, user, password);
                    break;
                case 9:
                    System.out.println("----------------------");
                    System.out.println("Has sortit del programa");
                    System.out.println("----------------------");
                    scanner.close();
                    return;
                default:
                    System.out.println("----------------------");
                    System.out.println("Opción no válida. Intente de nuevo.");
                    System.out.println("----------------------");
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
            System.out.println("----------------------");
            System.out.println("Error al conectar o executar la consulta: " + e.getMessage());
            System.out.println("----------------------");
        }
    }

    public static void veureEquipAmbID(String url, String user, String password) {
        System.out.print("Per quina ID vols buscar?: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        String query = "SELECT * FROM Equipo WHERE IdEquipo = ?";

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)
        ) {
            pst.setInt(1, id); 

            try (ResultSet rs = pst.executeQuery()) {
                System.out.println("\n--- Equip amb Id: " + id + " ---");
                if (rs.next()) {
                    System.out.println("IdEquip: " + rs.getInt("IdEquipo"));
                    System.out.println("NomEquip: " + rs.getString("NombreEquipo"));
                    System.out.println("Ciutat: " + rs.getString("Ciudad"));
                    System.out.println("Fundació: " + rs.getInt("Fundacion"));
                    System.out.println("----------------------");
                } else {
                    System.out.println("----------------------");
                    System.out.println("No s'ha trobat cap equip amb aquesta ID.");
                    System.out.println("----------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("----------------------");
            System.out.println("Error al conectar o executar la consulta: " + e.getMessage());
            System.out.println("----------------------");
        }
    }

    public static void veureEquipAmbNom(String url, String user, String password) {
        System.out.print("Per quin nom vols buscar?: ");
        String nom = scanner.nextLine();

        String query = "SELECT * FROM Equipo WHERE NombreEquipo LIKE ?";

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)
        ) {
            pst.setString(1, "%" + nom + "%"); 

            try (ResultSet rs = pst.executeQuery()) {
                System.out.println("\n--- Equip amb Nom: " + nom + " ---");
                if (rs.next()) {
                    System.out.println("IdEquip: " + rs.getInt("IdEquipo"));
                    System.out.println("NomEquip: " + rs.getString("NombreEquipo"));
                    System.out.println("Ciutat: " + rs.getString("Ciudad"));
                    System.out.println("Fundació: " + rs.getInt("Fundacion"));
                    System.out.println("Estadio: " + rs.getInt("IdEstadio"));
                    System.out.println("----------------------");
                } else {
                    System.out.println("----------------------");
                    System.out.println("No s'ha trobat cap equip amb aquest nom.");
                    System.out.println("----------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("----------------------");
            System.out.println("Error al conectar o executar la consulta: " + e.getMessage());
            System.out.println("----------------------");
        }
    }

    public static void inserirRegistre(String url, String user, String password) {
        System.out.println("-- Inserir un nou Equip --");

        System.out.print("Nom de l'equip: ");
        String nom_equip = scanner.nextLine();

        System.out.print("Ciutat de l'equip: ");
        String ciutat_equip = scanner.nextLine();

        System.out.print("Any de fundació: ");
        int fundacio_equip = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID de l'estadi: ");
        int id_estadi = scanner.nextInt();
        scanner.nextLine(); 

        String query = "INSERT INTO Equipo (NombreEquipo, Ciudad, Fundacion, IdEstadio) VALUES (?, ?, ?, ?)";

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)
        ) {
            pst.setString(1, nom_equip);
            pst.setString(2, ciutat_equip);
            pst.setInt(3, fundacio_equip);
            pst.setInt(4, id_estadi);

            pst.executeUpdate();

            System.out.println("----------------------");
            System.out.println("Registre registrat satisfactoriament.");
            System.out.println("----------------------");

        } catch (SQLException e) {
            System.out.println("----------------------");
            System.out.println("Error al conectar o executar la consulta: " + e.getMessage());
            System.out.println("----------------------");
        }
    }

    public static void eliminarRegistre(String url, String user, String password) {
        System.out.println("-- Eliminar un Equip --");
        System.out.print("Quin equip vols eliminar? ID de l'equip: ");

        int id_equip = scanner.nextInt();
        scanner.nextLine(); 

        String query = "DELETE FROM Equipo WHERE IdEquipo = ?";

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)
        ) {
            pst.setInt(1, id_equip);
            pst.executeUpdate();
            System.out.println("----------------------");
            System.out.println("Equip eliminat satisfactoriament.");
            System.out.println("----------------------");
        } catch (SQLException e) {
            System.out.println("----------------------");
            System.out.println("Error al conectar o executar la consulta: " + e.getMessage());
            System.out.println("----------------------");
        }
    }

    public static void modificarRegistre(String url, String user, String password) {

        System.out.println("-- Modificar un equip --");
        System.out.println("Quin equip vols modificar? (ID): ");

        int id_equip = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Quin camp vols modificar?: ");
        System.out.println("1. IdEquipo");
        System.out.println("2. NombreEquipo");
        System.out.println("3. Ciudad");
        System.out.println("4. Fundacion");
        System.out.println("5. IdEstadio");

        int opcio = scanner.nextInt();
        scanner.nextLine();

        String campo = "";
        String tipus = "";

        switch (opcio) {
            case 1:
                campo = "IdEquipo";
                tipus = "int";
                break;
            case 2:
                campo = "NombreEquipo";
                tipus = "string";
                break;
            case 3:
                campo = "Ciudad";
                tipus = "string";
                break;
            case 4:
                campo = "Fundacion";
                tipus = "string";
                break;
            case 5:
                campo = "IdEstadio";
                tipus = "int";
                break;
            default:
                System.out.println("Opció no vàlida. Intenta de nou.");
        }

        System.out.println("Que vols introduir en el camp? ");

        Object valor = null;

        if (tipus.equals("int")) {
            valor = scanner.nextInt();
            scanner.nextLine();  
        } else if (tipus.equals("string")) {
            valor = scanner.nextLine();
        }

        String query = "UPDATE Equipo SET " + campo + " = ? WHERE IdEquipo = ?";
        

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)

        ){
            pst.setObject(1, valor);
            pst.setInt(2, id_equip);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("----------------------");
                System.out.println("Equip modificat amb èxit!");
                System.out.println("----------------------");
            } else {
                System.out.println("----------------------");
                System.out.println("No s'ha trobat l'equip amb l'ID especificat.");
                System.out.println("----------------------");
            }

        }catch (SQLException e) {
            System.out.println("----------------------");
            System.out.println("Error de connexió: " + e.getMessage());
            System.out.println("----------------------");
        }

        
    }
}
