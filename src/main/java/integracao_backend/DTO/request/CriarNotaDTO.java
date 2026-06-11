package integracao_backend.DTO.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CriarNotaDTO {
    private Long alunoId;
    private Long turmaId;
    private Long disciplinaId;
    private Double valor;
}
