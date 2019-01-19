package com.faf.twitterCloneApp.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
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
    @JsonBackReference
    private Tweet tweet;

    @ManyToOne
    @JsonBackReference
    private TwitterUser twitterUser;

    public Comment(){}

}
