public class Main {
    public static void main(String[] args) throws Exception {
        UsuarioModel model = new UsuarioModel("Pedro", "123@example.com");

        UsuarioView view = new UsuarioView();

        UsuarioController controller = new UsuarioController(model, view);

        controller.atualizarView();

        controller.setUsuarioNome("Vitor");
        controller.setUsuarioEmail("321@example.com");

        controller.atualizarView();
    }
}
