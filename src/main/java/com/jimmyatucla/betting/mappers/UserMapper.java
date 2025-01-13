package com.jimmyatucla.betting.mappers;

import java.util.List;
import java.util.stream.Collectors;
import com.jimmyatucla.betting.dtos.UserDTO;
import com.jimmyatucla.betting.entities.User;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }

    public static List<UserDTO> toUserDTOList(List<User> users) {
        return users.stream()
                    .map(UserMapper::toUserDTO)
                    .collect(Collectors.toList());
    }
}
