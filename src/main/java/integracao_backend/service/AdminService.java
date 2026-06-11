package integracao_backend.service;

import integracao_backend.DTO.interfaces.DashboardAlunosTurmaDTO;
import integracao_backend.DTO.request.CriarAlunoDTO;
import integracao_backend.DTO.request.CriarProfessorDTO;
import integracao_backend.DTO.request.CriarTurmaDTO;
import integracao_backend.DTO.responses.AdminDashboardResponse;
import integracao_backend.enums.UserLevel;
import integracao_backend.model.Aluno;
import integracao_backend.model.Matricula;
import integracao_backend.model.Professor;
import integracao_backend.model.Turma;
import integracao_backend.model.Usuario;
import integracao_backend.repository.AlunoRepository;
import integracao_backend.repository.DisciplinaRepository;
import integracao_backend.repository.MatriculaRepository;
import integracao_backend.repository.ProfessorRepository;
import integracao_backend.repository.TurmaRepository;
import integracao_backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminService {

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;
    private final UsuarioRepository usuarioRepository;
    private final MatriculaRepository matriculaRepository;
    private final DisciplinaRepository disciplinaRepository;

    public AdminService(AlunoRepository alunoRepository, ProfessorRepository professorRepository,
                        TurmaRepository turmaRepository, UsuarioRepository usuarioRepository,
                        MatriculaRepository matriculaRepository, DisciplinaRepository disciplinaRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.turmaRepository = turmaRepository;
        this.usuarioRepository = usuarioRepository;
        this.matriculaRepository = matriculaRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public AdminDashboardResponse buscarDashboardAdmin() {
        List<Aluno> alunos = alunoRepository.findAll();
        List<Professor> professores = professorRepository.findAll();
        List<Turma> turmas = turmaRepository.findAll();
        return new AdminDashboardResponse(alunos.size(), professores.size(), turmas.size(), alunos, professores, turmas);
    }

    @Transactional
    public Aluno criarAluno(CriarAlunoDTO dto) {
        Usuario usuario = new Usuario(null, dto.getLogin(), dto.getSenha(), UserLevel.ALUNO);
        usuario = usuarioRepository.save(usuario);

        Aluno aluno = new Aluno(null, dto.getNome(), dto.getEmail(), dto.getCpf(), dto.getNascimento(), usuario.getId());
        aluno = alunoRepository.save(aluno);

        if (dto.getTurmaId() != null) {
            turmaRepository.findById(dto.getTurmaId())
                    .orElseThrow(() -> new RuntimeException("Turma não encontrada: " + dto.getTurmaId()));
            Matricula matricula = new Matricula(null, aluno.getId(), dto.getTurmaId(), LocalDate.now());
            matriculaRepository.save(matricula);
        }

        return aluno;
    }

    @Transactional
    public void excluirAluno(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado: " + alunoId));
        Usuario usuario = usuarioRepository.findById(aluno.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + aluno.getUsuarioId()));
        usuarioRepository.deleteById(usuario.getId());
    }

    @Transactional
    public Professor criarProfessor(CriarProfessorDTO dto) {
        turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada: " + dto.getTurmaId()));

        disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada: " + dto.getDisciplinaId()));

        Long disciplinaId = dto.getDisciplinaId();

        Usuario usuario = new Usuario(null, dto.getLogin(), dto.getSenha(), UserLevel.PROFESSOR);
        usuario = usuarioRepository.save(usuario);

        Professor professor = new Professor(null, dto.getNome(), dto.getEmail(), dto.getEspecialidade(), usuario.getId());
        professor = professorRepository.save(professor);

        professorRepository.associarTurma(professor.getId(), dto.getTurmaId(), disciplinaId);
        return professor;
    }

    public List<DashboardAlunosTurmaDTO> listarAlunosPorTurma(Long turmaId) {
        turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada: " + turmaId));
        return turmaRepository.buscarAlunosPorTurma(turmaId);
    }

    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public List<String> listarEspecialidades() {
        return professorRepository.findDistinctEspecialidades();
    }

    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }

    @Transactional
    public void excluirProfessor(Long usuarioId) {
        usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + usuarioId));
        professorRepository.findByUsuarioId(usuarioId).ifPresent(p -> professorRepository.deleteById(p.getId()));
        usuarioRepository.deleteById(usuarioId);
    }

    public Turma criarTurma(CriarTurmaDTO dto) {
        Turma turma = new Turma(null, dto.getNome(), dto.getAno());
        return turmaRepository.save(turma);
    }

    public void excluirTurma(Long turmaId) {
        turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada: " + turmaId));
        turmaRepository.deleteById(turmaId);
    }
}
