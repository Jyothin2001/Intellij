package com.xworkz.issuemanagement.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Slf4j
@Data
@Table(name="admin_table")
public class AdminDTO {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "username", nullable = false, unique = true)
        private String username;

        @Column(name = "password", nullable = false)
        private String password;

        @Column(name = "email", nullable = false, unique = true)
        private String email;


}
