package integracao_backend.DTO.request;

public class LoginDTO {

    private String login;
    private String senha;

    public LoginDTO() {
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
