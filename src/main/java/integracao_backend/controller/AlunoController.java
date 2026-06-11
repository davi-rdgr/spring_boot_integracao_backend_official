package integracao_backend.controller;

import integracao_backend.DTO.request.CriarAlunoDTO;
import integracao_backend.DTO.responses.DashboardMatriculaDetalhesResponse;
import integracao_backend.service.AdminService;
import integracao_backend.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    private final AdminService adminService;

    public AlunoController(AlunoService alunoService, AdminService adminService) {
        this.alunoService = alunoService;
        this.adminService = adminService;
    }

    @GetMapping("/{id}/detalhes")
    public DashboardMatriculaDetalhesResponse detalhes(@PathVariable Long id) {
        return alunoService.buscarDashboardAluno(id);
    }

    @GetMapping("/buscarPorTurma/{turmaId}")
    public ResponseEntity<?> buscarPorTurma(@PathVariable Long turmaId) {
        return ResponseEntity.ok(adminService.listarAlunosPorTurma(turmaId));
    }

    @PostMapping("/adicionarAluno")
    public ResponseEntity<?> adicionarAluno(@Valid @RequestBody CriarAlunoDTO dto) {
        return ResponseEntity.status(201).body(adminService.criarAluno(dto));
    }

    @DeleteMapping("/removerAluno/{id}")
    public ResponseEntity<?> removerAluno(@PathVariable Long id) {
        adminService.excluirAluno(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}
