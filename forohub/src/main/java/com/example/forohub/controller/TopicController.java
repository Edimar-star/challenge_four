package com.example.forohub.controller;

import java.util.List;
import java.util.Optional;

import com.example.forohub.model.Topic;
import com.example.forohub.repository.TopicRepository;

import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/topics")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Topic> getTopicById(@PathVariable Long id) {
        return topicRepository.findById(id);
    }

    @PostMapping
    public Topic createTopic(@RequestBody Topic topic) {
        topic.getAuthor().addTopic(topic);
        topic.getCourse().addTopic(topic);

        return topicRepository.save(topic);
    }

    @PutMapping("/{id}")
    public Topic updateTopic(@PathVariable Long id, @RequestBody Topic topicDetails) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado con id " + id));

        topic.setTitle(topicDetails.getTitle());
        topic.setMessage(topicDetails.getMessage());
        topic.setCreationDate(topicDetails.getCreationDate());
        topic.setStatus(topicDetails.getStatus());
        topic.setAuthor(topicDetails.getAuthor());
        topic.setCourse(topicDetails.getCourse());

        return topicRepository.save(topic);
    }

    @DeleteMapping("/{id}")
    public void deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado con id " + id));

        topicRepository.delete(topic);
    }
}