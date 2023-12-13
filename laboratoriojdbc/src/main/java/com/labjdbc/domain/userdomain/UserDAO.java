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
    private Connection transaConnection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public UserDAO() {
    }

    public UserDAO(Connection transaConnection) {
        this.transaConnection = transaConnection;
    }

    public void setConnection(Connection connection){
        this.transaConnection = connection;
    }

    public Connection getConnection(){
        return this.transaConnection;
    }

    @Override
    public int insert(User user) throws SQLException {
        int registros = 0;
        Connection connection = DataBaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(SQL_INSERT);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        registros = preparedStatement.executeUpdate();
        DataBaseConnection.close(preparedStatement);
        if (this.transaConnection == null)
            DataBaseConnection.close(connection);
        return registros;
    }

    @Override
    public int update(User user) throws SQLException {
        int registros = 0;
        Connection connection = this.transaConnection != null ? this.transaConnection
                : DataBaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(SQL_UPDATE);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getIDUser());
        registros = preparedStatement.executeUpdate();
        DataBaseConnection.close(preparedStatement);
        if (this.transaConnection == null)
            DataBaseConnection.close(connection);
        return registros;
    }

    @Override
    public int delete(User user) throws SQLException {
        int registros = 0;
        Connection connection = this.transaConnection != null ? this.transaConnection
                : DataBaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(SQL_DELETE);
        preparedStatement.setInt(1, user.getIDUser());
        registros = preparedStatement.executeUpdate();
        DataBaseConnection.close(preparedStatement);
        if (this.transaConnection == null)
            DataBaseConnection.close(connection);
        return registros;
    }

    @Override
    public List<User> select() throws SQLException {
        User user = null;
        List<User> personas = new ArrayList<>();
        Connection connection = this.transaConnection != null ? this.transaConnection
                : DataBaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(SQL_SELECT);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int idUser = resultSet.getInt("id_user");
            String userName = resultSet.getString("user");
            String password = resultSet.getString("password");
            user = new User(idUser, userName, password);
            personas.add(user);
        }
        DataBaseConnection.close(resultSet, preparedStatement);
        if (this.transaConnection == null)
            DataBaseConnection.close(connection);
        return personas;
    }

}
