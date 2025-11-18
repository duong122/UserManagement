package com.example.vti_2506_usermanagement.service;

import com.example.vti_2506_usermanagement.dto.RequestAlterUserInGroupDTO;

import java.util.List;

public interface UserGroupMappingService {
    void alterUsersInGroup(RequestAlterUserInGroupDTO dto);
}
