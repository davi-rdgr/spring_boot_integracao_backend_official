package integracao_backend.DTO.responses;

import integracao_backend.model.Aluno;
import integracao_backend.model.Professor;
import integracao_backend.model.Turma;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDashboardResponse {
    private long totalAlunos;
    private long totalProfessores;
    private long totalTurmas;
    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Turma> turmas;
}
