package com.twt.registerverification.repository;

import com.twt.registerverification.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmailIgnoreCase(String email);
    Boolean existsByUserEmail(String email);
}
