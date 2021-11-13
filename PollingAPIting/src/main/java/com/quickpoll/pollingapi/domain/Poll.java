package com.quickpoll.pollingapi.domain;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="POLL_ID")
    private Long id;

    @Column(name="QUESTION")
    @NotEmpty
    private String question;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="POLL_ID")
    @OrderBy
    @Size(min=2, max = 6)
    private Set<AOption> aOptions;

    public Poll() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<AOption> getOptions() {
        return aOptions;
    }

    public void setOptions(Set<AOption> aOptions) {
        this.aOptions = aOptions;
    }
}
