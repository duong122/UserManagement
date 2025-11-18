package com.example.vti_2506_usermanagement.service.impl;

import com.example.vti_2506_usermanagement.dto.GroupDTO;
import com.example.vti_2506_usermanagement.dto.GroupFilter;
import com.example.vti_2506_usermanagement.entity.Group;
import com.example.vti_2506_usermanagement.exception.BusinessException;
import com.example.vti_2506_usermanagement.repository.GroupRepository;
import com.example.vti_2506_usermanagement.service.GroupService;
import com.example.vti_2506_usermanagement.specification.GroupSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<Group> getAllGroups(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Group createGroup(GroupDTO groupDto) {
        Group group = modelMapper.map(groupDto, Group.class);
        return groupRepository.save(group);
    }

    @Transactional
    @Override
    public Group updateGroup(Long id, GroupDTO groupDto) {
        if (groupRepository.findById(id).isEmpty()) {
            throw new BusinessException("Group not exists");
        }
        Group group = groupRepository.findById(id).get();
        group.setGroupName(groupDto.getGroupName());
        group.setGroupPolicy(groupDto.getGroupPolicy());
        group.setEstablishDay(groupDto.getEstablishDay());


        return groupRepository.save(group);
    }

    @Transactional
    @Override
    public Group deleteGroup(Long id) {
        if (groupRepository.findById(id).isEmpty()) {
            throw new BusinessException("Group not exists");
        }
        return groupRepository.deleteGroupById(id);
    }

    @Override
    public Group getGroupById(Long id) {
        if (groupRepository.findById(id).isEmpty()) {
            throw new BusinessException("Group not exists");
        }
        return groupRepository.findById(id).get();
    }

    @Override
    public Page<Group> searchGroup(GroupFilter groupFilter, Pageable pageable) {
        Specification<Group> specification = getSpecification(groupFilter);
        return groupRepository.findAll(specification, pageable);
    }

    @Override
    public Page<Group> getGroupsByUserId(Long id, Pageable pageable) {
        return groupRepository.getGroupsByUserId(id, pageable);
    }


    public Specification<Group> getSpecification(GroupFilter groupFilter) {
        Specification<Group> specification = Specification.where(null);


        if (groupFilter.getGroupName() != null && !groupFilter.getGroupName().isEmpty()) {
            specification = specification.and(GroupSpecification.hasGroupName(groupFilter.getGroupName()));
        }
        if (groupFilter.getGroupPolicy() != null && !groupFilter.getGroupPolicy().isEmpty()) {
            specification = specification.and(GroupSpecification.hasGroupPolicy(groupFilter.getGroupPolicy()));
        }
        if (groupFilter.getEstablishDay() != null) {
            specification = specification.and(GroupSpecification.hasEstablishDay(groupFilter.getEstablishDay()));
        }

        return specification;
    }
}
