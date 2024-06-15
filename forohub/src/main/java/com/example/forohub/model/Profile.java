package com.example.forohub.model;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PROFILE")
public class Profile {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "profiles")
    private Set<Author> authors;

    public Profile() {}

    @JsonCreator
    public Profile(@JsonProperty("id") Long id, 
                    @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
        this.authors = new HashSet<>();
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

    public void addAuthor(Author author) {
        this.authors.add(author);
    }
}