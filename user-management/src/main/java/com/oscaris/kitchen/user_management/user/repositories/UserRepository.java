package com.oscaris.kitchen.user_management.user.repositories;/*
*
@author ameda
@project user-management
*
*/

import com.oscaris.kitchen.user_management.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
