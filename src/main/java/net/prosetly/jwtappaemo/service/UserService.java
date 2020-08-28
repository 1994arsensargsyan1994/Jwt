package net.prosetly.jwtappaemo.service;


import net.prosetly.jwtappaemo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String name);

    User findById(Long id);

    void delete(Long id);


    User addUser(User user);
}
