package UserDetails.UserDetails.services;

import UserDetails.UserDetails.entity.User;

import java.util.Optional;

public interface UserService {

    public User insertOrUpdate(User user);

    public boolean existsById(String id);

    boolean login(String email,String password);
    boolean signup(User user);

    public User addUser(User user);
    public Optional<User> findByUserId(String userId);

    public Optional<User> findByUserEmail(String userEmail);


    }
