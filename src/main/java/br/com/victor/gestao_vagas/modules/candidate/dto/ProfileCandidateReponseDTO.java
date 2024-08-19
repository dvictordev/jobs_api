package br.com.victor.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateReponseDTO {

    @Schema(example = "Desenvolvedor Java")
    private String description;
    @Schema(example = "DevJava")
    private String username;
    @Schema(example = "example@email.com")
    private String email;
    private UUID id;
    @Schema(example = "candidato fulano da silva")
    private String name;

}
