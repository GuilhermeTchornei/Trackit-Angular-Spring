package com.trackit.api.user.service;

import java.util.NoSuchElementException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.trackit.api.user.dto.UserDto;
import com.trackit.api.user.dto.mapper.UserMapper;
import com.trackit.api.user.model.Users;
import com.trackit.api.user.repository.UserRepository;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public void create(UserDto userDto) {
        Users user = UserMapper.INSTANCE.userDtoToUsers(userDto);
        user.setPassword(encryptPassword(user.getPassword()));
        repository.save(user);
    }

    public UserDto getUser(@NotNull @Positive Long id) {
        return repository.findById(id).map(UserMapper.INSTANCE::usersToUserDto)
                .orElseThrow(NoSuchElementException::new);
    }

    public String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
