package net.prosetly.jwtappaemo.service.impl;


import lombok.extern.slf4j.Slf4j;
import net.prosetly.jwtappaemo.model.Role;
import net.prosetly.jwtappaemo.model.Status;
import net.prosetly.jwtappaemo.model.User;
import net.prosetly.jwtappaemo.repo.RoleRepo;
import net.prosetly.jwtappaemo.repo.UserRepo;
import net.prosetly.jwtappaemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {

        Role role = roleRepo.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        User userRegister = userRepo.save(user);
        log.info("In register - User: {} successfully registered", userRegister);
        return userRegister;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepo.findAll();
        log.info("in GetAll - {} users found", users);
        return users;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepo.findByUsername(username);
        log.info("In findByUsername - {} user found by username: {} ", user, username);
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            log.warn("In findById user not found  by id : {}", id);
            return null;
        }
        log.info("In findById - {} user found by id: {} ", user, id);
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
        log.info("In delete - User: successfully deleted ");


    }

    @Override
    public User addUser(User user) {
        user.setStatus(Status.ACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
}
