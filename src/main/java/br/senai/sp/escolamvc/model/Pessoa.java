package br.senai.sp.escolamvc.model;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.br.CPF;

import java.sql.Date;
import java.time.Instant;
import java.time.OffsetDateTime;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "tipo",
        length = 1,
        discriminatorType = DiscriminatorType.STRING
)
@Data
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @CPF(message = "CPF INVÁLIDO")
    @NotEmpty(message = "O CPF deve ser informado")
    private String cpf;


    @NotEmpty(message = "Email deve ser informado")
    private String email;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @CreationTimestamp(source = SourceType.DB)
    private Instant dataCadastro;

    @UpdateTimestamp(source = SourceType.DB)
    private Instant dataAtualizacao;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

}
