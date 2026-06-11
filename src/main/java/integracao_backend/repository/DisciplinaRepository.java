package integracao_backend.repository;

import integracao_backend.DTO.interfaces.DashboardDisciplinaProfessorDTO;
import integracao_backend.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    Optional<Disciplina> findByNome(String nome);

    @Query(value = """
            SELECT dd.id AS disciplina_id, pp.id AS professor_id, dd.nome AS disciplina FROM professor_disciplina pd
                JOIN professor pp ON pp.id = pd.professor_id
                JOIN disciplina dd ON dd.id = pd.disciplina_id
                WHERE pp.id = :id;
            """, nativeQuery = true)
    List<DashboardDisciplinaProfessorDTO> buscarDisciplinaProfessor(Long id);
}
