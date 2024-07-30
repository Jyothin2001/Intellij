package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepo  {

    //admin login
    AdminDTO findByEmailAndPassword(String email, String password);

    //fetch userDetails by SignUpId
    List<SignUpDTO> findByUserId(SignUpDTO signUpDTO);

    //fetch ComplaintRaiseDetails by complaintId
    List<ComplaintRaiseDTO> findByComplaintRaiseId(ComplaintRaiseDTO complaintRaiseDTO);

    //search by complaint type and city "or"
     List<ComplaintRaiseDTO> searchByComplaintTypeAndCity(String complaintType, String city);


    //search by complaintType and city "and"
     List<ComplaintRaiseDTO> searchByComplaintTypeOrCity(String complaintType, String city);

     //fetch city names for dropdown
     List<ComplaintRaiseDTO> findAllCities();

    //fetch adminName to display in navBar
    String getAdminName(String email,String password);

    //save department
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

//find all departmentName for displaying in ViewComplaintRaise.jsp
List<DepartmentDTO> findByDepartmentName(String  departmentName);

//update status and department id
 boolean updateStatusAndDepartmentId(int complaintId, int departmentId, String status);


}

