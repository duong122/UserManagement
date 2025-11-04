package com.example.vti_2506_usermanagement.service;

import com.example.vti_2506_usermanagement.dto.GroupDTO;
import com.example.vti_2506_usermanagement.dto.GroupFilter;
import com.example.vti_2506_usermanagement.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups();
    public Group createGroup(GroupDTO groupDto);
    public Group updateGroup(Long id, GroupDTO groupDto);
    public Group deleteGroup(Long id);
    public Group getGroupById(Long id);
    public List<Group> searchGroup(GroupFilter groupFilter);
}
