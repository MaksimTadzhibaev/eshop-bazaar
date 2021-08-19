package ru.tadzh.service;

import org.springframework.data.domain.Page;
import ru.tadzh.controller.UserDto;
import ru.tadzh.controller.UserListParams;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    Page<UserDto> findWithFilter(UserListParams userListParams);

    Optional<UserDto> findById(Long id);

    void save(UserDto user);

    void deleteById(Long id);
}
