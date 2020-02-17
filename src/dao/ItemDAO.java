package dao;

import entities.Item;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements DAO<Item> {

    @Override
    public Item get(long id) {
        try {
            Connection conn = helpers.Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from items where id = ?"
            );
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Item(
                        rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<Item> getAll() {
        try {
            Connection conn = helpers.Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from items"
            );
            ResultSet rs = ps.executeQuery();
            List<Item> items = new ArrayList<>();
            while (rs.next()) {
                items.add(
                        new Item(
                        rs.getLong(1), rs.getString(2), rs.getString(3),
                                rs.getString(4), rs.getString(5), rs.getString(6)
                ));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }



    @Override
    public void save(Item x) {
        try {
            Connection conn = helpers.Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "insert into items(LABEL, DESCRIPTION, ORDERS, CREATOR, PHOTOPATH) " +
                            "values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, x.getLabel());
            ps.setString(2, x.getDescription());
            ps.setString(3, x.getOrders());
            ps.setString(4, x.getCreator());
            ps.setString(5, x.getPhotoPath());

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


    public void delete(Long id) throws SQLException {
        Connection connection = helpers.Database.getConnection();
        //id не может быть меньше 0(в противном случае выбрасываем исключение).
        if (id < 0L) throw new IllegalArgumentException();
        /* Мы выполняем sql-запрос, удаляя строку из таблицы по параметру id. */
        //Создаём новый объект Statement
        //Использование try-with-resources необходимо для гарантированного закрытия statement,вне зависимости от успешности операции.
        try (Statement statement = connection.createStatement()) {
            //Выолняем запрос и получаем колличество изменённых строк
            int updRows = statement.executeUpdate("DELETE from items where id = " + id + ";");
            if (updRows == 0) {
                //Если ничего не было изменено, значит возникла ошибка
                //Возбуждаем соответсвующее исключение
                throw new SQLException();
            }
        } catch (SQLException e) {
            //Если удаление провалилось, обернём пойманное исключение в непроверяемое и пробросим дальше(best-practise)
            throw new IllegalStateException(e);
        }
    }


    public List<Item> getByLikePattern(String pattern) {
        try {
            Connection conn = helpers.Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from items where label like ?"
            );
            ps.setString(1, "%" + pattern + "%");
            ResultSet rs = ps.executeQuery();
            List<Item> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new Item(
                        rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6)
                ));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }


    public boolean updateOrders(Long id, String orders){
        try {
            Connection conn = helpers.Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE items SET orders = ? where id = ?"
            );
            ps.setString(1, orders);
            ps.setLong(2, id);
            ps.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

}
