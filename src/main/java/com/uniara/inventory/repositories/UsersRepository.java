package com.uniara.inventory.repositories;

import com.uniara.inventory.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository <Users, Long >{
    Users findByUsername(String username);
    UserDetails findByLogin(String login);
}