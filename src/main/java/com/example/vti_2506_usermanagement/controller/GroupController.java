package com.example.vti_2506_usermanagement.controller;

import com.example.vti_2506_usermanagement.common.BaseResponse;
import com.example.vti_2506_usermanagement.dto.GroupDTO;
import com.example.vti_2506_usermanagement.dto.GroupFilter;
import com.example.vti_2506_usermanagement.entity.Group;
import com.example.vti_2506_usermanagement.entity.User;
import com.example.vti_2506_usermanagement.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<BaseResponse<Page<Group>>> getAllGroups(Pageable pageable) {
        return ResponseEntity.ok(new BaseResponse<>(groupService.getAllGroups(pageable), "Get groups successfully"));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Group>> createGroup(@Valid @RequestBody GroupDTO groupDto) {
        return ResponseEntity.ok(new BaseResponse<>(groupService.createGroup(groupDto), "Create group successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Group>> updateGroup(@PathVariable Long id,@Valid @RequestBody GroupDTO groupDto) {
        return ResponseEntity.ok(new BaseResponse<>(groupService.updateGroup(id, groupDto), "Update group successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Group>> deleteGroup(@PathVariable Long id) {
        return ResponseEntity.ok(new BaseResponse<>(groupService.deleteGroup(id), "Delete group successfully"));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BaseResponse<Group>> getGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(new BaseResponse<>(groupService.getGroupById(id), "Get group successfully"));
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<Group>>> searchGroup(GroupFilter groupFilter, Pageable pageable) {
        return ResponseEntity.ok(new BaseResponse<>(groupService.searchGroup(groupFilter, pageable), "Get group successfully"));
    }
}
