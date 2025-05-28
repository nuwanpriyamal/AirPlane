package service;

import dao.UserDAO;
import dto.UserDTO;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public UserDTO authenticateUser(String username, String password) {
        try {
            UserDTO user = userDAO.getUserByUsername(username);
            if (user != null) {
                // For now, just check if password equals "changeme"
                if ("changeme".equals(password)) {
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean registerCustomer(String username, String name, String password) {
        try {
            UserDTO user = new UserDTO(0, username, name, "CUSTOMER");
            return userDAO.addUser(user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UserDTO getUserById(int id) {
        try {
            return userDAO.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserDTO getUserByUsername(String username) {
        try {
            return userDAO.getUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
} 