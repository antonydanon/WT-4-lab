package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean createNewUser(User user) {
        boolean result = false;
        try {
            Connection connection = Connector.getConnection();
            String sql = "INSERT INTO users (user_login, user_password, user_role) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRole());
            result = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public User findUser(User user) {
        try {
            Connection connection = Connector.getConnection();
            String sql = "SELECT * FROM users WHERE user_login = ? AND user_password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setRole(resultSet.getInt(4));
            }
            if (user.getLogin() != null) {
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
