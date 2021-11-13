package com.quickpoll.pollingapi.domain;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="VOTE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="OPTION_ID")
    private AOption AOption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AOption getOption() {
        return AOption;
    }

    public void setOption(AOption AOption) {
        this.AOption = AOption;
    }

}
