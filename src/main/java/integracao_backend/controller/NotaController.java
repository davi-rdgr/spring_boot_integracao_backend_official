package integracao_backend.controller;

import integracao_backend.DTO.request.AtualizarNotaDTO;
import integracao_backend.DTO.request.CriarNotaDTO;
import integracao_backend.DTO.request.NotasDetalhadasRequest;
import integracao_backend.DTO.responses.DashboardNotasPorTurmasEprofessorResponse;
import integracao_backend.service.NotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {
    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<?> criarNota(@RequestBody CriarNotaDTO dto) {
        notaService.criarNota(dto);
        return ResponseEntity.status(201).body("Nota criada com sucesso");
    }

    @PostMapping("/notasDetalhadas")
    public List<DashboardNotasPorTurmasEprofessorResponse> notasDetalhadas(@RequestBody NotasDetalhadasRequest request) {
        return notaService.trazerNotasDetalhadas(
                request.getTurmaId()
        );
    }

    @PostMapping("/atualizarNotas")
    public void atualizarNotas(@RequestBody List<AtualizarNotaDTO> notas) {
        notaService.atualizarNotas(notas);
    }
}