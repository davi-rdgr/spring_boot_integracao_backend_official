package integracao_backend.service;

import integracao_backend.model.Usuario;
import integracao_backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findByLogin(String login) {
        Usuario usuario = usuarioRepository
                .findByLogin(login)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return usuario;
    }

    public Usuario findById(Long id) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return usuario;
    }
}
