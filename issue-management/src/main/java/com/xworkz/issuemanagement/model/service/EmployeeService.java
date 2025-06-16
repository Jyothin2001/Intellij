package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import javax.servlet.http.HttpSession;

public interface EmployeeService {
    boolean saveEmployeeDetails(EmployeeDTO employeeDTO);

    //fetch departmentName for storing departmentId
    DepartmentDTO findByDepartmentType(String employeeDeptName);

    //to check whether emailId exists or not in database
    EmployeeDTO findByEmail(String emailId);

    EmployeeDTO updateEmployeeDetails(EmployeeDTO employeeDTO);


//    @Service
//    public class OtpService {
//
//        public String generateOtp(Integer employeeId) {
//            // Generate a random 6-digit OTP
//            Random random = new Random();
//            int otp = 100000 + random.nextInt(900000);
//            return String.valueOf(otp);
//        }
//
//        public void saveOtpForEmployee(Integer employeeId, String otp) {
//            // Save the OTP in the database or session for later validation
//            // You can use a temporary storage mechanism here
//        }
//    }

//
//    @Service
//    public class MailService {
//
//        @Autowired
//        private JavaMailSender mailSender;
//
//        public void sendOtpEmail(String to, String otp) {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(to);
//            message.setSubject("Your OTP Code");
//            message.setText("Your OTP code is: " + otp);
//            mailSender.send(message);
//        }
//    }


}
