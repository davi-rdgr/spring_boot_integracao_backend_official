package integracao_backend.service;

import integracao_backend.DTO.interfaces.DashboardDisciplinaProfessorDTO;
import integracao_backend.DTO.responses.DashboardDisciplinaProfessorResponse;
import integracao_backend.DTO.interfaces.DashboardTurmaProfessorDTO;
import integracao_backend.DTO.responses.DashboardTurmaCompletaResponse;
import integracao_backend.DTO.responses.DashboardTurmaProfessorResponse;
import integracao_backend.model.Professor;
import integracao_backend.repository.DisciplinaRepository;
import integracao_backend.repository.TurmaRepository;
import integracao_backend.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;
    private final DisciplinaRepository disciplinaRepository;

    public ProfessorService(TurmaRepository turmaRepository, ProfessorRepository professorRepository, DisciplinaRepository disciplinaRepository) {
        this.turmaRepository = turmaRepository;
        this.professorRepository = professorRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public DashboardTurmaCompletaResponse buscarDashboardProfessorCompleto(Long id) {

        Professor professor = professorRepository.findByUsuarioId(id).orElseThrow(() -> new RuntimeException("Professor não encontrado para usuário " + id));

        List<DashboardDisciplinaProfessorDTO> projecaoDisciplinas = disciplinaRepository.buscarDisciplinaProfessor(professor.getId());
        List<DashboardTurmaProfessorDTO> projecaoTurmas = turmaRepository.buscarTurmasProfessor(professor.getId());

        return new DashboardTurmaCompletaResponse(professor, projecaoDisciplinas, projecaoTurmas);
    }

    public List<DashboardDisciplinaProfessorResponse> buscarDashboardDisciplinaProfessor(Long id) {
        List<DashboardDisciplinaProfessorDTO> projecao = disciplinaRepository.buscarDisciplinaProfessor(id);

        return projecao.stream()
                .map(disciplina -> new DashboardDisciplinaProfessorResponse(disciplina))
                .toList();
    }
}
