package com.xworkz.issuemanagement.dto;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "signup")
@Data
@Slf4j
public @ToString class SignUpDTO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "FirstName cannot be empty")
    @Size(min = 3, max = 30, message = "First Name should contain letters between >2 and <30")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "First Name should contain only alphabetic letters")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "LastName cannot be empty")
    @Size(min = 1, max = 30, message = "LastName should be >1 and <4 letters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Last Name should contain only alphabetic letters")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Please enter valid email")
    @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "Enter valid email")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Please Enter Contact Number")
    @Min(1111111111)
    @Max(9999999999L)
    @Column(name = "contact_number")
    private Long contactNumber;

    @NotNull(message = "Please Enter Alternative Contact Number")
//    @Pattern(regexp = "^[0-9]{10}$", message = "Alternative Contact Number should be 10 digits")
    @Min(1111111111)
    @Max(9999999999L)
    @Column(name = "alternative_number")
    private Long alternateContactNumber;

    @NotEmpty(message = "Please enter Address")
    @Size(min = 5, max = 500, message = "Address should contain between 5 and 500 characters")
    @Column(name = "address")
    private String address;

    @Transient
    private String agree;




    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "is_active")
    private boolean isActive;




    @Column(name = "password")
    private String password;

    @Column(name = "account_locked")
    private boolean accountLocked=false;

    @Column(name="failed_attempted")
    private Integer failedAttempt=0;


    @Column(name="image_name")
    private String imageName;

    public SignUpDTO()
    {
        log.info("created SignUpDTO.. constructor");
    }

}

