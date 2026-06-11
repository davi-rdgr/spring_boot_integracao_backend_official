package integracao_backend.controller;

import integracao_backend.service.DisciplinaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplinas")
public class  DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping("/listarDisciplinas")
    public ResponseEntity<?> listarDisciplinas() {
        return ResponseEntity.ok(disciplinaService.listarDisciplinas());
    }
}
