package com.example.vti_2506_usermanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserGroupMappingDTO {
    @NotNull(message = "Group id không được để trống")
    @Min(value = 1)
    private Integer groupId;

    @NotNull(message = "User id không được để trống")
    @Min(value = 1)
    private Integer userId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserGroupMappingDTO that = (UserGroupMappingDTO) o;
        return Objects.equals(groupId, that.groupId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, userId);
    }
}
