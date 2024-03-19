package org.hoann.prj301.services;

import java.util.ArrayList;
import java.util.List;
import org.hoann.prj301.dao.UserDAO;
import org.hoann.prj301.repositories.user.UserDTO;
import org.hoann.prj301.repositories.user.UserERROR;

public class UserService {

    private static UserService instance;
    private static final UserDAO userDao = UserDAO.getInstance();

    private UserService() {
    }

    public static UserService getInstance() {
        return instance = instance == null ? new UserService() : instance;
    }

    public UserDTO checkLogin(String userId, String password) throws UserERROR {
        UserDTO user = userDao.get(userId);
        if (user == null || !user.getPassword().equals(password)) {
            throw new UserERROR("Invalid userId or password");
        }
        return user;
    }

    public List<UserDTO> search(String keyword) {
        List<UserDTO> list = new ArrayList<>();
        List<UserDTO> users = userDao.get();
        users.forEach(user -> {
            if (user.getName().contains(keyword)) {
                list.add(user);
            }
        });
        return list;
    }

    public boolean block(String userId) {
        UserDTO user = userDao.get(userId);
        user.setStatus(false);
        userDao.put(user);
        return true;
    }
}
