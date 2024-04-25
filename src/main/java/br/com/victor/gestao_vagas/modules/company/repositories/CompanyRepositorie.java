package br.com.victor.gestao_vagas.modules.company.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.gestao_vagas.modules.company.entities.CompanyEntity;

public interface CompanyRepositorie extends JpaRepository<CompanyEntity, UUID> {

}
