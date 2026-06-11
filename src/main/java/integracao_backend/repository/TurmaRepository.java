package integracao_backend.repository;

import integracao_backend.DTO.interfaces.DashboardAlunosTurmaDTO;
import integracao_backend.DTO.interfaces.DashboardTurmaProfessorDTO;
import integracao_backend.model.Turma;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findAll();

    Optional<Turma> findById(Long id);

    @Query(value = """
                SELECT 
                    tt.id, 
                    tt.nome, 
                    tt.ano
                FROM turma_professor tp
                JOIN turma tt ON tp.turma_id = tt.id
                WHERE tp.professor_id = :id;
            """, nativeQuery = true)
    List<DashboardTurmaProfessorDTO> buscarTurmasProfessor(@Param("id") Long id);

    @Query(value = """
                SELECT aa.id, aa.nome, mm.turma_id FROM matricula mm
                JOIN aluno aa ON aa.id = mm.aluno_id
                WHERE mm.turma_id = :turmaId;
            """, nativeQuery = true)
    List<DashboardAlunosTurmaDTO> buscarAlunosPorTurma(@Param("turmaId") Long turmaId);


}
