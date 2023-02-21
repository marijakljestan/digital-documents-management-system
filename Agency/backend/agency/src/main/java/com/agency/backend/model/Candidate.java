package com.agency.backend.model;

import javax.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Candidate extends BaseEntity{

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String degree;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Transient
    private Address address;

    @Column(nullable = true)
    private String cv;

    @Column(nullable = true)
    private String coverLetter;
}
