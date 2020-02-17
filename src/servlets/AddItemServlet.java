package servlets;

import dao.ItemDAO;
import dao.UserDAO;
import entities.Item;
import entities.User;
import helpers.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddItemServlet")
public class AddItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String label = request.getParameter("label");
        String description = request.getParameter("description");

        if (
                label != null && !"".equals(label) && description != null && !"".equals(description)
        ) {

            Item item = new Item();
            item.setLabel(label);
            item.setDescription(description);
            User user = (User) session.getAttribute("current_user");
            item.setCreator("" + user.getLogin() + "");

            item.setPhotoPath("shiiit");

            item.setOrders("0");
            ItemDAO itemDAO = new ItemDAO();
            itemDAO.save(item);
            response.sendRedirect("/addItem");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        if(user != null) {
            Map<String, Object> root = new HashMap<>();
            Helper.render(request, response, "addItem.ftl", root);
        }
        else {
            response.sendRedirect("/login");
        }
    }
}
