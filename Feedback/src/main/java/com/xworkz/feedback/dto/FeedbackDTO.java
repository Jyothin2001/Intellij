package com.xworkz.feedback.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
//@ToString
@Table(name="feedback_details")
public @ToString class FeedbackDTO implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //same column name
    @Getter
    @Setter
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$", message = "Name can  contain letters and spaces also")
   // @Setter
    //@Getter
    @Column(name="user_name")
    private String name;

 // @AssertTrue(message = "msg")
    //@NotBlank(message = "You must agree to the terms and conditions")
   // @Getter
    //it is a checkbox don't save into database anyway user select that option
    //to avoid not to save into db use @Transient
    @Transient
    private String condition;

    @NotNull(message = " rating is required")
    @Column(name="feedback_service_rate")
    private String rating;

    @NotBlank(message = "please Comment ")
    @Column(name="feedback_comments")
    private String comments;

    @NotBlank(message = "Please select an option")
    @Column(name="feedback_recommend_to_friend")
    private String recommend;


    public FeedbackDTO()
    {
        System.out.println("no args FeedBackDTO:");

    }
}
