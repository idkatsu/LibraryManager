package com.example.demo.service;

import com.example.demo.dto.ReaderDTO;

import java.util.List;

public interface IReaderService {

    ReaderDTO createReader(ReaderDTO readerDTO);

    List<ReaderDTO> getAllReaders();

    ReaderDTO getReaderById(Long id);

    ReaderDTO updateReader(Long id, ReaderDTO readerDTO);

    void deleteReader(Long id);
}
