package br.com.victor.gestao_vagas.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.victor.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import br.com.victor.gestao_vagas.modules.candidate.repositorie.CandidateRepository;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCandidateDTO authCandidateDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateDTO.getUsername()).orElseThrow(() -> {
            throw new UsernameNotFoundException("User/password incorrect!");
        });
        ;

        var passwordMatches = this.passwordEncoder.matches(authCandidateDTO.getPassword(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(candidate.getId().toString())
                .sign(algorithm);

        return token;

    }
}
