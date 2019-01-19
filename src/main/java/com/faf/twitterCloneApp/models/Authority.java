package com.faf.twitterCloneApp.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String role;

    @ManyToOne
    @JsonBackReference
    private TwitterUser twitterUser;

    public Authority() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Authority;
    }

}
