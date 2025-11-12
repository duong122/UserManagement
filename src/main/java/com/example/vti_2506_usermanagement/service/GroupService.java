package com.example.vti_2506_usermanagement.service;

import com.example.vti_2506_usermanagement.dto.GroupDTO;
import com.example.vti_2506_usermanagement.dto.GroupFilter;
import com.example.vti_2506_usermanagement.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupService {
    Page<Group> getAllGroups(Pageable pageable);
    Group createGroup(GroupDTO groupDto);
    Group updateGroup(Long id, GroupDTO groupDto);
    Group deleteGroup(Long id);
    Group getGroupById(Long id);
    Page<Group> searchGroup(GroupFilter groupFilter, Pageable pageable);
    Page<Group> getGroupsByUserId(Long id, Pageable pageable);
}
