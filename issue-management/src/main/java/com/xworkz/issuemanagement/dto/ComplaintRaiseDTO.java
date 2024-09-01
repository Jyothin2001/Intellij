package com.xworkz.issuemanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Data
@Slf4j
@ToString
@Table(name = "complaint_raise")
public class ComplaintRaiseDTO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="complaint_id")
    private int complaintId;

    @Column(name="complaint_type")
    private String complaintType;

    private String country;

    private String state;

    private String city;

    private String area;

    private String address;

    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_fk", referencedColumnName = "id")
    //@JoinColumn(name = "user_id", nullable = false)
    private SignUpDTO signUpDTO;// Many complaint Raise can be associated with one user


    //its coming from department table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_fk", referencedColumnName = "department_id")
    private DepartmentDTO departmentDTO;

    @Column(name = "status")
    private String status;

    //@Data= getter,setter,toString,equals(),hashcode()
   // @RequiredArgsConstructor: Generates a constructor with required arguments (final fields and fields marked as @NonNull).
}
