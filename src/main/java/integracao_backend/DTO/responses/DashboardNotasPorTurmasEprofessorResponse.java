package integracao_backend.DTO.responses;

import integracao_backend.DTO.interfaces.DashboardNotasPorTurmasEprofessorDTO;

public class DashboardNotasPorTurmasEprofessorResponse implements DashboardNotasPorTurmasEprofessorDTO {
    private Long notaId;
    private Long alunoid;
    private String alunoNome;
    private String disciplina;
    private Double nota;

    public DashboardNotasPorTurmasEprofessorResponse(DashboardNotasPorTurmasEprofessorDTO projecao) {
        this.notaId = projecao.getNotaId();
        this.alunoid = projecao.getAlunoId();
        this.alunoNome = projecao.getAlunoNome();
        this.disciplina = projecao.getDisciplina();
        this.nota = projecao.getNota();
    }

    @Override
    public Long getNotaId() {
        return notaId;
    }

    @Override
    public Long getAlunoId() {
        return alunoid;
    }

    @Override
    public String getAlunoNome() {
        return alunoNome;
    }

    @Override
    public String getDisciplina() {
        return disciplina;
    }

    @Override
    public Double getNota() {
        return nota;
    }
}
