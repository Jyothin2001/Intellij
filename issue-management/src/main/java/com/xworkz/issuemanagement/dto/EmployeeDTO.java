package com.xworkz.issuemanagement.dto;

import com.xworkz.issuemanagement.constants.Status;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Slf4j
@Data
@Table(name="employee_details")
public class EmployeeDTO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;

    @Column(name = "employee_name")
    @NotBlank(message = "Employee name is required.")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Name should only contain letters and spaces.")
    private String employeeName;

    @Column(name = "employee_designation")
    @NotBlank(message = "Employee designation is required.")
    private String employeeDesignation;

    @Column(name = "employee_dept_type")
    private String departmentName;

    @Column(name = "employee_email")
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    @Column(name = "employee_contact_number")
    @NotNull(message = "Contact number cannot be null")
    @Min(value = 1000000000L, message = "Contact number should be at least 10 digits")
    @Max(value = 9999999999L, message = "Contact number should be at most 10 digits")
    private Long contactNumber;


//    @NotBlank(message = "Contact number is required.")
//    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits.")
//    private Long contactNumber;

    @Column(name = "employee_address")
    @NotBlank(message = "Address is required.")
    @Length(min = 5, max = 100, message = "Address must be between 5 and 50 characters.")
    private String address;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="dept_id",nullable = false,referencedColumnName = "department_id")
    private DepartmentDTO departmentDTO;

    @Column(name = "employee_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    private Long otp;









}
