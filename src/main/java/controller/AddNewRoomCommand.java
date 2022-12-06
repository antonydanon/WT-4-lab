package controller;

import service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewRoomCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        try {
            int roomNumber = Integer.parseInt(req.getParameter("roomNumber"));
            RoomService roomService = new RoomService();
            roomService.addNewRoom(roomNumber);
            res.sendRedirect("Controller?COMMAND=GET_ROOMS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
