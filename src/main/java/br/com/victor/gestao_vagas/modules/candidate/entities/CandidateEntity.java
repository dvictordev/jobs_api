package br.com.victor.gestao_vagas.modules.candidate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "candidato fulano da silva", requiredMode = RequiredMode.REQUIRED, description = "Nome do usuario")
    private String name;

    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço.")
    @Schema(example = "CandidatoFulano", requiredMode = RequiredMode.REQUIRED, description = "username do usuario")
    private String username;
    @Email(message = "O campo [email] deve conter um e-mail valido.")
    @Schema(example = "example@email.com", requiredMode = RequiredMode.REQUIRED, description = "Email do usuario")
    private String email;

    @Length(min = 6, max = 100, message = "A senha deve conter entre 6 e 100 caracteres.")
    @Schema(example = "123456", maxLength = 100, minLength = 6, requiredMode = RequiredMode.REQUIRED, description = "Senha do usuario")
    private String password;
    @Schema(example = "Desenvolvedor Java | Spring", description = "Descricao do usuario")
    private String description;
    @Schema(example = "Desenvolvedor Java com 2 anos de experiencia", description = "Curriculo do usuario")
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
