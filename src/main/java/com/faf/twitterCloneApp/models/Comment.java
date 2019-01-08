package com.faf.twitterCloneApp.models;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

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

    private String createByUser;

    @ManyToOne
    private Twitt twitt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateByUser() {
        return createByUser;
    }

    public void setCreateByUser(String createByUser) {
        this.createByUser = createByUser;
    }

    public Twitt getTwitt() {
        return twitt;
    }

    public void setTwitt(Twitt twitt) {
        this.twitt = twitt;
    }
}
