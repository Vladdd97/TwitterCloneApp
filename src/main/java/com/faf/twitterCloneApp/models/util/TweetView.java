package com.faf.twitterCloneApp.models.util;

import com.faf.twitterCloneApp.models.Tweet;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class TweetView {

    private Tweet parentTweet;

    private Tweet tweet;

    public TweetView(){}
}
