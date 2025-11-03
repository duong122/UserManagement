package com.example.vti_2506_usermanagement.specification;

import com.example.vti_2506_usermanagement.entity.Group;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class GroupSpecification {
    public static Specification<Group> hasGroupName(String groupName) {
        return (root, query, builder)
                -> builder.like(builder.lower(root.get("groupName")),  "%" + groupName.toLowerCase() + "%");
    }

    public static Specification<Group> hasGroupPolicy(String groupPolicy) {
        return (root, query, creteriaBuilder)
                -> creteriaBuilder.like(creteriaBuilder.lower(root.get("groupPolicy")), "%" + groupPolicy.toLowerCase() + "%");
    }

    public static Specification<Group> hasEstablishDay(LocalDate establishDay) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("establishDay"), establishDay);
    }
}
