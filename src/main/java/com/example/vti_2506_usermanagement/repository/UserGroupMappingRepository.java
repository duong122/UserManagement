package com.example.vti_2506_usermanagement.repository;

import com.example.vti_2506_usermanagement.entity.UserGroupMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupMappingRepository extends JpaRepository<UserGroupMapping, Integer>, JpaSpecificationExecutor<UserGroupMapping> {

    @Query(value = "select ugp.* from users u join user_group_mapping ugp on ugp.user_id = u.id where ugp.group_id =:groupId", nativeQuery = true)
    List<UserGroupMapping> getAllUserByGroupId(Integer groupId);

}
