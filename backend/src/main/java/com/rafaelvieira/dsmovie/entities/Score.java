package com.rafaelvieira.dsmovie.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_score")
public class Score {

    //É atribuido o new para garantir a instancia do objeto de relacionamento entre as tabelas
    @EmbeddedId
    private ScorePK id = new ScorePK();
    private Double value;

    public Score(){
    }

    //Faz a referencia de avaliação com filme
    public void setMovie(Movie movie){
        id.setMovie(movie);
    }
    //Faz a referencia de avaliação do usuário com filme
    public void setUser(User user) {
        id.setUser(user);
    }

    public ScorePK getId() {
        return id;
    }

    public void setId(ScorePK id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
