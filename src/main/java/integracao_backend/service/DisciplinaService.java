package integracao_backend.service;

import integracao_backend.model.Disciplina;
import integracao_backend.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }
}
