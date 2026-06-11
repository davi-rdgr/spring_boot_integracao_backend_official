package integracao_backend.DTO.responses;

import integracao_backend.DTO.interfaces.DashboardDisciplinaProfessorDTO;
import integracao_backend.DTO.interfaces.DashboardTurmaCompletaDTO;
import integracao_backend.DTO.interfaces.DashboardTurmaProfessorDTO;
import integracao_backend.model.Professor;

import java.util.List;

public class DashboardTurmaCompletaResponse implements DashboardTurmaCompletaDTO {
    private Long id;
    private String nome;
    private String email;
    private List<DashboardDisciplinaProfessorDTO> disciplinas;
    private List<DashboardTurmaProfessorDTO> turmas;

    public DashboardTurmaCompletaResponse(
            Professor professor,
            List<DashboardDisciplinaProfessorDTO> disciplinas,
            List<DashboardTurmaProfessorDTO> turmas
    ) {
        this.id = professor.getId();
        this.nome = professor.getNome();
        this.email = professor.getEmail();
        this.disciplinas = disciplinas;
        this.turmas = turmas;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<DashboardDisciplinaProfessorDTO> getDisciplinas() {
        return disciplinas;
    }

    public List<DashboardTurmaProfessorDTO> getTurmas() {
        return turmas;
    }
}
