package com.faf.twitterCloneApp.models;


import com.faf.twitterCloneApp.models.util.Gender;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@Entity
public class TwitterUserInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private Gender gender;

    private String userInfo;

    @ColumnDefault("false")
    private Boolean isEmailNotificationEnabled;

    @OneToOne
    private TwitterUser twitterUser;

    public TwitterUserInfo(){}

    // set the default value of columns
    @PrePersist
    public void prePersist(){
        if ( this.isEmailNotificationEnabled == null){
            this.isEmailNotificationEnabled = false;
        }
        if ( this.gender == null){
            this.gender = Gender.Male;
        }
    }

}
