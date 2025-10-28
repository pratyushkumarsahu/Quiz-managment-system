package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import dao.UserDao;
import dao.UserDaoImpl;


@WebServlet("/forgot")
public class forgot extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String recovery = request.getParameter("recovery");

        UserDao userDao = new UserDaoImpl();
        if (userDao.isRecoveryCorrect(username, recovery))
        {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("newPass.jsp");
        }
        else {
            response.sendRedirect("forgot.jsp?error=1");
            System.out.println("Recovery failed: Invalid username or recovery code");
        }
    }

}
