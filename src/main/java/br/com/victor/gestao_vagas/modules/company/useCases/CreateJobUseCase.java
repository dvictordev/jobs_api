package br.com.victor.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.victor.gestao_vagas.modules.company.entities.JobEntity;
import br.com.victor.gestao_vagas.modules.company.repositories.JobRepositorie;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepositorie jobRepositorie;

    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRepositorie.save(jobEntity);
    }

}
