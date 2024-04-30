package br.com.victor.gestao_vagas.modules.candidate.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.victor.gestao_vagas.exceptions.UserFoundException;
import br.com.victor.gestao_vagas.modules.candidate.dto.ProfileCandidateReponseDTO;
import br.com.victor.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.victor.gestao_vagas.modules.candidate.repositorie.CandidateRepository;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateReponseDTO execute(UUID idCandidate) throws UserFoundException {
        var candidate = this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UserFoundException();
        });

        var candidateReponseDTO = ProfileCandidateReponseDTO.builder().description(candidate.getDescription())
                .email(candidate.getEmail()).name(candidate.getName()).id(candidate.getId())
                .username(candidate.getUsername()).build();

        return candidateReponseDTO;

    }

}
