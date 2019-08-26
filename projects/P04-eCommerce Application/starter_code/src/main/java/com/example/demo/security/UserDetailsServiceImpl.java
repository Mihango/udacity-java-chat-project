package com.example.demo.security;

import com.example.demo.model.persistence.EcommerceUser;
import com.example.demo.model.persistence.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EcommerceUser user = userRepository.findByUsername(username);

        if (user == null) {
            log.error("User called >>>>>>>>>> null");
            throw new UsernameNotFoundException(username);
        }
        log.error("User called >>>>>>>>>> " + user.getUsername() + " >>>>>> "+ user.getPassword());
        return new User(user.getUsername(), user.getPassword(), getAuthorities());
    }

    private List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        log.error("authorities called >>>>>>>>>> :" + authorities);
        return authorities;
    }
}
