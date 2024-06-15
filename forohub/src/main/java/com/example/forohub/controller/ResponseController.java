package com.example.forohub.controller;

import java.util.List;
import java.util.Optional;

import com.example.forohub.model.Response;
import com.example.forohub.repository.ResponseRepository;

import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {
    @Autowired
    private ResponseRepository responseRepository;

    @GetMapping
    public List<Response> getAllResponses() {
        return responseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Response> getResponseById(@PathVariable Long id) {
        return responseRepository.findById(id);
    }

    @PostMapping
    public Response createResponse(@RequestBody Response response) {
        response.getTopic().addResponse(response);
        response.getAuthor().addResponse(response);

        return responseRepository.save(response);
    }

    @PutMapping("/{id}")
    public Response updateResponse(@PathVariable Long id, @RequestBody Response responseDetails) {
        Response response = responseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada con id " + id));

        response.setMessage(responseDetails.getMessage());
        response.setCreationDate(responseDetails.getCreationDate());
        response.setSolution(responseDetails.getSolution());
        response.setTopic(responseDetails.getTopic());
        response.setAuthor(responseDetails.getAuthor());

        return responseRepository.save(response);
    }

    @DeleteMapping("/{id}")
    public void deleteResponse(@PathVariable Long id) {
        Response response = responseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada con id " + id));

        responseRepository.delete(response);
    }
}