package com.ecommerce.user.service.impl;

import com.ecommerce.user.dto.UserDto;
import com.ecommerce.user.enums.ResponseCodes;
import com.ecommerce.user.exception.BusinessException;
import com.ecommerce.user.exception.ENFException;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new BusinessException("user already present with this email id");
        }
        User user = modelMapper.map(userDto, User.class);
        user.setId(UUID.randomUUID());
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ENFException(User.class,"id",id, ResponseCodes.EVENT_NOT_FOUND));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
