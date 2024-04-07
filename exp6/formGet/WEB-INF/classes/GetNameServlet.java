import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetNameServlet")
public class GetNameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>GET Method Example</title></head>");
        out.println("<body>");
        out.println("<h2>Using GET method to read data</h2>");
        out.println("<ul>");
        out.println("<li><b>First Name:</b> " + request.getParameter("firstname") + "</li>");
        out.println("<li><b>Last Name:</b> " + request.getParameter("lastname") + "</li>");
        out.println("</ul>");
        out.println("</body></html>");
    }
}
