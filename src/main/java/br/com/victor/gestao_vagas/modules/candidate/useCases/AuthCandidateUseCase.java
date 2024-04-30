package br.com.victor.gestao_vagas.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.victor.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import br.com.victor.gestao_vagas.modules.candidate.dto.AuthCandidateReponseDTO;
import br.com.victor.gestao_vagas.modules.candidate.repositorie.CandidateRepository;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateReponseDTO execute(AuthCandidateDTO authCandidateDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateDTO.username()).orElseThrow(() -> {
            throw new UsernameNotFoundException("User/password incorrect!");
        });
        ;

        var passwordMatches = this.passwordEncoder.matches(authCandidateDTO.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expires_at = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withSubject(candidate.getId().toString())
                .sign(algorithm);

        var authCandidateResponseDto = AuthCandidateReponseDTO.builder().access_token(token).expires_at(
                expires_at.toEpochMilli()).build();

        return authCandidateResponseDto;

    }
}
