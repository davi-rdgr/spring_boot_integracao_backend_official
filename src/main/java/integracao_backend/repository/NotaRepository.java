package integracao_backend.repository;

import integracao_backend.DTO.interfaces.DashboardNotaDTO;
import integracao_backend.DTO.interfaces.DashboardNotasPorTurmasEprofessorDTO;
import integracao_backend.model.Nota;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NotaRepository extends JpaRepository<Nota, Long> {

    @Query(value = """
                SELECT n.* FROM nota n
                JOIN matricula m ON n.matricula_id = m.id
                WHERE m.aluno_id = :alunoId
            """, nativeQuery = true)
    List<Nota> buscarNotasPorAluno(Long alunoId);

    @Query(value = """
                SELECT n.id AS id, d.nome AS disciplina, n.valor
                    FROM nota n
                    JOIN matricula m ON n.matricula_id = m.id
                    JOIN disciplina d ON n.disciplina_id = d.id
                    WHERE m.aluno_id = :alunoId
            """, nativeQuery = true)
    List<DashboardNotaDTO> buscarNotasDetalhadas(Long alunoId);

    @Query(value = """
                SELECT\s
                        n.id AS nota_id,
                        a.id AS aluno_id,
                        a.nome AS aluno_nome,
                        d.nome AS disciplina,
                        n.valor AS nota
                    FROM nota n
                    JOIN matricula m ON m.id = n.matricula_id
                    JOIN aluno a ON a.id = m.aluno_id
                    JOIN disciplina d ON d.id = n.disciplina_id
                    WHERE m.turma_id = :turmaId
                    ORDER BY a.nome, d.nome;
            """, nativeQuery = true)
    List<DashboardNotasPorTurmasEprofessorDTO> buscarNotasPorTurmasEprofessor(Long turmaId);

    @Modifying
    @Query("""
            UPDATE Nota n 
                SET n.valor = :valor 
                WHERE n.id = :id
            """)
    void atualizarNota(Long id, Double valor);
}
