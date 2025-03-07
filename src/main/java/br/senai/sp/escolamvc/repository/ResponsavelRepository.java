package br.senai.sp.escolamvc.repository;

import br.senai.sp.escolamvc.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    List<Responsavel> findResponsavelsByNomeContaining(String nome);

    Responsavel findResponsavelByCpf(String cpf);

    List<Responsavel> findResponsavelsByNomeContainingOrCpfContaining(
            String nome,
            String cpf
    );
}
