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
        private Dealer dealer; // Many users belong to one dealer

        public Users(String login, String password, UserRole role){
            this.login = login;
            this.password = password;
            this.role = role;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
            else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

        @Override
        public String getPassword() {
            String o = null;
            return o;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
}

