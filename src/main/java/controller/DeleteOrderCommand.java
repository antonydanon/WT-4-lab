package controller;

import service.OrderService;
import service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOrderCommand implements Command{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {

        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            int roomId = Integer.parseInt(req.getParameter("roomId"));
            OrderService orderService = new OrderService();
            orderService.deleteOrder(orderId);
            RoomService roomService = new RoomService();
            roomService.updateRoomStatus(roomId, false);
            req.getRequestDispatcher("Controller?COMMAND=GET_ROOMS").forward(req, res);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
