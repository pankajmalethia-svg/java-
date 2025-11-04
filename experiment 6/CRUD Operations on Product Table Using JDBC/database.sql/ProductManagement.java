import java.sql.*;
import java.util.Scanner;

public class ProductManagement {
    // JDBC variables
    private static final String URL = "jdbc:mysql://localhost:3306/productdb";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";
    private static Connection conn;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to Database Successfully!");

            // Menu-driven interface
            while (true) {
                System.out.println("\n====== PRODUCT MANAGEMENT SYSTEM ======");
                System.out.println("1. Add Product");
                System.out.println("2. View All Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1: createProduct(); break;
                    case 2: readProducts(); break;
                    case 3: updateProduct(); break;
                    case 4: deleteProduct(); break;
                    case 5:
                        conn.close();
                        System.out.println("👋 Exiting... Connection Closed.");
                        return;
                    default:
                        System.out.println("⚠️ Invalid choice, try again!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CREATE
    private static void createProduct() {
        System.out.print("Enter Product Name: ");
        sc.nextLine(); // consume newline
        String name = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        String sql = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);
            ps.executeUpdate();
            System.out.println("✅ Product added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    private static void readProducts() {
        String sql = "SELECT * FROM Product";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nProductID | ProductName       | Price   | Quantity");
            System.out.println("--------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-10d %-20s %-8.2f %-10d%n",
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE (with transaction)
    private static void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter New Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter New Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter New Quantity: ");
        int qty = sc.nextInt();

        String sql = "UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?";
        try {
            conn.setAutoCommit(false); // begin transaction
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setDouble(2, price);
                ps.setInt(3, qty);
                ps.setInt(4, id);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    conn.commit(); // commit if successful
                    System.out.println("✅ Product updated successfully!");
                } else {
                    conn.rollback(); // rollback if no record found
                    System.out.println("⚠️ No product found with that ID. Transaction rolled back.");
                }
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("❌ Error occurred. Transaction rolled back.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    // DELETE (with transaction)
    private static void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM Product WHERE ProductID=?";
        try {
            conn.setAutoCommit(false); // begin transaction
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    conn.commit(); // success
                    System.out.println("✅ Product deleted successfully!");
                } else {
                    conn.rollback(); // failure
                    System.out.println("⚠️ No product found with that ID. Transaction rolled back.");
                }
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("❌ Error occurred. Transaction rolled back.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
