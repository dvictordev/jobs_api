package br.com.victor.gestao_vagas.modules.candidate.repositorie;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.gestao_vagas.modules.candidate.entities.CandidateEntity;

import java.util.List;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

    Optional<CandidateEntity> findByUsername(String username);
}
