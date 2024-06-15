package com.example.forohub.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;
import javax.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "COURSE")
public class Course {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY")
    private String category;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Topic> topics;

    public Course() {}

    @JsonCreator
    public Course(@JsonProperty("id") Long id, 
                    @JsonProperty("name") String name, 
                    @JsonProperty("category") String category) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.topics = new ArrayList<>();
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

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void addTopic(Topic topic) {
        this.topics.add(topic);
    }
}