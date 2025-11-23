package com.goylik.booking.domain.user.service.impl;

import com.goylik.booking.domain.user.dto.request.RegisterUserRequest;
import com.goylik.booking.domain.user.dto.response.UserResponse;
import com.goylik.booking.domain.user.exception.UserAlreadyExistsException;
import com.goylik.booking.domain.user.exception.UserNotFoundException;
import com.goylik.booking.domain.user.mapper.UserMapper;
import com.goylik.booking.domain.user.model.Role;
import com.goylik.booking.domain.user.model.User;
import com.goylik.booking.domain.user.repository.UserRepository;
import com.goylik.booking.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse register(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.email()))
            throw new UserAlreadyExistsException(request.email());

        var user = new User();
        user.setEmail(request.email());
        user.setFullName(request.fullName());
        user.setRole(Role.USER);
        user.setPasswordHash(passwordEncoder.encode(request.password()));

        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        var user = fetchUserById(id);
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getByEmail(String email) {
        var user = fetchUserByEmail(email);
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var user = fetchUserById(id);
        userRepository.delete(user);
    }

    private User fetchUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private User fetchUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
