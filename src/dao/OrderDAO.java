package dao;

import entities.Item;
import entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public Order get(long id) {
        try {
            Connection conn = helpers.Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from orders where id = ?"
            );
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Order> getByCustomer(long id) {
        try {
            Connection conn = helpers.Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from orders where buyerid = ?"
            );
            ps.setString(1, id+"");
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                orders.add(
                        new Order(
                                rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4)
                        ));
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }


    public void save(Order x) {
        try {
            Connection conn = helpers.Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "insert into orders(BUYERID, ITEMID, ORDERTIME) " +
                            "values (?,?,?)", Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, x.getBuyerId());
            ps.setString(2, x.getItemId());
            ps.setString(3, x.getOrderTime());

            int updRows = ps.executeUpdate();
            if (updRows == 0) {
                //Если ничего не было изменено, значит возникла ошибка
                //Возбуждаем соответсвующее исключений
                throw new SQLException();
            }
//Достаём созданное Id пользователя
            try (ResultSet set = ps.getGeneratedKeys()) {
                //Если id  существет,обновляем его у подели.
                if (set.next()) {
                    x.setId(set.getLong(1));
                } else {
                    //Модель сохранилась но не удаётся получить сгенерированный id
                    //Возбуждаем соответвующее исключение
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
