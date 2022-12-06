package dao;

import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class RoomDAO implements Callable<List<Room>> {
    private String command;
    private int roomId;
    private boolean status;
    private int roomNumber;

    public RoomDAO(String command, int roomId, boolean status, int roomNumber) {
        this.command = command;
        this.roomId = roomId;
        this.status = status;
        this.roomNumber = roomNumber;
    }

    public RoomDAO(String command, int roomId, boolean status) {
        this.command = command;
        this.roomId = roomId;
        this.status = status;
    }

    public RoomDAO(String command, int roomId, int roomNumber) {
        this.command = command;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
    }

    public RoomDAO(String command) {
        this.command = command;
    }

    public RoomDAO() {

    }

    public List<Room> call() throws Exception {
        switch (command) {
            case "getRooms": {
                return getRooms();
            }
            case "updateRoomStatus": {
                updateRoomStatus(roomId, status);
                return null;

            }
            case "getRoom": {
                ArrayList<Room> rooms = new ArrayList<Room>();
                rooms.add(getRoom(roomId));
                return rooms;

            }
            case "addNewRoom": {
                addNewRoom(roomNumber);
                return null;

            }
        }
        return null;
    }

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
            String sql = "UPDATE rooms SET is_reserved = ? WHERE id = ?";
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
            String sql = "SELECT * FROM rooms WHERE id = ?";
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
            String sql = "INSERT INTO rooms (number, is_reserved) VALUES (?, false)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, roomNumber);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
