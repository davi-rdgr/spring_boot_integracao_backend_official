package integracao_backend.service;

import integracao_backend.DTO.interfaces.DashboardAlunosTurmaDTO;
import integracao_backend.DTO.responses.DashboardAlunosTurmaResponse;
import integracao_backend.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {
    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public List<DashboardAlunosTurmaResponse> buscarDashboardTurmaPorProfessor(Long turmaId) {
        return turmaRepository.buscarAlunosPorTurma(turmaId)
                .stream()
                .map(aluno -> new DashboardAlunosTurmaResponse(aluno))
                .toList();
    }




}
