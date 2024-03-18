package com.spring.mmm.domain.users.service;

import com.spring.mmm.domain.users.domain.User;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import com.spring.mmm.domain.users.infra.UserDetailsImpl;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = findByEmail(email);
        return new UserDetailsImpl(user, user.getEmail());
    }

    private UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                ()->new UserException(UserErrorCode.EMAIL_NOT_FOUND)
        );
    }

}
