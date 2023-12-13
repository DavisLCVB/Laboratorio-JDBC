package com.labjdbc.data;

import java.sql.SQLException;
import java.util.List;

import com.labjdbc.domain.userdomain.User;

public interface DataBaseActions {

    boolean READ = false;
    boolean WRITE = true;

    public int insert(User user) throws SQLException;
    public int update(User user) throws SQLException;
    public int delete(User user) throws SQLException;
    public List<User> select() throws SQLException;
}
