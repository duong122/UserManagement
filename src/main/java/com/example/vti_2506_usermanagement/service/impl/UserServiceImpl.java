package com.example.vti_2506_usermanagement.service.impl;

import com.example.vti_2506_usermanagement.dto.UserDTO;
import com.example.vti_2506_usermanagement.dto.UserFilter;
import com.example.vti_2506_usermanagement.entity.User;
import com.example.vti_2506_usermanagement.exception.BusinessException;
import com.example.vti_2506_usermanagement.repository.UserRepository;
import com.example.vti_2506_usermanagement.service.UserService;
import com.example.vti_2506_usermanagement.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthday(userDTO.getBirthday());
        user.setAddress(userDTO.getAddress());

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        if(!userRepository.existsById(id)){
            throw new BusinessException("User with id " + id + " not exists");
        }
        User user = userRepository.findById(id).get();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthday(userDTO.getBirthday());
        user.setAddress(userDTO.getAddress());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException("User id is invalid");
        }
        if (!userRepository.existsById(id)) {
            throw new BusinessException("User with id " + id + " not exists");
        }
         userRepository.deleteById(id);
    }

    @Override
    public List<User> searchUser(UserFilter userFilter) {
        Specification<User> specification = buildSpecification(userFilter);
        return userRepository.findAll(specification);
    }

    public Specification<User> buildSpecification(UserFilter userFilter) {
        Specification<User> specification = Specification.where(null);
        if (userFilter.getFirstName() != null && !userFilter.getFirstName().isBlank()) {
            specification = specification.and(UserSpecification.hasFirstName(userFilter.getFirstName()));
        }
        if (userFilter.getLastName() != null && !userFilter.getLastName().isBlank()) {
            specification = specification.and(UserSpecification.hasLastName(userFilter.getLastName()));
        }
        if (userFilter.getAddress() != null && !userFilter.getAddress().isBlank()) {
            specification = specification.and(UserSpecification.hasAddress(userFilter.getAddress()));
        }
        return specification;
    }
}
