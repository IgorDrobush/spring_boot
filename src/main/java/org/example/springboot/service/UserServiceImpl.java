package org.example.springboot.service;

import org.example.springboot.dao.UserDao;
import org.example.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User findUserById(long id) {
        return userDao.findUserById(id);
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        User user = userDao.findUserById(id);
        if (user != null) {
            userDao.deleteUser(user);
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        User userToUpdate = userDao.findUserById(user.getId());
        userDao.updateUser(user, userToUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
