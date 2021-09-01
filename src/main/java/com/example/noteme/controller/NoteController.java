package com.example.noteme.controller;

import com.example.noteme.model.Note;
import com.example.noteme.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping
    public ResponseEntity<Iterable<Note>> findAll() {
        return new ResponseEntity<>(noteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> findById(@PathVariable Long id) {
        Optional<Note> noteOptional = noteService.findById(id);
        if (!noteOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(noteOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Note> add(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.save(note), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@RequestBody Note note, @PathVariable Long id) {
        Optional<Note> noteOptional = noteService.findById(id);
        if (!noteOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        note.setId(id);
        return new ResponseEntity<>(noteService.save(note), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Note> deleteById(@PathVariable Long id) {
        noteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
