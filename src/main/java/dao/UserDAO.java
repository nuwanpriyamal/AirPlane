package dao;

import dto.UserDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public UserDTO getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRowToUserDTO(rs);
            }
        }
        return null;
    }

    public UserDTO getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRowToUserDTO(rs);
            }
        }
        return null;
    }

    public List<UserDTO> getAllUsers() throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                users.add(mapRowToUserDTO(rs));
            }
        }
        return users;
    }

    public boolean addUser(UserDTO user, String password) throws SQLException {
        String sql = "INSERT INTO users (username, name, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, password);
            ps.setString(4, user.getRole());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateUser(UserDTO user) throws SQLException {
        String sql = "UPDATE users SET username = ?, name = ?, role = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getRole());
            ps.setInt(4, user.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private UserDTO mapRowToUserDTO(ResultSet rs) throws SQLException {
        return new UserDTO(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("name"),
            rs.getString("role")
        );
    }
} 