package integracao_backend.repository;

import integracao_backend.model.Aluno;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByUsuarioId(Long usuarioId);
}
