import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        response.setHeader("host", request.getServerName() + ":39756");
        response.setHeader("Cache-Control", "max-age=0");
        response.setHeader("Accept-Language", "da-DK,da;q=0.8," + request.getHeader("Accept-Language").replace("0.8", "0.6") + ",en;q=0.4");
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table><tr><th>Header</th><th>Value</th></tr>"
                    + "<tr><td>host</td><td>" + response.getHeader("host") + "</td></tr>"
                    + "<tr><td>connection</td><td>" + request.getHeader("Connection") + "</td></tr>"
                    + "<tr><td>cache-control</td><td>" + response.getHeader("Cache-Control") + "</td></tr>"
                    + "<tr><td>accept</td><td>" + request.getHeader("Accept") + "</td></tr>"
                    + "<tr><td>user-agent</td><td>" + request.getHeader("User-Agent") + "</td></tr>"
                    + "<tr><td>accept-encoding</td><td>" + request.getHeader("Accept-Encoding").replace("br", "sdch") + "</td></tr>"
                    + "<tr><td>accept-language</td><td>" + response.getHeader("Accept-Language") + "</td></tr>"
                    + "</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
