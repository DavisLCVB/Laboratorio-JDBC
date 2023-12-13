package com.labjdbc.domain.userdomain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.labjdbc.data.DataBaseActions;
import com.labjdbc.data.DataBaseConnection;

public class UserDAO implements DataBaseActions {

    private static final String SQL_INSERT = "INSERT INTO users (user, password) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE users SET user = ?, password = ? WHERE id_user = ?";
    private static final String SQL_DELETE = "DELETE FROM users WHERE id_user = ?";
    private static final String SQL_SELECT = "SELECT id_user, user, password FROM users ORDER BY id_user";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public int insert(User user) {
        int registros = 0;
        try {
            connection = DataBaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            registros = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                DataBaseConnection.close(preparedStatement, connection);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public int update(User user) {
        int registros = 0;
        try {
            connection = DataBaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getIDUser());
            registros = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                DataBaseConnection.close(preparedStatement, connection);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public int delete(User user) {
        int registros = 0;
        try {
            connection = DataBaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, user.getIDUser());
            registros = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                DataBaseConnection.close(preparedStatement, connection);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public List<User> select() {
        User user = null;
        List<User> personas = new ArrayList<>();
        try {
            connection = DataBaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idUser = resultSet.getInt("id_user");
                String userName = resultSet.getString("user");
                String password = resultSet.getString("password");
                user = new User(idUser, userName, password);
                personas.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                DataBaseConnection.close(resultSet, preparedStatement, connection);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return personas;
    }

}
