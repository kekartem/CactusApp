package servlets;

import dao.ItemDAO;
import entities.Item;
import entities.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxSearchServlet extends HttpServlet {
    private ItemDAO itemDAO;

    @Override
    public void init() {
        itemDAO = new ItemDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");

        List<Item> items = itemDAO.getByLikePattern(query);

        JSONArray ja = new JSONArray();

        for (Item item: items) {
            ja.put(new JSONObject(item));
        }
        JSONObject jo = new JSONObject();
        jo.put("objects", ja);

        response.setContentType("text/json");
        response.getWriter().write(jo.toString());

    }
}
