package com.epam.servlets;

import com.epam.dao.UserDAO;
import com.epam.dao.beans.UserBean;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Oleg on 5/24/2016.
 */


@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(RegisterServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");


        {

            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            try {
                PrintWriter out = response.getWriter();
                if (UserDAO.createUser(new UserBean(name, password, email), con)) {
                    logger.info("User registered =" + name);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
                    out.println("<font color=green>Registration successful, please login below.</font>");
                    rd.include(request, response);
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/registerForm.html");
                    rd.include(request, response);
                    out.println("<font color=red>User with given name already exist, chose another name.</font>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("Database connection problem");
                throw new ServletException("DB Connection problem.");
            }
        }

    }

}