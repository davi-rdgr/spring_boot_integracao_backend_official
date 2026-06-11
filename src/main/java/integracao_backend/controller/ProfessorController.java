package integracao_backend.controller;

import integracao_backend.DTO.request.CriarProfessorDTO;
import integracao_backend.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final AdminService adminService;

    public ProfessorController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/listarProfessores")
    public ResponseEntity<?> listarProfessores() {
        return ResponseEntity.ok(adminService.listarProfessores());
    }

    @GetMapping("/listarEspecialidades")
    public ResponseEntity<?> listarEspecialidades() {
        return ResponseEntity.ok(adminService.listarEspecialidades());
    }

    @PostMapping("/adicionarProfessor")
    public ResponseEntity<?> adicionarProfessor(@Valid @RequestBody CriarProfessorDTO dto) {
        return ResponseEntity.status(201).body(adminService.criarProfessor(dto));
    }

    @DeleteMapping("/removerProfessor/{id}")
    public ResponseEntity<?> removerProfessor(@PathVariable Long id) {
        adminService.excluirProfessor(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}
