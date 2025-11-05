package com.example.vti_2506_usermanagement.service;

import com.example.vti_2506_usermanagement.dto.GroupDTO;
import com.example.vti_2506_usermanagement.dto.GroupFilter;
import com.example.vti_2506_usermanagement.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupService {
    public Page<Group> getAllGroups(Pageable pageable);
    public Group createGroup(GroupDTO groupDto);
    public Group updateGroup(Long id, GroupDTO groupDto);
    public Group deleteGroup(Long id);
    public Group getGroupById(Long id);
    public Page<Group> searchGroup(GroupFilter groupFilter, Pageable pageable);
}
