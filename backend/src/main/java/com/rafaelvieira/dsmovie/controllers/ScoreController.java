package com.rafaelvieira.dsmovie.controllers;

import com.rafaelvieira.dsmovie.dto.MovieDTO;
import com.rafaelvieira.dsmovie.dto.ScoreDTO;
import com.rafaelvieira.dsmovie.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/scores")
public class ScoreController {

@Autowired
private ScoreService scoreService;

    @PutMapping
    public MovieDTO saveScore(@RequestBody ScoreDTO dto) {
        MovieDTO movieDTO = scoreService.saveScore(dto);
        return movieDTO;
    }
}

