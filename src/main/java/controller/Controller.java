package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest req, HttpServletResponse res){
        String choice = req.getParameter("COMMAND");
        Commands commands = new Commands();
        Command command = commands.findCommand(choice);
        command.execute(req, res);
    }
}
