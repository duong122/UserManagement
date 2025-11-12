package com.example.vti_2506_usermanagement.repository;

import com.example.vti_2506_usermanagement.entity.User;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    List<User> getUsersByFirstName(String firstName);

    List<User> findByBirthdayBefore(LocalDate birthdayBefore);

    @Query("SELECT u FROM User u WHERE ?1 < u.birthday")
    List<User> findByBirthdayBefore2(LocalDate birthdayBefore);

    @Query(value = "SELECT * FROM users u WHERE u.birth_day < :birthDayParam", nativeQuery = true)
    List<User> findByBirthdayBefore3(@Param("birthDayParam") LocalDate birthDayBefore);

    User getUserByUsername(String username);
}
