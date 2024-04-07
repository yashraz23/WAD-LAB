import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;

@WebServlet("/CustomLoginServlet")
public class CustomLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connect to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "admin"); // replace username and password with your MySQL credentials
            
            // Prepare SQL statement to check user credentials
            PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            pst.setString(1, username);
            pst.setString(2, password);
            
            // Execute the SQL query
            ResultSet rs = pst.executeQuery();
            
            // Start HTML response
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Login Result</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; }");
            out.println(".container { background-color: #fff; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); width: 350px; text-align: center; }");
            out.println("h2 { margin-bottom: 30px; }");
            out.println("p { font-size: 16px; margin-top: 20px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
            
            // Check if the result set contains any rows
            if (rs.next()) {
                out.println("<h2>Login successful!</h2>");
            } else {
                out.println("<h2>Incorrect username or password!</h2>");
            }
            
            // Close HTML response
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
            // Close the database connection
            con.close();
        } catch(Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
        
        out.close();
    }
}
