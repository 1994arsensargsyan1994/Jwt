package net.prosetly.jwtappaemo.repo;

import net.prosetly.jwtappaemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
