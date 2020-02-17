package servlets;

import dao.ItemDAO;
import dao.OrderDAO;
import entities.Order;
import entities.User;
import helpers.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        if(user != null){
            Map<String, Object> root = new HashMap<>();
            root.put("user", user);
            OrderDAO orderDAO = new OrderDAO();
            List<Order> orders = orderDAO.getByCustomer(user.getId());
            ItemDAO itemDAO = new ItemDAO();
            if(orders != null) {
                for (Order order : orders) {
                    order.setItemId(itemDAO.get(Integer.parseInt(order.getItemId())).getLabel());
                }
            }
            root.put("orders", orders);
            Helper.render(request, response, "profile.ftl", root);
        } else {
            response.sendRedirect("/login");
        }
    }
}
