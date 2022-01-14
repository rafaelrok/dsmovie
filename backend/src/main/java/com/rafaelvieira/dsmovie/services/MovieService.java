package com.rafaelvieira.dsmovie.services;

import com.rafaelvieira.dsmovie.dto.MovieDTO;
import com.rafaelvieira.dsmovie.entities.Movie;
import com.rafaelvieira.dsmovie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Transactional
    public Page<MovieDTO> findAll(Pageable pageable) {
        Page<Movie> result = repository.findAll(pageable);
        Page<MovieDTO> dto = result.map(x -> new MovieDTO(x));
        //Page<MovieDTO> dto = result.map(MovieDTO::new); 2º opçõa
        return dto;
    }

    @Transactional
    public MovieDTO findById(Long id) {
        Movie result = repository.findById(id).get();
        MovieDTO dto = new MovieDTO(result);
        return dto;
    }

}
