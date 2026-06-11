package integracao_backend.controller;

import integracao_backend.DTO.responses.DashboardUsuarioResponse;
import integracao_backend.DTO.request.LoginDTO;
import integracao_backend.model.Usuario;
import integracao_backend.service.AdminService;
import integracao_backend.service.AlunoService;
import integracao_backend.service.ProfessorService;
import integracao_backend.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AlunoService alunoService;
    private final ProfessorService professorService;
    private final AdminService adminService;

    public UsuarioController(UsuarioService usuarioService, AlunoService alunoService,
                             ProfessorService professorService, AdminService adminService) {
        this.usuarioService = usuarioService;
        this.alunoService = alunoService;
        this.professorService = professorService;
        this.adminService = adminService;
    }

    @PostMapping("auth")
    public ResponseEntity<?> autenticacao(@RequestBody LoginDTO loginDTO) {
        Usuario usuario = usuarioService.findByLogin(loginDTO.getLogin());

        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuario não encontrado");
        }

        if (!usuario.getSenha().equals(loginDTO.getSenha())) {
            return ResponseEntity.status(401).body("Senha incorreta");
        }

        return ResponseEntity.ok(new DashboardUsuarioResponse(usuario));
    }

    @GetMapping("/{usuarioId}/dashboard")
    public ResponseEntity<?> meuPerfil(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.findById(usuarioId);

        return switch (usuario.getPerfil()) {
            case ALUNO ->
                ResponseEntity.ok(alunoService.buscarDashboardAluno(usuario.getId()));
            case PROFESSOR ->
                ResponseEntity.ok(professorService.buscarDashboardProfessorCompleto(usuario.getId()));
            case ADMIN ->
                ResponseEntity.ok(adminService.buscarDashboardAdmin());
            default ->
                ResponseEntity.status(403).build();
        };
    }
}
