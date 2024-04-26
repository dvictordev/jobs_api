package br.com.victor.gestao_vagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.gestao_vagas.modules.company.entities.CompanyEntity;

//Creates the repositorie tha is going to communicate with the database
public interface CompanyRepositorie extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUsernameOrEmail(String name, String password);

    Optional<CompanyEntity> findByUsername(String username);
}
