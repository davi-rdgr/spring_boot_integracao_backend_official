package integracao_backend.DTO.interfaces;

import java.time.LocalDate;

public interface DashboardMatriculaDetalhesDTO {
    Long getId();
    
    String getNome();
    
    String getEmail();
    
    String getCpf();
    
    LocalDate getDataNascimento();
    
    LocalDate getDataMatricula();
    
    String getNomeTurma();
    
    String getAno();
    
}
