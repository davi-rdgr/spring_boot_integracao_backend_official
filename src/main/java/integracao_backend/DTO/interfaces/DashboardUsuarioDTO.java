package integracao_backend.DTO.interfaces;

import integracao_backend.enums.UserLevel;

public interface DashboardUsuarioDTO {

    public Long getId();

    public String getLogin();

    public UserLevel getPerfil();
}