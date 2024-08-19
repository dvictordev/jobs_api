package br.com.victor.gestao_vagas.modules.company.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.gestao_vagas.modules.company.entities.JobEntity;

public interface JobRepositorie extends JpaRepository<JobEntity, UUID> {

  List<JobEntity> findByDescriptionContaining(String filter);
}
