package org.hoann.prj301.routes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hoann.prj301.repositories.service.ServiceDTO;
import org.hoann.prj301.services.ServeService;

@WebServlet(name = "UpdateServiceRoute", urlPatterns = {"/updateservice"})
public class UpdateServiceRoute extends HttpServlet {

    private static final String ERROR = "admin.js";
    private static final String SUCCESS = "admin.js";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            ServeService service = ServeService.getInstance();
            String serviceId = request.getParameter("serviceId");
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            ServiceDTO serviceObj = new ServiceDTO(serviceId, name, price, true);
            url = service.put(serviceObj) ? SUCCESS : ERROR;
        } catch (Exception e) {
            log("Error at block user route" + e);
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
