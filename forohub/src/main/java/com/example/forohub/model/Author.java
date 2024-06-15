package com.example.forohub.model;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "AUTHOR")
public class Author {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Topic> topics;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Response> responses;

    @ManyToMany
    @JoinTable(
        name = "AUTHOR_PROFILE",
        joinColumns = @JoinColumn(name = "AUTHOR"),
        inverseJoinColumns = @JoinColumn(name = "PROFILE")
    )
    private Set<Profile> profiles;

    public Author() {}
    
    @JsonCreator
    public Author(@JsonProperty("id") Long id, 
                    @JsonProperty("name") String name, 
                    @JsonProperty("email") String email, 
                    @JsonProperty("password") String password,
                    @JsonProperty("profiles") Set<Profile> profiles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.topics = new ArrayList<>();
        this.responses = new ArrayList<>();
        this.profiles = new HashSet<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addTopic(Topic topic) {
        this.topics.add(topic);
    }

    public void addResponse(Response response) {
        this.responses.add(response);
    }

    public void addProfile(Profile profile) {
        this.profiles.add(profile);
    }

    public Set<Profile> getProfiles() {
        return this.profiles;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Author) {
            return ((Author) o).getEmail().compareTo(this.getEmail()) == 0 
                && ((Author) o).getPassword().compareTo(this.getPassword()) == 0;
        }

        return false;
    }
}