package integracao_backend.DTO.responses;

import integracao_backend.DTO.interfaces.DashboardAlunosTurmaDTO;

public class DashboardAlunosTurmaResponse implements DashboardAlunosTurmaDTO {

    private Long alunoId;
    private String nome;
    private Long turmaId;

    public DashboardAlunosTurmaResponse(DashboardAlunosTurmaDTO projecao) {
        this.alunoId = projecao.getId();
        this.nome = projecao.getNome();
        this.turmaId = projecao.getTurmaId();
    }

    @Override
    public Long getId() {
        return alunoId;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public Long getTurmaId() {
        return turmaId;
    }
}
