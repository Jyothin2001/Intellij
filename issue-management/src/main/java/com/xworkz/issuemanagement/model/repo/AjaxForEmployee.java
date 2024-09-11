package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AjaxForEmployee  extends JpaRepository<EmployeeDTO, Long>
{
    @Query("SELECT e FROM EmployeeDTO e WHERE e.email = :email")
    Optional<EmployeeDTO> findByEmail(@Param("email") String email);

    @Query("SELECT e FROM EmployeeDTO e WHERE e.contactNumber = :contactNumber")
    Optional<EmployeeDTO> findByContactNumber(@Param("contactNumber") String contactNumber);
}
