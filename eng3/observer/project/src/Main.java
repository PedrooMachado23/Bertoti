import editor.Editor;
import listeners.EmailListener;
import listeners.LogListener;

public class Main {
    public static void main(String[] args) throws Exception {
        Editor editor = new Editor();
        editor.manager.inscrever("abriu", new LogListener("project/arquivo.txt"));
        editor.manager.inscrever("editou", new EmailListener("admin1@example.com"));
        editor.manager.inscrever("salvou", new EmailListener("admin2@emxample.com"));

        try {
            editor.abrirArquivo("project/arquivo.txt");
            editor.editarArquivo();
            editor.salvarArquivo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
