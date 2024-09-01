package com.xworkz.issuemanagement.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
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
    @NotBlank(message = "Contact number is required.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits.")
    private String contactNumber;

    @Column(name = "employee_address")
    @NotBlank(message = "Address is required.")
    @Length(min = 5, max = 100, message = "Address must be between 5 and 50 characters.")
    private String address;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="dept_id",referencedColumnName = "department_id")
    private DepartmentDTO departmentDTO;

    @Column(name = "employee_status")
    private String status;








}
