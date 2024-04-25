package br.com.victor.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.victor.gestao_vagas.exceptions.UserFoundException;
import br.com.victor.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.victor.gestao_vagas.modules.company.repositories.CompanyRepositorie;

@Service
public class CreateCompanyUseCase {

    // Dependency injection
    @Autowired
    private CompanyRepositorie companyRepositorie;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepositorie.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        ;

        return this.companyRepositorie.save(companyEntity);
    }
}
