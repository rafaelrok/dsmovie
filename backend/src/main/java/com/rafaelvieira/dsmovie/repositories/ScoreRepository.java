package com.rafaelvieira.dsmovie.repositories;

import com.rafaelvieira.dsmovie.entities.Score;
import com.rafaelvieira.dsmovie.entities.ScorePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, ScorePK> {
}
