package br.com.victor.gestao_vagas.modules.candidate.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.victor.gestao_vagas.modules.company.entities.JobEntity;
import br.com.victor.gestao_vagas.modules.company.repositories.JobRepositorie;

@Service
public class ListAllJobsByFilterUseCase {

  @Autowired
  private JobRepositorie jobRepositorie;

  public List<JobEntity> execute(String filter) {

    return this.jobRepositorie.findByDescriptionContaining(filter);
  }
}
