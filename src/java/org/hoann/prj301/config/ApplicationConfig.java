package org.hoann.prj301.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplicationConfig extends HttpServlet {

    private static final String WELCOME_PAGE = "login.jsp";

    private static final String LOGIN_ACTION = "Login";
    private static final String LOGIN_ROUTE = "login";

    private static final String SEARCH_ACTION = "Search";
    private static final String SEARCH_ROUTE = "search";

    private static final String BLOCK_ACTION = "Block";
    private static final String BLOCK_ROUTE = "block";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = WELCOME_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = WELCOME_PAGE;
            } else {
                switch (action) {
                    case LOGIN_ACTION:
                        url = LOGIN_ROUTE;
                        break;
                    case SEARCH_ACTION:
                        url = SEARCH_ROUTE;
                        break;
                    case BLOCK_ACTION:
                        url = BLOCK_ROUTE;
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            log("Error at application config");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
