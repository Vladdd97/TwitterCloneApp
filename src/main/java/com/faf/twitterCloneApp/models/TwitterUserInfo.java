package com.faf.twitterCloneApp.models;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class TwitterUserInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String gender;

    private String userInfo;

    @OneToOne
    private TwitterUser twitterUser;

}
