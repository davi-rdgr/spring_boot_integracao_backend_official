package integracao_backend.DTO.responses;

import integracao_backend.DTO.interfaces.DashboardMatriculaDetalhesDTO;
import integracao_backend.DTO.interfaces.DashboardNotaDTO;

import java.time.LocalDate;
import java.util.List;

public class DashboardMatriculaDetalhesResponse implements DashboardMatriculaDetalhesDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataNascimento;
    private LocalDate dataMatricula;
    private String nomeTurma;
    private String ano;
    private List<DashboardNotaDTO> notas;

    public DashboardMatriculaDetalhesResponse(DashboardMatriculaDetalhesDTO matricula, List<DashboardNotaDTO> nota) {
        this.id = matricula.getId();
        this.nome = matricula.getNome();
        this.email = matricula.getEmail();
        this.cpf = matricula.getCpf();
        this.dataNascimento = matricula.getDataNascimento();
        this.dataMatricula = matricula.getDataMatricula();
        this.nomeTurma = matricula.getNomeTurma();
        this.ano = matricula.getAno();
        this.notas = nota;
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
    public String getEmail() {
        return email;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    @Override
    public String getNomeTurma() {
        return nomeTurma;
    }

    @Override
    public String getAno() {
        return ano;
    }

    public List<DashboardNotaDTO> getNotas() {
        return notas;
    }

}
