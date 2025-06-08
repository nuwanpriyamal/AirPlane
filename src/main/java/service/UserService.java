package service;

import dao.UserDAO;
import dto.UserDTO;
import service.PasswordUtil;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public UserDTO authenticateUser(String username, String password) {
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            java.sql.Connection conn = dao.DBConnection.getConnection();
            java.sql.PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String dbPassword = rs.getString("password");
                String hashedInput = PasswordUtil.hashPassword(password);
                if (dbPassword != null && dbPassword.equals(hashedInput)) {
                    return userDAO.getUserByUsername(username);
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
            String hashed = PasswordUtil.hashPassword(password);
            return userDAO.addUser(user, hashed);
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

    public java.util.List<dto.UserDTO> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }
} 