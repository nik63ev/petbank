package ru.bank.petbank.dao;

import ru.bank.petbank.model.UserInfo;

public interface UserDao {
    UserInfo getByID(Long id);
    // save
}
