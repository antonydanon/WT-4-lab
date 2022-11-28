package dao;

import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public boolean orderRoom(int userId, int roomId) {
        try {
            Connection connection = Connector.getConnection();
            String sql = "INSERT INTO orders (user_id, room_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, roomId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean unorderedRoom(int roomId) {
        try {
            Connection connection = Connector.getConnection();
            String sql = "DELETE FROM orders WHERE room_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, roomId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Order> getUserOrders(int userId) {
        try {
            Connection connection = Connector.getConnection();
            String sql = "SELECT * FROM orders WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            RoomDAO roomDAO = new RoomDAO();
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3)
                );
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteOrder(int orderId) {
        try {
            Connection connection = Connector.getConnection();
            String sql = "DELETE FROM orders WHERE order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
