package com.example.vti_2506_usermanagement.service.impl;

import com.example.vti_2506_usermanagement.dto.UpdateUserDTO;
import com.example.vti_2506_usermanagement.dto.UserDTO;
import com.example.vti_2506_usermanagement.dto.UserFilter;
import com.example.vti_2506_usermanagement.entity.User;
import com.example.vti_2506_usermanagement.exception.BusinessException;
import com.example.vti_2506_usermanagement.repository.UserRepository;
import com.example.vti_2506_usermanagement.service.UserService;
import com.example.vti_2506_usermanagement.specification.UserSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public User createUser(UserDTO userDTO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User updateUser(Long id, UpdateUserDTO userDTO) {
        if(!userRepository.existsById(id)){
            throw new BusinessException("User with id " + id + " not exists");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findById(id).get();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthday(userDTO.getBirthday());
        user.setAddress(userDTO.getAddress());
        user.setRoleName(userDTO.getRoleName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new BusinessException("User with id " + id + " not exists");
        }
         userRepository.deleteById(id);
    }

    @Override
    public List<User> findbyAgeMoreThan(String age) {
        if (age == null || age.isEmpty() || Integer.parseInt(age) < 0) {
            throw new BusinessException("Age is invalid");
        }
        LocalDate localdate = LocalDate.of(LocalDate.now().minusYears(Integer.parseInt(age)).getYear(), 1, 1);
        return userRepository.findByBirthdayBefore(localdate);
    }

    @Override
    public List<User> finbyAgeMoreThan2(String age) {
        if (age == null || age.isEmpty() || Integer.parseInt(age) < 0) {
            throw new BusinessException("Age is invalid");
        }
        LocalDate localdate = LocalDate.of(LocalDate.now().minusYears(Integer.parseInt(age)).getYear(), 1, 1);
        return userRepository.findByBirthdayBefore2(localdate);
    }

    @Override
    public List<User> finbyAgeMoreThan3(String age) {
        if (age == null || age.isEmpty() || Integer.parseInt(age) < 0) {
            throw new BusinessException("Age is invalid");
        }
        LocalDate localdate = LocalDate.of(LocalDate.now().minusYears(Integer.parseInt(age)).getYear(), 1, 1);
        return userRepository.findByBirthdayBefore3(localdate);
    }

    @Override
    public Page<User> searchUser(UserFilter userFilter, Pageable pageable) {
        Specification<User> specification = buildSpecification(userFilter);
        return userRepository.findAll(specification, pageable);
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
        if (userFilter.getRoleName() != null && !userFilter.getRoleName().isBlank()) {
            specification = specification.and(UserSpecification.hasRoleName(userFilter.getRoleName()));
        }
        return specification;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null || username.isBlank()) {
            throw new BusinessException("Username không được để trống");
        }
        User user = userRepository.getUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Không tìm thấy user ");
        }
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + userRepository.getRoleByUsername(username).toUpperCase()));
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + userRepository.getRoleByUsername(username).toUpperCase())));
    }
}
