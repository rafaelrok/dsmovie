package com.rafaelvieira.dsmovie.services;

import com.rafaelvieira.dsmovie.dto.MovieDTO;
import com.rafaelvieira.dsmovie.dto.ScoreDTO;
import com.rafaelvieira.dsmovie.entities.Movie;
import com.rafaelvieira.dsmovie.entities.Score;
import com.rafaelvieira.dsmovie.entities.User;
import com.rafaelvieira.dsmovie.repositories.MovieRepository;
import com.rafaelvieira.dsmovie.repositories.ScoreRepository;
import com.rafaelvieira.dsmovie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ScoreService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO scoreDTO) {
        //Pega o usuário por email e verificar se existe na base de dados
        //caso não exista, cria um novo usuário
        User user = userRepository.findByEmail(scoreDTO.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(scoreDTO.getEmail());
            user = userRepository.saveAndFlush(user);
        }

        //Pega o filme por id e verificar se existe na base de dados
        Movie movie = movieRepository.findById(scoreDTO.getMovieId()).get();

        //Cria um novo score verifica a associação do usuário e filme
        //e salva no banco de dados com a avalização do usuário
        Score score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(scoreDTO.getScore());
        score = scoreRepository.saveAndFlush(score);

        //Atualiza a média do filme
        double soma = 0;
        for(Score s : movie.getScores()) {
            soma += s.getValue();
            //soma opcional
            //soma = soma + s.getValue();
        }

        //calculo de media de scores do filme com geral
        double avg = soma / movie.getScores().size();

        //pega o filme lança a avaliação
        movie.setScore(avg);
        //Calcula a média do filme
        movie.setCount(movie.getScores().size());

        movie = movieRepository.saveAndFlush(movie);

        //Converte o movie para DTO
        return new MovieDTO(movie);
    }
}
