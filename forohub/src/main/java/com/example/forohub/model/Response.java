package com.example.forohub.model;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RESPONSE")
public class Response {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "CREATIONDATE")
    private LocalDate creationDate;

    @Column(name = "SOLUTION")
    private String solution;

    @ManyToOne
    @JoinColumn(name = "TOPIC")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "AUTHOR")
    private Author author;

    public Response() {}

    @JsonCreator
    public Response(@JsonProperty("id") Long id, 
                    @JsonProperty("message") String message, 
                    @JsonProperty("creationDate") LocalDate creationDate, 
                    @JsonProperty("solution") String solution, 
                    @JsonProperty("topic") Topic topic, 
                    @JsonProperty("author") Author author) {
        this.id = id;
        this.message = message;
        this.creationDate = creationDate;
        this.solution = solution;
        this.topic = topic;
        this.author = author;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSolution() {
        return this.solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Topic getTopic() {
        return this.topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}