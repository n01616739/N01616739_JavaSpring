package com.example.ejbtutorial;

import com.example.ejb.LoggerBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logger")
public class LoggerServlet extends HttpServlet {

    @EJB
    private LoggerBean loggerBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            loggerBean.logAction(action);
            resp.getWriter().write("Logged action: " + action);
        } else {
            resp.getWriter().write("Please provide an 'action' query parameter.");
        }
    }
}

