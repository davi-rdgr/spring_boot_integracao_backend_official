package integracao_backend.service;

import integracao_backend.DTO.interfaces.DashboardMatriculaDetalhesDTO;
import integracao_backend.DTO.responses.DashboardMatriculaDetalhesResponse;
import integracao_backend.DTO.interfaces.DashboardNotaDTO;
import integracao_backend.model.Aluno;
import integracao_backend.repository.AlunoRepository;
import integracao_backend.repository.MatriculaRepository;
import integracao_backend.repository.NotaRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final MatriculaRepository matriculaRepository;
    private final NotaRepository notaRepository;

    public AlunoService(AlunoRepository alunoRepository, MatriculaRepository matriculaRepository, NotaRepository notaRepository) {
        this.alunoRepository = alunoRepository;
        this.matriculaRepository = matriculaRepository;
        this.notaRepository = notaRepository;
    }

    public DashboardMatriculaDetalhesResponse buscarDashboardAluno(Long usuarioId) {
        Aluno aluno = alunoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado para usuário " + usuarioId));

        DashboardMatriculaDetalhesDTO projecao = matriculaRepository
                .buscarDashboardAluno(aluno.getId())
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        List<DashboardNotaDTO> notas = notaRepository.buscarNotasDetalhadas(aluno.getId());
        return new DashboardMatriculaDetalhesResponse(projecao, notas);
    }

}
