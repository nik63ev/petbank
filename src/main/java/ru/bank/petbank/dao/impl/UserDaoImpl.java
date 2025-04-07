package ru.bank.petbank.dao.impl;

import ru.bank.petbank.dao.UserDao;
import ru.bank.petbank.model.UserInfo;

import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    private Map<Long, UserInfo> users = new HashMap<Long, UserInfo>();
    @Override
    public UserInfo getByID(Long id) {
        return users.get(id);
    }
}
