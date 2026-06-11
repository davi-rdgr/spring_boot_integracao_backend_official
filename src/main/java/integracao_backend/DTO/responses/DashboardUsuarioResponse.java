package integracao_backend.DTO.responses;

import integracao_backend.DTO.interfaces.DashboardUsuarioDTO;
import integracao_backend.enums.UserLevel;
import integracao_backend.model.Usuario;

public class DashboardUsuarioResponse implements DashboardUsuarioDTO {

    private Long id;
    private String login;
    private UserLevel perfil;

    public DashboardUsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.perfil = usuario.getPerfil();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public UserLevel getPerfil() {
        return perfil;
    }
}
