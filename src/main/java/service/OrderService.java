package service;

import dao.OrderDAO;
import model.Order;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderService {
    ExecutorService pool = Executors.newFixedThreadPool(3);

    public boolean orderRoom(int userId, int roomId) {
        try {
            Callable<List<Order>> orderRoomCallable = new OrderDAO("orderRoom", userId, roomId);
            boolean res = pool.submit(orderRoomCallable) != null;
            pool.shutdown();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean unorderedRoom(int roomId) {
        Callable<List<Order>> unorderRoomCollable = new OrderDAO("unorderRoom", 0, roomId);
        boolean res = pool.submit(unorderRoomCollable) != null;
        pool.shutdown();
        return res;
    }

    public List<Order> getUserOrders(int userId) {
        try {
            Callable<List<Order>> getUserOrders = new OrderDAO("getUserOrders", userId, 0);
            List<Order> res = pool.submit(getUserOrders).get();
            pool.shutdown();
            return res;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteOrder(int orderId) {
        Callable<List<Order>> deleteOrderCallable = new OrderDAO("deleteOrder", orderId);
        boolean res = pool.submit(deleteOrderCallable) != null;
        pool.shutdown();
        return res;
    }
}
