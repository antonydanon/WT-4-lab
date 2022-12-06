package dao;

import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class OrderDAO implements Callable<List<Order>> {
    private String command;
    private int userId;
    private int roomId;
    private int orderId;

    public OrderDAO(String command, int userId, int roomId, int orderId) {
        this.command = command;
        this.userId = userId;
        this.roomId = roomId;
        this.orderId = orderId;
    }

    public OrderDAO(String command, int userId, int roomId) {
        this.command = command;
        this.userId = userId;
        this.roomId = roomId;
    }

    public OrderDAO(String command, int orderId) {
        this.command = command;
        this.orderId = orderId;
    }

    public OrderDAO() {

    }

    public List<Order> call() {
        switch (command) {
            case "orderRoom": {
                orderRoom(userId, roomId);
                break;
            }
            case "unorderRoom": {
                unorderRoom(roomId);
                break;
            }
            case "getUserOrders": {
                return getUserOrders(userId);
            }
            case "deleteOrder": {
                deleteOrder(orderId);
                break;
            }
        }
        return null;
    }

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

    public boolean unorderRoom(int roomId) {
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
            String sql = "DELETE FROM orders WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
