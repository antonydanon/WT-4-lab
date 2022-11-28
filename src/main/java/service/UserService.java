package service;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserService {

    ExecutorService pool = Executors.newFixedThreadPool(3);

    public boolean createNewUser(User user)  {
        try {
            Callable<User> createNewUserCallable = new UserDAO("createNewUser", user);
            boolean res = pool.submit(createNewUserCallable) != null;
            pool.shutdown();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User findUser(User user) {

        try {
            Callable<User> findUserCallable = new UserDAO("findUser", user);
            User res = pool.submit(findUserCallable).get();
            pool.shutdown();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
