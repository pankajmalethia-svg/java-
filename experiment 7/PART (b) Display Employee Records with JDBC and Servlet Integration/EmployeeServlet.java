package employee;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String empid = request.getParameter("empid");

        out.println("<html><body>");
        out.println("<h2>Employee Details</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/webappdemo", "root", "yourpassword");

            Statement stmt = con.createStatement();
            ResultSet rs;

            if (empid != null && !empid.trim().isEmpty()) {
                rs = stmt.executeQuery("SELECT * FROM Employee WHERE EmpID=" + empid);
            } else {
                rs = stmt.executeQuery("SELECT * FROM Employee");
            }

            out.println("<table border='1' align='center'>");
            out.println("<tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");

            boolean recordFound = false;
            while (rs.next()) {
                recordFound = true;
                out.println("<tr>");
                out.println("<td>" + rs.getInt("EmpID") + "</td>");
                out.println("<td>" + rs.getString("Name") + "</td>");
                out.println("<td>" + rs.getFloat("Salary") + "</td>");
                out.println("</tr>");
            }

            if (!recordFound) {
                out.println("<tr><td colspan='3'>No record found!</td></tr>");
            }

            out.println("</table>");
            con.close();

        } catch (Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }
}
