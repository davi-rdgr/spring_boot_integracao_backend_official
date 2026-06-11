package integracao_backend.service;

import integracao_backend.DTO.request.AtualizarNotaDTO;
import integracao_backend.DTO.request.CriarNotaDTO;
import integracao_backend.DTO.interfaces.DashboardNotasPorTurmasEprofessorDTO;
import integracao_backend.DTO.responses.DashboardNotasPorTurmasEprofessorResponse;
import integracao_backend.model.Matricula;
import integracao_backend.model.Nota;
import integracao_backend.repository.MatriculaRepository;
import integracao_backend.repository.NotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {
    private final NotaRepository notaRepository;
    private final MatriculaRepository matriculaRepository;

    public NotaService(NotaRepository notaRepository, MatriculaRepository matriculaRepository) {
        this.notaRepository = notaRepository;
        this.matriculaRepository = matriculaRepository;
    }

    public void criarNota(CriarNotaDTO dto) {
        Matricula matricula = matriculaRepository
                .findByAlunoIdAndTurmaId(dto.getAlunoId(), dto.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));

        Nota nota = new Nota();
        nota.setMatriculaId(matricula.getId());
        nota.setDisciplinaId(dto.getDisciplinaId());
        nota.setValor(dto.getValor());

        notaRepository.save(nota);
    }

    public List<DashboardNotasPorTurmasEprofessorResponse> trazerNotasDetalhadas(Long turmaId) {
        return notaRepository.buscarNotasPorTurmasEprofessor(turmaId)
                .stream()
                .map(nota -> new DashboardNotasPorTurmasEprofessorResponse(nota))
                .toList();
    }

    @Transactional
    public void atualizarNotas(List<AtualizarNotaDTO> notas) {
        for (AtualizarNotaDTO dto : notas) {
            notaRepository.atualizarNota(dto.getNotaId(), dto.getNota());
        }
    }
}
