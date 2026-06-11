package integracao_backend.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarProfessorDTO {
    @NotBlank
    private String nome;
    @Email
    private String email;
    @NotBlank
    private String especialidade;
    @NotBlank
    private String login;
    @NotBlank
    private String senha;
    @NotNull
    private Long turmaId;
    @NotNull
    private Long disciplinaId;
}
