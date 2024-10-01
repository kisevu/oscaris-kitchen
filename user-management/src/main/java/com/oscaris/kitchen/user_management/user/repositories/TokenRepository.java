package com.oscaris.kitchen.user_management.user.repositories;
/*
*
@author ameda
@project user-management
*
*/

import com.oscaris.kitchen.user_management.user.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByToken(String token);
}
