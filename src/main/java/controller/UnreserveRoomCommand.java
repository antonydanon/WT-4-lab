package controller;

import service.OrderService;
import service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnreserveRoomCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        try {
            int userId = (int) req.getSession().getAttribute("userId");
            int roomId = Integer.parseInt(req.getParameter("roomId"));

            OrderService orderService = new OrderService();
            orderService.unorderRoom(roomId);

            RoomService roomService = new RoomService();
            roomService.updateRoomStatus(roomId, false);

            req.getRequestDispatcher("Controller?COMMAND=GET_ROOMS").forward(req, res);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

