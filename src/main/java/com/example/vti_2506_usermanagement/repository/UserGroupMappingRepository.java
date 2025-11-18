package com.example.vti_2506_usermanagement.repository;

import com.example.vti_2506_usermanagement.entity.UserGroupMapping;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupMappingRepository extends JpaRepository<UserGroupMapping, Integer>, JpaSpecificationExecutor<UserGroupMapping> {
    @Query("select u.userId from UserGroupMapping u where u.groupId = :groupId")
    List<Integer> getUserIdByGroupId(Integer groupId);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserGroupMapping ugm WHERE ugm.groupId = :groupId and ugm.userId IN :userIds")
    void deleteAllByUserIdAndGroupId(Integer groupId, List<Integer> userIds);

    UserGroupMapping getByGroupIdAndUserId(Integer groupId, Integer value);
}
