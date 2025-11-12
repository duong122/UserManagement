package com.example.vti_2506_usermanagement.service.impl;

import com.example.vti_2506_usermanagement.dto.UserGroupMappingDTO;
import com.example.vti_2506_usermanagement.entity.UserGroupMapping;
import com.example.vti_2506_usermanagement.repository.UserGroupMappingRepository;
import com.example.vti_2506_usermanagement.service.UserGroupMappingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserGroupMappingServiceImpl implements UserGroupMappingService {
    private final UserGroupMappingRepository userGroupMappingRepository;

    @Transactional
    @Override
    public Void addUserToGroup(List<UserGroupMappingDTO> userGroupMappingDTOList) {
        Integer groupId = userGroupMappingDTOList.getFirst().getGroupId();
        List<UserGroupMapping> userGroupMappingInDb = userGroupMappingRepository.getAllUserByGroupId(groupId);

        List<UserGroupMappingDTO> usersNew = userGroupMappingDTOList;
        List<UserGroupMappingDTO> usersOld = userGroupMappingInDb.stream()
                .map(dto -> {
                    UserGroupMappingDTO entity = new UserGroupMappingDTO();
                    entity.setGroupId(dto.getGroupId());
                    entity.setUserId(dto.getUserId());
                    return entity;
                })
                .collect(Collectors.toList());


        // Lấy ra tất cả các user cân xóa là các phần tử tồn tại ở userOld mà không tồn tại ở userNew
        Set<UserGroupMappingDTO> usersNewSet = new HashSet<>(usersNew);
        List<UserGroupMappingDTO> userTodDelete = usersOld.stream()
                                                    .filter(e -> !usersNewSet.contains(e))
                                                    .toList();

        List<Integer> userGroupMappingIdToDelete = new ArrayList<>();
        for(UserGroupMappingDTO u : userTodDelete) {
            for(UserGroupMapping ugm : userGroupMappingInDb) {
                if (u.getUserId().equals(ugm.getUserId()) && u.getGroupId().equals(ugm.getGroupId())) {
                    userGroupMappingIdToDelete.add(ugm.getId());
                }
            }
        }
        userGroupMappingRepository.deleteAllById(userGroupMappingIdToDelete);

        // Lấy ra tất cả các phần tử cần thêm vào là các phần tử tồn tại ở userNew mà không tồn tại ở userOld
        Set<UserGroupMappingDTO> usersOldSet = new HashSet<>(usersOld);
        List<UserGroupMappingDTO> userToAdd = usersNew.stream()
                                                .filter(e -> !usersOldSet.contains(e))
                                                    .toList();
        List<UserGroupMapping> userGroupMappingToAddList = userToAdd.stream()
                .map(dto -> {
                    UserGroupMapping entity = new UserGroupMapping();
                    entity.setGroupId(dto.getGroupId());
                    entity.setUserId(dto.getUserId());
                    return entity;
                })
                .collect(Collectors.toList());

        userGroupMappingRepository.saveAll(userGroupMappingToAddList);

        // Các phần tử cần update là các phần tử tồn tại ở cả userNew và userOld
        List<UserGroupMappingDTO> userToUpdate = usersOld; userToUpdate.addAll(usersNew);
        userToUpdate = userToUpdate.stream()
                        .filter(e -> usersNewSet.contains(e) && usersOldSet.contains(e))
                        .toList();
        List<Integer> userToUpdateInt = new ArrayList<>();
        for (UserGroupMappingDTO u : userToUpdate) {
            for (UserGroupMapping ugm : userGroupMappingInDb) {
                if (u.getUserId().equals(ugm.getUserId()) && u.getGroupId().equals(ugm.getGroupId())) {
                    userToUpdateInt.add(ugm.getId());

                }
            }
        }


        return null;
    }
}
