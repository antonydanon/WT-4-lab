package controller;

import model.User;;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        try {
            String userLogin = req.getParameter("RegUserLogin");
            String userPassword = req.getParameter("RegPassword");
            String userCheckPassword = req.getParameter("RegCheckPassword");

            if (!userPassword.equals(userCheckPassword)) {
                System.out.println("Passwords don't match");
                return;
            }
            UserService userService = new UserService();
            User user = new User(userLogin, userPassword, 2);
            if (userService.createNewUser(user)) {
                req.getSession(true).setAttribute("userId", user.getId());
                req.getSession().setAttribute("userLogin", user.getLogin());
                req.getSession().setAttribute("userRole", user.getRole());
                res.sendRedirect("Controller?COMMAND=GET_ROOMS");
            } else {
                req.getRequestDispatcher("index.jsp").forward(req, res);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
