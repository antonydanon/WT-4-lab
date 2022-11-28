package service;

import dao.RoomDAO;
import model.Room;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomService {
    ExecutorService pool = Executors.newFixedThreadPool(3);

    public List<Room> getRooms() {
        try {
            Callable<List<Room>> getRoomsCallable = new RoomDAO("getRooms");
            List<Room> res = pool.submit(getRoomsCallable).get();
            pool.shutdown();
            return res;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateRoomStatus(int roomId, boolean status) {
        Callable<List<Room>> updateRoomStatusCallable = new RoomDAO("updateRoomStatus", roomId, status);
        boolean res = pool.submit(updateRoomStatusCallable) != null;
        pool.shutdown();
        return res;
    }

    public boolean addNewRoom(int roomNumber) {
        Callable<List<Room>> addNewRoomCallable = new RoomDAO("addNewRoom", 0, roomNumber);
        boolean res = pool.submit(addNewRoomCallable) != null;
        pool.shutdown();
        return res;
    }
}
