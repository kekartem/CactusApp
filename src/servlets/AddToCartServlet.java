package servlets;

import dao.ItemDAO;
import entities.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddToCart")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("current_user") == null){
            response.sendRedirect("/login");
            return;
        }
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int id = Integer.parseInt(request.getParameter("id"));
        ItemDAO itemDAO = new ItemDAO();
        for (Item i : cart) {
            if (i.getId() == id) {
                response.sendRedirect("/catalog");
                return;
            }
        }
        cart.add(itemDAO.get(id));
        session.setAttribute("cart", cart);
        response.sendRedirect("/catalog");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
