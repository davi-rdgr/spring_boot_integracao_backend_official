package integracao_backend.DTO.request;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//@Entity sem entity pois não tem id
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarAlunoDTO {
    @NotBlank
    private String nome;
    @Email
    private String email;
    @NotBlank
    private String cpf;
    @NotBlank
    private LocalDate nascimento;
    @NotBlank
    private String login;
    @NotBlank
    private String senha;
    @NotBlank
    private Long turmaId;

}
