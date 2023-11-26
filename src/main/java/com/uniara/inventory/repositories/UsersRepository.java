package com.uniara.inventory.repositories;

import com.uniara.inventory.domain.dealer.Dealer;
import com.uniara.inventory.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository <Users, String >{

    UserDetails findByLogin(String login);

    @Query("SELECT u.dealer FROM Users u WHERE u.login = :login")
    Dealer findDealerByLogin(@Param("login") String login);

}
