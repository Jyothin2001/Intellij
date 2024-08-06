package com.xworkz.issuemanagement.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Slf4j
@Table(name="department_admin_table")
public class RegDeptAdminDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_adminId")
    private int id;


    @NotEmpty(message = "AdminName cannot be empty")
    @Size(min = 3,max = 30,message = "AdminName should contain letters between > 2 and  <30")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "AdminName should contain only alphabetic letters")
    @Column(name = "admin_name")
    private String adminName;


    @NotEmpty(message = "Please select departmentName")
    @Column(name = "department_name")
    private String departmentName;


    @NotEmpty(message = "Please enter valid email")
    @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "Enter valid email")
    @Column(name = "email")
    private String email;


    @NotNull(message = "Please Enter contact number")
    @Min(1111111111)
    @Max(9999999999L)
    @Column(name = "contact_number")
    private Long contactNumber;

    @NotNull(message = "Please Enter Alternative contact number")
    @Min(1111111111)
    @Max(9999999999L)
    @Column(name = "alt_contactNo")
    private Long alternateContactNumber;

    @Column(name="password")
    private String password;


}
