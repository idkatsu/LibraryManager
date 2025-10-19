package com.example.demo.controller;

import com.example.demo.dto.ReaderDTO;
import com.example.demo.service.interfaces.IReaderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    private final IReaderService readerService;

    @Autowired
    public ReaderController(IReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    public List<ReaderDTO> getAllReaders() {
        return readerService.getAllReaders();
    }

    @GetMapping("/{id}")
    public ReaderDTO getReaderById(@PathVariable Long id) {
        return readerService.getReaderById(id);
    }

    @PostMapping
    public ReaderDTO createReader(@Valid @RequestBody ReaderDTO readerDTO) {
        return readerService.createReader(readerDTO);
    }

    @PutMapping("/{id}")
    public ReaderDTO updateReader(
            @PathVariable Long id,
            @Valid @RequestBody ReaderDTO readerDTO) {
        return readerService.updateReader(id, readerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
    }
}
