package com.example.vti_2506_usermanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "`groups`")
@Getter
@Setter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_policy")
    private String groupPolicy;

    @Column(name = "establish_day")
    private LocalDate establishDay;
}
