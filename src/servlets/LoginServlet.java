package servlets;

import dao.UserDAO;
import entities.Item;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static helpers.Helper.render;


public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String checkbox = request.getParameter("checkbox");
        if (login != null && !"".equals(login) && password != null && !"".equals(password)) {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.login(login, password);
            if (user != null) {
                session.setAttribute("current_user", user);
                if (checkbox != null) {
                    response.addCookie(new Cookie("current_user", user.getLogin()));
                }
                response.sendRedirect("/profile");
            } else response.sendRedirect("/registration");

        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String cookieName = "current_user";
        Cookie cookie = null;
            if(cookies != null) {
                for (Cookie c : cookies) {
                    if (cookieName.equals(c.getName())) {
                        cookie = c;
                        break;
                    }
                }
            }
            if (cookie != null) {
                UserDAO userDAO = new UserDAO();
                User user = userDAO.profile(cookie.getValue());
                HttpSession session = request.getSession();
                session.setAttribute("current_user", user);
                response.sendRedirect("/profile");
            } else {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("current_user");
                List<Item> cart = new ArrayList<>();
                session.setAttribute("cart", cart);
                if (user != null) {
                    response.sendRedirect("/profile");
                } else {
                    Map<String, Object> root = new HashMap<>();
                    render(request, response, "auth.ftl", root);
                }
            }


        }
}

