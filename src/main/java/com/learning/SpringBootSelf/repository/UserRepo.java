package com.learning.SpringBootSelf.repository;

import com.learning.SpringBootSelf.entaties.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    public User findByUserName(String userName);

    public void deleteByUserName(String userName);
}
