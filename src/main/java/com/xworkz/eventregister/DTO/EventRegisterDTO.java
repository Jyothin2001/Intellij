package com.xworkz.eventregister.DTO;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Component
public class EventRegisterDTO implements Serializable,Comparable<EventRegisterDTO>
{

      @NotNull(message = "name can not be null")
      @Size(min=3 ,max = 30,message = "name should be min 3 and max 30")
      private String fullname;

        private String email;
        private String gender;
        private String phone;
        private String comments;
        private String event;

        public EventRegisterDTO()
        {
            System.out.println("no args EventRegisterDTO:");
        }

        @Override
        public String toString() {
            return "EventRegisterDTO{" +
                    "comments='" + comments + '\'' +
                    ", fullname='" + fullname + '\'' +
                    ", email='" + email + '\'' +
                    ", gender='" + gender + '\'' +
                    ", phone='" + phone + '\'' +
                    ", event='" + event + '\'' +
                    '}';
        }


        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public int compareTo(EventRegisterDTO o)
        {
            return 0;
        }
    }


