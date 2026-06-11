package integracao_backend.DTO.responses;

import integracao_backend.DTO.interfaces.DashboardDisciplinaProfessorDTO;

public class DashboardDisciplinaProfessorResponse implements DashboardDisciplinaProfessorDTO {
    private Long disciplinaId;
    private Long professorId;
    private String disciplina;

    public DashboardDisciplinaProfessorResponse(DashboardDisciplinaProfessorDTO projecao) {
        this.disciplinaId = projecao.getDisciplinaId();
        this.professorId = projecao.getProfessorId();
        this.disciplina = projecao.getDisciplina();
    }

    @Override
    public Long getDisciplinaId() {
        return disciplinaId;
    }

    @Override
    public Long getProfessorId() {
        return professorId;
    }

    @Override
    public String getDisciplina() {
        return disciplina;
    }
}
