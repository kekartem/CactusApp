package servlets;

import dao.UserDAO;
import entities.User;
import helpers.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class RegistrationServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String lastName = request.getParameter("lastName");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        if (
                login != null && !"".equals(login) && password != null && !"".equals(password) &&
                        lastName != null && !"".equals(lastName) && name != null && !"".equals(name) &&
                        email != null && !"".equalsIgnoreCase(email) && address != null && !"".equalsIgnoreCase(address)
        ) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setLastName(lastName);
            user.setName(name);
            user.setEmail(email);
            user.setAddress(address);


            Part p = request.getPart("photo");
            String localdir = "uploads";
            String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
            File dir = new File(pathDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String[] filename_data = Helper.getSubmittedFileName(p).split("\\.");
            String filename = Math.random() + "." + filename_data[filename_data.length - 1];
            String fullpath = pathDir + File.separator + filename;
            p.write(fullpath);



            user.setPhotoPath("/" + localdir + "/" + filename);
            UserDAO userDAO = new UserDAO();
            userDAO.save(user);
            response.addCookie(new Cookie("current_user", user.getLogin()));
            session.setAttribute("current_user", user);
            response.sendRedirect("/profile");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        String cookieName = "current_user";
        Cookie cookie = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    cookie = c;
                    break;
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
                if (user != null) {
                    response.sendRedirect("/profile");
                } else {
                    Map<String, Object> root = new HashMap<>();
                    try {
                        Class.forName("helpers.Helper");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Helper.render(request, response, "registration.ftl", root);
                }
                doPost(request, response);
            }


        }
    }

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
