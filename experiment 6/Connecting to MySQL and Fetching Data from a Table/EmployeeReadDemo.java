import java.sql.*;

public class EmployeeReadDemo {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/companydb";
        String user = "root";
        String password = "your_password"; // 🔑 replace with your MySQL password

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1️⃣ Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ JDBC Driver Loaded Successfully!");

            // 2️⃣ Establish Connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to Database!");

            // 3️⃣ Create SQL Query
            String query = "SELECT * FROM Employee";

            // 4️⃣ Create Statement
            stmt = conn.createStatement();

            // 5️⃣ Execute Query
            rs = stmt.executeQuery(query);

            // 6️⃣ Display Results
            System.out.println("\nEmpID | Name              | Salary");
            System.out.println("-------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");

                System.out.printf("%-6d | %-17s | %.2f%n", id, name, salary);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found. Please add MySQL connector JAR.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Database error occurred!");
            e.printStackTrace();
        } finally {
            // 7️⃣ Close all resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                System.out.println("\n✅ Resources closed successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
