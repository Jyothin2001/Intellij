package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.RegDeptAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AjaxEmailAndNumberRepo
{
    //Ajax for user Email
    public SignUpDTO existsByEmail(String email);

    //Ajax for user number
    public SignUpDTO existsByNumber(Long contactNumber);


    //Ajax for sub admin Email
    public RegDeptAdminDTO existsBySubAdminEmail(String email);

    //Ajax for sub admin number
    public RegDeptAdminDTO existsBySubAdminNumber(Long contactNumber);


    // Find by email for email validation
    Optional<EmployeeDTO>  findByEmail(String email);

    // Find by contact number for contact number validation
    Optional<EmployeeDTO> findByContactNumber(Long contactNumber);


}
