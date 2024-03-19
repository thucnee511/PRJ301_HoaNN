package org.hoann.prj301.routes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hoann.prj301.repositories.user.UserDTO;
import org.hoann.prj301.repositories.user.UserERROR;
import org.hoann.prj301.services.UserService;

@WebServlet(name = "LoginRoute", urlPatterns = {"/login"})
public class LoginRoute extends HttpServlet {

    public static final String ERROR = "login.jsp";
    public static final String AD_SUCCESS = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            String userId = request.getParameter("userId");
            String password = request.getParameter("password");
            UserService service = UserService.getInstance();
            UserDTO user = service.checkLogin(userId, password);
            HttpSession session = request.getSession();
            session.setAttribute("LOGIN_USER", user);
            if (user.getRoleId().equals("AD")) {
                url = AD_SUCCESS;
            }
        } catch (UserERROR e) {
            url = ERROR;
            request.setAttribute("ERROR_MESSAGE", e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
