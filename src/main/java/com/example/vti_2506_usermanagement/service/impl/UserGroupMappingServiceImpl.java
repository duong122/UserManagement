package com.example.vti_2506_usermanagement.service.impl;

import com.example.vti_2506_usermanagement.dto.RequestAlterUserInGroupDTO;
import com.example.vti_2506_usermanagement.entity.UserGroupMapping;
import com.example.vti_2506_usermanagement.exception.BusinessException;
import com.example.vti_2506_usermanagement.repository.GroupRepository;
import com.example.vti_2506_usermanagement.repository.UserGroupMappingRepository;
import com.example.vti_2506_usermanagement.repository.UserRepository;
import com.example.vti_2506_usermanagement.service.UserGroupMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserGroupMappingServiceImpl implements UserGroupMappingService {
    private final UserGroupMappingRepository userGroupMappingRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Override
    public void alterUsersInGroup(RequestAlterUserInGroupDTO dto) {
        Integer groupId = dto.getGroupId();
        List<Integer> userNew = dto.getUserIds();

        // Validate groupId có tồn tại hay không, và tất cả các userId đều phải tồn tại trong hệ thống
        if (groupRepository.getGroupById(groupId) == null) {
            throw new BusinessException("Group không tồn tại");
        }
        Integer numberUserExistInUserNew = userRepository.getUsersByIdIsIn(userNew).toArray().length;
        if (userNew.size() != numberUserExistInUserNew) {
            throw new BusinessException("Trong các user có một vài user không tồn tại");
        }

        List<Integer> userExisted = userGroupMappingRepository.getUserIdByGroupId(groupId);

        // Lấy ra tất cả các user cần xóa đi là các user chỉ nằm trong userExisted mà không nằm trong userNew
        Set<Integer> userNewSet = new HashSet<>(userNew);
        List<Integer> userToDelete = userExisted.stream().filter(e -> !userNewSet.contains(e)).toList();
        if(!userToDelete.isEmpty()) {
            userGroupMappingRepository.deleteAllByUserIdAndGroupId(groupId, userToDelete);
        }

        // Lấy ra tất cả các user cần thêm vào là các user chỉ tồn tại ở userNew mà không tồn tại ở userExisted
        Set<Integer> userExistedSet = new HashSet<>(userExisted);
        List<Integer> userToAdd = userNew.stream().filter(e -> !userExistedSet.contains(e)).toList();
        List<UserGroupMapping> userEntityToAdd = new ArrayList<>();
        for (Integer value : userToAdd) {
            UserGroupMapping ugm = new UserGroupMapping();
            ugm.setGroupId(groupId);
            ugm.setUserId(value);
            userEntityToAdd.add(ugm);
        }
        if(!userEntityToAdd.isEmpty()) {
            userGroupMappingRepository.saveAll(userEntityToAdd);
        }

        // Lấy ra tất cả các user cần upadte là các user tồn tại ở cả userNew và userExisted
        List<Integer> userToUpdate = new ArrayList<>(userNew);
        userToUpdate.addAll(userExisted);
        Set<Integer> userToUpdateSet = new HashSet<>(userToUpdate);
        userToUpdate = userToUpdateSet.stream().filter(e -> userNew.contains(e) && userExisted.contains(e)).toList();
        List<UserGroupMapping> userToUpdateEntity = new ArrayList<>();
        for (Integer value : userToUpdate) {
            UserGroupMapping ugm = userGroupMappingRepository.getByGroupIdAndUserId(groupId, value);
            ugm.setUserId(value);
            ugm.setGroupId(groupId);
            // ... set một vài thuộc tính ở đây nữa nếu sau này tồn tại
            userToUpdateEntity.add(ugm);
        }
        userGroupMappingRepository.saveAll(userToUpdateEntity);
    }


}
