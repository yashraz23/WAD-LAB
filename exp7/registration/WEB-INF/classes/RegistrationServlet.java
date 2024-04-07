import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "admin"); // replace username and password with your MySQL credentials
            PreparedStatement pst = con.prepareStatement("INSERT INTO users (username, password, email) VALUES (?, ?, ?)");
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, email);
            
            int i = pst.executeUpdate();
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Registration Result</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh; }");
            out.println(".container { background-color: #fff; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); width: 350px; text-align: center; }");
            out.println("h2 { margin-bottom: 30px; }");
            out.println("p { font-size: 16px; margin-top: 20px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
            if (i > 0) {
                out.println("<h1>User registered successfully!</h1>");
                out.println("<p>Username: " + username + "</p>");
                out.println("<p>Email: " + email + "</p>");
            } else {
                out.println("<h2>Error occurred while registering user!</h2>");
            }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
            con.close();
        } catch(Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
