package br.com.victor.gestao_vagas.modules.candidate.controllers;

import br.com.victor.gestao_vagas.modules.candidate.dto.ProfileCandidateReponseDTO;
import br.com.victor.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.victor.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.victor.gestao_vagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import br.com.victor.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.victor.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            var result = this.createCandidateUseCase.execute(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Tag(name = "Candidato", description = "Informacoes do candidato")
    @Operation(summary = "Perfil do candidato", description = "Essa funcao é responsavel por buscar as informacoes do perfil do candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCandidateReponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "User not found")

    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> findCandidate(HttpServletRequest httpServletRequest) {
        var id = httpServletRequest.getAttribute("candidate_id");
        try {
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(id.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Tag(name = "Candidato", description = "Informacoes do candidato")
    @Operation(summary = "Listagem de vagas disponiveis para o candidato", description = "Essa funcao é responsavel por listar todas as vagas disponiveis baseada no filtro")
    @ApiResponses(@ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
    }))
    @SecurityRequirement(name = "jwt_auth")
    public List<JobEntity> findJobsByFilter(@RequestParam String filter) {
        return this.listAllJobsByFilterUseCase.execute(filter);
    }

}
