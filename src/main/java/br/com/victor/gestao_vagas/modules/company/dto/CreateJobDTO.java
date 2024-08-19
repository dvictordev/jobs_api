package br.com.victor.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
public class CreateJobDTO {

    @Schema(example = "Vaga Desenvolvedor java junior", requiredMode = RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "Vale alimentacao, home-office, PLR, Gym-pass", requiredMode = RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "Junior", requiredMode = RequiredMode.REQUIRED)
    private String level;

}
