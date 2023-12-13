package com.labjdbc.data;

import java.util.List;

import com.labjdbc.domain.userdomain.User;

public interface DataBaseActions {

    int READ = 1;
    int WRITE = 2;

    public int insert(User user);
    public int update(User user);
    public int delete(User user);
    public List<User> select();
}
