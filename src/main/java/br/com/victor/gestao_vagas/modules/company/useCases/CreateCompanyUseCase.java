package br.com.victor.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.victor.gestao_vagas.exceptions.UserFoundException;
import br.com.victor.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.victor.gestao_vagas.modules.company.repositories.CompanyRepositorie;

@Service
public class CreateCompanyUseCase {

    // Dependency injection
    @Autowired
    private CompanyRepositorie companyRepositorie;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepositorie.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        ;

        var password = passwordEncoder.encode(companyEntity.getPassword());

        companyEntity.setPassword(password);

        return this.companyRepositorie.save(companyEntity);
    }
}
