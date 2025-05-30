public class UsuarioController {
    private UsuarioModel model;
    private UsuarioView view;

    public UsuarioController(UsuarioModel model, UsuarioView view){
        this.model = model;
        this.view = view;
    }

    public void atualizarView(){
        this.view.exibirDetalhesUsuario(model.getNome(), model.getEmail());
    }

    public void setUsuarioNome(String nome){
        this.model.setNome(nome);
    }

    public String getUsuarioNome(){
        return this.model.getNome();
    }

    public void setUsuarioEmail(String email){
        this.model.setEmail(email);
    }

    public String getUsuarioEmail(){
        return this.model.getEmail();
    }
}
