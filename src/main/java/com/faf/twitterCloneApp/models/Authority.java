package com.faf.twitterCloneApp.models;


import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String role;

    @ManyToOne
    private TwitterUser twitterUser;

    public Authority() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Authority;
    }

}
