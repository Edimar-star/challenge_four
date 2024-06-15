package com.example.forohub.controller;

import java.util.List;
import java.util.Optional;

import com.example.forohub.model.Author;
import com.example.forohub.model.Profile;
import com.example.forohub.repository.AuthorRepository;
import com.example.forohub.repository.ProfileRepository;

import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Profile> getProfileById(@PathVariable Long id) {
        return profileRepository.findById(id);
    }

    @PostMapping("/{id}")
    public Profile createProfile(@PathVariable Long id, @RequestBody Profile profile) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrada con id " + id));

        author.addProfile(profile);
        profile.addAuthor(author);

        return profileRepository.save(profile);
    }

    @PutMapping("/{id}")
    public Profile updateProfile(@PathVariable Long id, @RequestBody Profile profileDetails) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con id " + id));

        profile.setName(profileDetails.getName());

        return profileRepository.save(profile);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con id " + id));

        profileRepository.delete(profile);
    }
}