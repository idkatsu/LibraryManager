package com.example.demo.service.impl;

import com.example.demo.dto.ReaderDTO;
import com.example.demo.exception.ResourseNotFoundException;
import com.example.demo.model.Reader;
import com.example.demo.repository.IReaderRepository;
import com.example.demo.service.interfaces.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService implements IReaderService {

    private final IReaderRepository readerRepository;

    private ReaderDTO mapToReaderDTO(Reader reader) {
        return new ReaderDTO(
                reader.getId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getEmail(),
                reader.getPhone(),
                reader.getRegistrationDate()
        );
    }

    private Reader mapToReader(ReaderDTO readerDTO) {
        return new Reader(
                readerDTO.getId(),
                readerDTO.getFirstName(),
                readerDTO.getLastName(),
                readerDTO.getEmail(),
                readerDTO.getPhone(),
                readerDTO.getRegistrationDate()
        );
    }

    @Autowired
    public ReaderService(IReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public ReaderDTO createReader(ReaderDTO readerDTO) {
        Reader reader = mapToReader(readerDTO);
        Reader saved = readerRepository.save(reader);
        return mapToReaderDTO(saved);
    }

    @Override
    public List<ReaderDTO> getAllReaders() {
        return readerRepository.findAll().stream()
                .map(this::mapToReaderDTO)
                .toList();
    }

    @Override
    public ReaderDTO getReaderById(Long id) {
        return readerRepository.findById(id)
                .map(this::mapToReaderDTO)
                .orElseThrow(() -> new ResourseNotFoundException("Книги с таким id нет"));
    }

    @Override
    public ReaderDTO updateReader(Long id, ReaderDTO readerDTO) {

        readerRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Книги с таким id нет"));

        Reader reader = mapToReader(readerDTO);
        reader.setId(id);
        Reader updated = readerRepository.save(reader);
        return mapToReaderDTO(updated);
    }

    @Override
    public void deleteReader(Long id) {

        if (!readerRepository.existsById(id)) {
            throw new ResourseNotFoundException("Книги с таким id нет");
        }

        readerRepository.deleteById(id);
    }
}
