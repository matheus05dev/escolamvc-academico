package br.senai.sp.escolamvc.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;

@DiscriminatorValue(value = "A")
@Entity
public class Aluno extends Pessoa {

    private String matricula;


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matrucula) {
        this.matricula = matrucula;
    }
}

