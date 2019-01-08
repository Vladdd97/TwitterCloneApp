package com.faf.twitterCloneApp.models;


import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Type(type = "text")
    private String content;

    @Column
    @Type(type="timestamp")
    private Date createDate;


    @ManyToOne
    private Tweet tweet;

    @ManyToOne
    private TwitterUser twitterUser;

    public Comment(){}

}
