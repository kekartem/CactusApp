package servlets;

import entities.Item;
import entities.User;
import helpers.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        if(user != null) {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            Map<String, Object> root = new HashMap<>();
            root.put("items", cart);
            List<Long> numbers = new ArrayList<>();

            if(cart != null) {
                for (Item item : cart) {
                    numbers.add(item.getId());
                }
            }

            root.put("numbers", numbers);
            Helper.render(request, response, "cart.ftl", root);
        }
        else {
            response.sendRedirect("/login");
        }

    }
}
