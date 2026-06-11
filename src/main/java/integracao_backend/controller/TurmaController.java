package integracao_backend.controller;

import integracao_backend.DTO.request.CriarTurmaDTO;
import integracao_backend.DTO.responses.DashboardAlunosTurmaResponse;
import integracao_backend.service.AdminService;
import integracao_backend.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    private final TurmaService turmaService;
    private final AdminService adminService;

    public TurmaController(TurmaService turmaService, AdminService adminService) {
        this.turmaService = turmaService;
        this.adminService = adminService;
    }

    @GetMapping("/{turmaId}/detalhes")
    public List<DashboardAlunosTurmaResponse> detalhes(@PathVariable Long turmaId) {
        return turmaService.buscarDashboardTurmaPorProfessor(turmaId);
    }

    @GetMapping("/listarTurmas")
    public ResponseEntity<?> listarTurmas() {
        return ResponseEntity.ok(adminService.listarTurmas());
    }

    @PostMapping("/adicionarTurma")
    public ResponseEntity<?> adicionarTurma(@Valid @RequestBody CriarTurmaDTO dto) {
        return ResponseEntity.status(201).body(adminService.criarTurma(dto));
    }

    @DeleteMapping("/removerTurma/{id}")
    public ResponseEntity<?> removerTurma(@PathVariable Long id) {
        adminService.excluirTurma(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}