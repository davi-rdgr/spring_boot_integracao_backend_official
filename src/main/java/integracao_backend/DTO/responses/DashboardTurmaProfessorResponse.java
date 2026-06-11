package integracao_backend.DTO.responses;

import integracao_backend.DTO.interfaces.DashboardTurmaProfessorDTO;

public class DashboardTurmaProfessorResponse implements DashboardTurmaProfessorDTO {
    private Long id;
    private String nome;
    private int ano;

    public DashboardTurmaProfessorResponse(DashboardTurmaProfessorDTO turma) {
        this.id = turma.getId();
        this.nome = turma.getNome();
        this.ano = turma.getAno();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getAno() {
        return ano;
    }
}
