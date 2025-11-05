package com.example.vti_2506_usermanagement.controller;

import com.example.vti_2506_usermanagement.dto.GroupDTO;
import com.example.vti_2506_usermanagement.dto.GroupFilter;
import com.example.vti_2506_usermanagement.entity.Group;
import com.example.vti_2506_usermanagement.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public Page<Group> getAllGroups(Pageable pageable) {
        return groupService.getAllGroups(pageable);
    }

    @PostMapping
    public Group createGroup(@Valid @RequestBody GroupDTO groupDto) {
        return groupService.createGroup(groupDto);
    }

    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable Long id,@Valid @RequestBody GroupDTO groupDto) {
        return groupService.updateGroup(id, groupDto);
    }

    @DeleteMapping("/{id}")
    public Group deleteGroup(@PathVariable Long id) {
        return groupService.deleteGroup(id);
    }

    @GetMapping("/id/{id}")
    public Group getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @GetMapping("/search")
    public Page<Group> searchGroup(GroupFilter groupFilter, Pageable pageable) {
        return groupService.searchGroup(groupFilter, pageable);
    }
}
