package dao;

import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    public List<Room> getRooms() {
        try {
            Connection connection = Connector.getConnection();
            String sql = "SELECT * FROM rooms";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Room> rooms = new ArrayList();
            while(resultSet.next()){
                Room room = new Room(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getBoolean(3)
                );
                rooms.add(room);
            }
            return rooms;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateRoomStatus(int roomId, boolean status) {
        try {
            Connection connection = Connector.getConnection();
            String sql = "UPDATE rooms SET room_reserv = ? WHERE room_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, roomId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public Room getRoom(int roomId) {
        try {
            Connection connection = Connector.getConnection();
            String sql = "SELECT * FROM rooms WHERE room_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Room room = new Room(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getBoolean(3)
            );
            return room;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addNewRoom(int roomNumber) {
        try {
            Connection connection = Connector.getConnection();
            String sql = "INSERT INTO rooms (room_number, room_reserv) VALUES (?, false)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, roomNumber);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
