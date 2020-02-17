package servlets;

import dao.ItemDAO;
import dao.OrderDAO;
import entities.Item;
import entities.Order;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "MakeOrderServlet")
public class MakeOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration e = request.getParameterNames();
        OrderDAO orderDAO = new OrderDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        ItemDAO itemDAO = new ItemDAO();
        while(e.hasMoreElements()){
            String name = (String)e.nextElement();
            if(name.startsWith("item")){
                String value = request.getParameter(name);
                Order order = new Order();
                order.setBuyerId(user.getId()+"");
                order.setItemId(value);
                order.setOrderTime(System.currentTimeMillis()+"");
                orderDAO.save(order);

                Item item = itemDAO.get(Long.parseLong(value));
                itemDAO.updateOrders(Long.parseLong(value), (Integer.parseInt(item.getOrders())+1)+"");
            }

        }

        response.sendRedirect("/catalog");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
