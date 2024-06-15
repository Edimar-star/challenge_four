package com.example.forohub.model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "TOPIC")
public class Topic {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "CREATIONDATE")
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "AUTHOR")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "COURSE")
    private Course course;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Response> responses;

    public Topic() {}

    @JsonCreator
    public Topic(@JsonProperty("id") Long id, 
                    @JsonProperty("title") String title, 
                    @JsonProperty("message") String message, 
                    @JsonProperty("creationDate") LocalDate creationDate, 
                    @JsonProperty("status") Status status, 
                    @JsonProperty("author") Author author, 
                    @JsonProperty("course") Course course) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.status = status;
        this.author = author;
        this.course = course;
        this.responses = new ArrayList<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = this.creationDate;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void addResponse(Response response) {
        this.responses.add(response);
    }
}