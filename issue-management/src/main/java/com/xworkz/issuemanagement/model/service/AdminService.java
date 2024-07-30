package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.AdminDTO;
import com.xworkz.issuemanagement.dto.ComplaintRaiseDTO;
import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.util.List;

public interface AdminService {

    boolean findByEmailAndPassword(String email,String Password);

    //fetch userDetails by SignUpId
    List<SignUpDTO> findByUserId(SignUpDTO signUpDTO);

    //fetch ComplaintRaiseDetails by complaintId
    List<ComplaintRaiseDTO> findByComplaintRaiseId(ComplaintRaiseDTO complaintRaiseDTO);

    //admin search by based on complaint type And city
    List<ComplaintRaiseDTO> searchByComplaintTypeAndCity(String complaintType, String city);


    //admin search by complaint type or city
    List<ComplaintRaiseDTO> searchByComplaintTypeOrCity(String complaintType, String city);

    //fetch city names for dropdown
    List<ComplaintRaiseDTO> findAllCities();

    //fetch adminName to display in navBar
    String getAdminName(String email, String password);


    //save department
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

    //find all departmentName for displaying in ViewComplaintRaise.jsp
    List<DepartmentDTO> findByDepartmentName(String  departmentName);

    //update status and department id
    boolean updateStatusAndDepartmentId(int complaintId, int departmentId, String status);


}
