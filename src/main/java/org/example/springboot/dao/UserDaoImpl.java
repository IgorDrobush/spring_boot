package org.example.springboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.springboot.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {

    }

    @Override
    public void saveUser(User user) {
        System.out.println("Сработал saveUser()");
        entityManager.persist(user);
    }

    @Override
    public User findUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUserById(User user) {
        entityManager.remove(user);
    }

    @Override
    public void updateUser(User user, User userToUpdate) {
        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }
}
