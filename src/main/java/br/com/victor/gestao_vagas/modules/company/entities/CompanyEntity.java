package br.com.victor.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

//Table for the database
@Entity(name = "company")
// Create getters and setters
@Data
public class CompanyEntity {

    /* Declare the table columns and types */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Validation with pattern
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço.")
    private String username;

    // Email validation
    @Email(message = "O campo [email] deve conter um e-mail valido.")
    private String email;

    // Size validation
    @Length(min = 6, max = 100, message = "A senha deve conter entre 6 e 100 caracteres.")
    private String password;

    private String website;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
