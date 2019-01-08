package com.faf.twitterCloneApp.models;


import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Type(type = "text")
    private String content;

    @ManyToOne
    private TwitterUser twitterUser;

    @Column
    @Type(type="timestamp")
    private Date createDate;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "tweet")
    private List<Comment> comments;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "tweet")
    private List<Reaction> reactions;


}
