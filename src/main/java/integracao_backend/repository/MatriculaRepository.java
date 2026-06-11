package integracao_backend.repository;

import integracao_backend.DTO.interfaces.DashboardMatriculaDetalhesDTO;
import integracao_backend.model.Matricula;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    @Query(value = """
                SELECT 
                    a.id,
                    a.nome, 
                    a.email, 
                    a.cpf, 
                    a.data_nascimento, 
                    m.data_matricula, 
                    t.nome AS nome_turma, 
                    t.ano 
                FROM matricula m
                JOIN aluno a ON a.id = aluno_id
                JOIN turma t ON t.id = turma_id
                WHERE a.id = :alunoId
            """, nativeQuery = true)
    List<DashboardMatriculaDetalhesDTO> buscarDashboardAluno(@Param("alunoId") Long alunoId);

    Optional<Matricula> findByAlunoIdAndTurmaId(Long alunoId, Long turmaId);
}
