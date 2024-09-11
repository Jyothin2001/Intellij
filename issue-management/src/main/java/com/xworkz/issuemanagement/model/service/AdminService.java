package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.*;
import com.xworkz.issuemanagement.model.repo.RegDeptAdminRepo;

import java.util.List;
import java.util.Optional;

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

    //NonUniqueResultException for duplicate entry in DepartmentDTO  it affect findByDepartmentType() (repo=SingleResultSet)
    boolean existsByDepartmentName(String departmentName);



    //find all departmentName for displaying in ViewComplaintRaise.jsp
    List<DepartmentDTO> findByDepartmentName();

    //update status and department id
    boolean updateStatusAndDepartmentId(int complaintId, int departmentId, String status);



}
