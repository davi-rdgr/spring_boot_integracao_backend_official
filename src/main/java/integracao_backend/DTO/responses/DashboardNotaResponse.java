package integracao_backend.DTO.responses;

import integracao_backend.DTO.interfaces.DashboardNotaDTO;

public class DashboardNotaResponse implements DashboardNotaDTO {

    private Long id;
    private String disciplina;
    private double valor;

    public DashboardNotaResponse(DashboardNotaDTO nota) {
        this.id = nota.getId();
        this.disciplina = nota.getDisciplina();
        this.valor = nota.getValor();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getDisciplina() {
        return disciplina;
    }

    @Override
    public double getValor() {
        return valor;
    }

}
