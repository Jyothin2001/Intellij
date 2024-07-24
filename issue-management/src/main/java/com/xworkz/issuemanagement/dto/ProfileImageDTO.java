package com.xworkz.issuemanagement.dto;

import com.xworkz.issuemanagement.constants.Status;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString
@Slf4j
@Entity

@Table(name="image_upload")
public class ProfileImageDTO
{
    public ProfileImageDTO()
    {
        log.info("No parameter constructor created in EditProfileImageDTO ");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id")
    private int imageId;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_type")
    private String imageType;

    @Column(name = "image_size")
    private long imageSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private SignUpDTO user;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private  LocalDateTime updatedOn;

    @Column(name = "image_path")
    private  String imagePath;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;







//@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
}
