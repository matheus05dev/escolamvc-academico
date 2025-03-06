package br.senai.sp.escolamvc.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@DiscriminatorValue(value = "R")
@Entity
public class Responsavel extends Pessoa {

}