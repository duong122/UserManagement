package com.example.vti_2506_usermanagement.repository;

import com.example.vti_2506_usermanagement.dto.GroupFilter;
import com.example.vti_2506_usermanagement.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>, JpaSpecificationExecutor<Group> {
    Group deleteGroupById(Long id);

    @Query(value = "select g.* from `groups` g join user_group_mapping ugm on g.id = ugm.group_id where ugm.user_id =:id;", nativeQuery = true)
    Page<Group> getGroupsByUserId(Long id, Pageable pageable);

    Group getGroupById(Integer groupId);
}
