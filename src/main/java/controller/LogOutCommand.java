package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {

        try {
            req.getSession().invalidate();
            req.getRequestDispatcher("index.jsp").forward(req, res);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
