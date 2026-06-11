package integracao_backend.repository;

import integracao_backend.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByUsuarioId(Long id);

    @Query("SELECT DISTINCT p.especialidade FROM Professor p ORDER BY p.especialidade")
    List<String> findDistinctEspecialidades();

    @Modifying
    @Query(value = "INSERT INTO turma_professor (professor_id, turma_id, disciplina_id) VALUES (:professorId, :turmaId, :disciplinaId)", nativeQuery = true)
    void associarTurma(@Param("professorId") Long professorId, @Param("turmaId") Long turmaId, @Param("disciplinaId") Long disciplinaId);
}
