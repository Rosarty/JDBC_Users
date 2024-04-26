package org.example.app.repository.impl;

import org.example.app.database.DatabaseConnector;
import org.example.app.entity.User;
import org.example.app.repository.AppRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements AppRepository<User> {

    private final static String TABLE_USERS = "users";

    @Override
    public String create(User user) {
        String sql = "INSERT INTO " + TABLE_USERS + " (name, email) VALUES (?, ?)";
        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();
            return "User added successfully.";
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public Optional<List<User>> read() {
        try (Connection connection = DatabaseConnector.connect();
             Statement stmt = connection.createStatement()) {
            List<User> userList = new ArrayList<>();
            String sql = "SELECT id, name, email FROM " + TABLE_USERS;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                userList.add(new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
            return Optional.of(userList);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public String update(User user) {
        if (readById(user.getId()).isEmpty()) {
            return "User not found.";
        } else {
            String sql = "UPDATE " + TABLE_USERS + " SET name = ?, email = ? WHERE id = ?";
            try (Connection connection = DatabaseConnector.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getEmail());
                pstmt.setLong(3, user.getId());
                pstmt.executeUpdate();
                return "User updated successfully.";
            } catch (SQLException e) {
                return "Error: " + e.getMessage();
            }
        }
    }

    @Override
    public String delete(Long id) {
        if (!isIdExists(id)) {
            return "User not found.";
        } else {
            String sql = "DELETE FROM " + TABLE_USERS + " WHERE id = ?";
            try (Connection connection = DatabaseConnector.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setLong(1, id);
                pstmt.executeUpdate();
                return "User deleted successfully.";
            } catch (SQLException e) {
                return "Error: " + e.getMessage();
            }
        }
    }

    @Override
    public Optional<User> readById(Long id) {
        String sql = "SELECT id, name, email FROM " + TABLE_USERS + " WHERE id = ?";
        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    private boolean isIdExists(Long id) {
        String sql = "SELECT COUNT(id) FROM " + TABLE_USERS + " WHERE id = ?";
        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}