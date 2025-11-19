package com.goylik.booking.domain.user.service;

import com.goylik.booking.domain.user.dto.request.RegisterUserRequest;
import com.goylik.booking.domain.user.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse register(RegisterUserRequest request);
    UserResponse getById(Long id);
    UserResponse getByEmail(String email);
    List<UserResponse> getAll();
    void delete(Long id);
}
