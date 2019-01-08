package com.faf.twitterCloneApp.models;


import com.faf.twitterCloneApp.models.util.TweetType;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
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

    private TweetType type;

    private Long parentTweetId;

    // set the default value of columns
    @PrePersist
    public void prePersist() {
        if (this.type == null) {
            this.type = TweetType.Tweet;
        }

    }

    public Tweet(){}


}
