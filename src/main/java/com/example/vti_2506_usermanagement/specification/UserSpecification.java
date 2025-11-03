package com.example.vti_2506_usermanagement.specification;

import com.example.vti_2506_usermanagement.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static  Specification<User> hasFirstName(String firstName) {
        return (root, query, builder)
                    -> builder.like(builder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
    }

    public static Specification<User> hasLastName(String lastName) {
        return (root, query, builder)
                    -> builder.like(builder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    public static Specification<User> hasAddress(String address) {
        return (root, query, builder)
                -> builder.like(builder.lower(root.get("address")), "%" + address.toLowerCase() + "%");
    }

}
