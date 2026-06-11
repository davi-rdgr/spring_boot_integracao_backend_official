package integracao_backend.DTO.interfaces;

import integracao_backend.model.Professor;

import java.util.List;

public interface DashboardTurmaCompletaDTO {

    Long getId();

    String getNome();

    String getEmail();

    List<DashboardDisciplinaProfessorDTO> getDisciplinas();

    List<DashboardTurmaProfessorDTO> getTurmas();
}
