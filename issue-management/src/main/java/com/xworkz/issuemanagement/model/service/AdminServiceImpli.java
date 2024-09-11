package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.*;
import com.xworkz.issuemanagement.model.repo.AdminRepo;
import com.xworkz.issuemanagement.model.repo.ComplaintRaiseRepoImpli;
import com.xworkz.issuemanagement.model.repo.RegDeptAdminRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class AdminServiceImpli implements AdminService
{
    @Autowired
   private AdminRepo adminRepo;


    @Override
    public boolean findByEmailAndPassword(String email, String Password)
    {
      AdminDTO adminDTO= adminRepo.findByEmailAndPassword(email, Password);
      log.info("AdminDTO in Service:{}",adminDTO);
        if (adminDTO!= null)
        {
            log.info("findByEmailAndPassword successful in AdminServiceImpl");
            return true;
        }
        else
        {
            log.info("findByEmailAndPassword not successful in AdminServiceImpl");
        }
        return false;
    }

    @Override
    public List<SignUpDTO> findByUserId(SignUpDTO signUpDTO) {

        System.out.println("findById method in AdminServiceImpl..");
        List<SignUpDTO> dtoData = adminRepo.findByUserId(signUpDTO);
        if (dtoData != null) {
            System.out.println("findById data successful in AdminServiceImpl..");
            return dtoData;
        } else {
            System.out.println("findById data not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();

    }

    @Override
    public List<ComplaintRaiseDTO> findByComplaintRaiseId(ComplaintRaiseDTO complaintRaiseDTO) {

        log.info("findByComplaintRaiseId method in AdminServiceImpl(Raise complaintDTO)");

        List<ComplaintRaiseDTO> data = adminRepo.findByComplaintRaiseId(complaintRaiseDTO);
        if (data != null) {
            log.info("findByComplaintRaiseId  data successful in AdminServiceImpl");
            return data;
        } else {
            log.info("findByComplaintRaiseId data not successful in AdminServiceImpl");
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintRaiseDTO> searchByComplaintTypeAndCity(String complaintType, String city) {
        log.info("searchByComplaintTypeAndCity method running in AdminServiceImpl..");
        List<ComplaintRaiseDTO> data = adminRepo.searchByComplaintTypeAndCity(complaintType, city);

        if (!data.isEmpty()) {
            log.info("searchByComplaintTypeANDCity successful in AdminServiceImpl..");
            return data;
        } else {
            log.info("searchByComplaintTypeANDCity not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintRaiseDTO> searchByComplaintTypeOrCity(String complaintType, String city) {
        log.info("searchByComplaintTypeORCity method running in AdminServiceImpl..");
        List<ComplaintRaiseDTO> data = adminRepo.searchByComplaintTypeOrCity(complaintType, city);

        if (!data.isEmpty()) {
            log.info("searchByComplaintTypeORCity successful in AdminServiceImpl..");
            return data;
        } else {
            log.info("searchByComplaintTypeORCity not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintRaiseDTO> findAllCities() {
        log.info("cities method running in AdminServiceImpl..");
        List<ComplaintRaiseDTO> data = adminRepo.findAllCities();

        if (!data.isEmpty()) {
            log.info("cityies successful in AdminServiceImpl..");
            return data;
        } else {
            log.info("cities not successful in AdminServiceImpl..");
        }


        return Collections.emptyList();
    }

    @Override
    public String getAdminName(String email, String password) {
        log.info("getAdminName method running in AdminServiceImpl..");
        String data = adminRepo.getAdminName(email, password);

        if (data!=null) {
            log.info("getAdminName successful in AdminServiceImpl..");
            return data;
        } else {
            log.info("getAdminName not successful in AdminServiceImpl..");
        }

        return null;
    }

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        log.info("saveDepartment method running in AdminServiceImpl..");

        DepartmentDTO data = adminRepo.saveDepartment(departmentDTO);

        log.info("data:{}" ,data);

        if (data != null) {
            log.info("saveDepartment  successful in AdminServiceImpl..");

            return data;
        } else {
            log.info("saveDepartment not successful in AdminServiceImpl..");
        }

        return null;
    }

    @Override
    public boolean existsByDepartmentName(String departmentName) {
        log.info("existsByDepartmentName method running in AdminServiceImpl..");
        return  adminRepo.existsByDepartmentName(departmentName).isPresent();
    }

    @Override
    public List<DepartmentDTO> findByDepartmentName() {
        log.info("findByDepartmentName method running in AdminServiceImpl..");
        List<DepartmentDTO> data = adminRepo.findByDepartmentName();

        if(data!=null)
        {
            log.info("findByDepartmentName successful in AdminServiceImpl..");
            return data;
        }
        else
        {
            log.info("findByDepartmentName  not successful in AdminServiceImpl..");
        }

        return Collections.emptyList();
    }

    @Override
    public boolean updateStatusAndDepartmentId(int complaintId, int departmentId, String status) {

        log.info("updateStatusAndDepartmentId method running in RaiseComplaintService");
       return adminRepo.updateStatusAndDepartmentId(complaintId, departmentId, status);

    }



}
