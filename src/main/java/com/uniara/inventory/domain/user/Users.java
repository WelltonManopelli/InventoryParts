package com.uniara.inventory.domain.user;

import com.uniara.inventory.domain.dealer.Dealer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;



    @Entity
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(of = "id")
    public class Users implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;
        private String login;
        private String password;
        private UserRole role;
        @ManyToOne
        @JoinColumn(name = "dealer_cod")
        private Dealer dealer;


        public Users (Register register, String encryptedPassword, Dealer dealer){
            this.login = register.login();
            this.password = encryptedPassword;
            this.role = register.role();
            this.dealer = dealer;

        }


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
            else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }



        @Override
        public String getUsername() {
            return login;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }



}

