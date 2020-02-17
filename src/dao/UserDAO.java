package dao;

import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {
    @Override
    public User get(long id) {
        try {
            Connection conn = helpers.Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from users where login = ? and password = ?"
            );
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }


    @Override
    public List<User> getAll() {
        try {
            Connection conn = helpers.Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from users"
            );
            ResultSet rs = ps.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new User(
                        rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8)
                ));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void save(User x) {
        try {
            Connection conn = helpers.Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "insert into users(LOGIN, PASSWORD, LAST_NAME, FIRST_NAME, EMAIL, ADDRESS, PHOTOPATH) " +
                            "values (?,?,?,?,?,?,?)" , Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, x.getLogin());
            ps.setString(2, x.getPassword());
            ps.setString(3, x.getLastName());
            ps.setString(4, x.getName());
            ps.setString(5, x.getEmail());
            ps.setString(6, x.getAddress());
            ps.setString(7, x.getPhotoPath());
            int updRows = ps.executeUpdate();
            if (updRows == 0) {
                //Если ничего не было изменено, значит возникла ошибка
                //Возбуждаем соответсвующее исключений
                throw new SQLException();
            }
//Достаём созданное Id пользователя
            try (ResultSet set = ps.getGeneratedKeys();) {
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
            e.printStackTrace();

        }
    }

    public User login(String login, String password) {
        try {
            Connection conn = helpers.Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from users where login = ? and password = ?"
            );
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public User profile(String login) {
        try {
            Connection conn = helpers.Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from users where login = ?"
            );
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

}