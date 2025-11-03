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

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group createGroup(GroupDTO groupDto) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(groupDto.getEstablishDay(), dateTimeFormatter);
        Group group = new  Group();
        group.setGroupName(groupDto.getGroupName());
        group.setGroupPolicy(groupDto.getGroupPolicy());
        group.setEstablishDay(LocalDate.parse(groupDto.getEstablishDay(), dateTimeFormatter));;

        return groupRepository.save(group);
    }

    @Override
    public Group updateGroup(Long id, GroupDTO groupDto) {
        if (id == null || id <= 0) {
            throw new BusinessException("Id can not be null or smaller than zero");
        }
        if (groupRepository.findById(id).isEmpty()) {
            throw new BusinessException("Group not exists");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(groupDto.getEstablishDay(), dateTimeFormatter);
        Group group = groupRepository.findById(id).get();
        group.setGroupName(groupDto.getGroupName());
        group.setGroupPolicy(groupDto.getGroupPolicy());
        group.setEstablishDay(LocalDate.parse(groupDto.getEstablishDay(), dateTimeFormatter));

        return groupRepository.save(group);
    }

    @Transactional
    @Override
    public Group deleteGroup(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException("Id can not be null or smaller than zero");
        }
        if (groupRepository.findById(id).isEmpty()) {
            throw new BusinessException("Group not exists");
        }
        return groupRepository.deleteGroupById(id);
    }

    @Override
    public Group getGroupById(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException("Id can not be null or smaller than zero");
        }
        if (groupRepository.findById(id).isEmpty()) {
            throw new BusinessException("Group not exists");
        }
        return groupRepository.findById(id).get();
    }

    @Override
    public List<Group> getGroupByGroupName(GroupFilter groupFilter) {
        Specification<Group> specification = getSpecification(groupFilter);
        return groupRepository.findAll(specification);
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
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(groupFilter.getEstablishDay(), dateTimeFormatter);
            specification = specification.and(GroupSpecification.hasEstablishDay(date));
        }

        return specification;

    }

}
