package com.example.lab8;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreDB implements ITaskDAO {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/dao";
    static final String USER = "postgres";
    static final String PASS = "1";
    private Connection connection = null;

    private void setConnection() {
        try {
            this.connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Task> getAllTasks() {
        setConnection();
        ObservableList<Task> tasks = FXCollections.observableArrayList();

        try {
            System.out.println("test");
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM test");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("target"),
                        rs.getString("dt")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return tasks;
    }

    @Override
    public Task getTaskById(int id) {
        return null;
    }

    @Override
    public void addTask(Task task) {
        setConnection();
        String query = "INSERT INTO test(id, target, dt) VALUES(?, ?, ?)";

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, (int) task.getId());
            pst.setString(2, task.getTarget());
            pst.setString(3, task.getDt());
            pst.executeUpdate();
            System.out.println("success created user");

        } catch (SQLException error) {
            Logger logger = Logger.getLogger(PostgreDB.class.getName());
            logger.log(Level.SEVERE, error.getMessage(), error);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Override
    public void updateTask(Task task) {
        setConnection();

        try {
            PreparedStatement ps = this.connection.prepareStatement("UPDATE test SET target = ?, dt= ? WHERE id = ?");
            ps.setString(1, task.getTarget());
            ps.setString(2, task.getDt());
            ps.setInt(3, (int) task.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteTask(int id) {
        setConnection();

        ResultSet resultSet;
        String query = "DELETE FROM test WHERE id = ?";

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException error) {
            Logger logger = Logger.getLogger(PostgreDB.class.getName());
            logger.log(Level.SEVERE, error.getMessage(), error);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
