package com.goylik.booking.domain.user.mapper;

import com.goylik.booking.domain.user.dto.response.UserResponse;
import com.goylik.booking.domain.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
}
