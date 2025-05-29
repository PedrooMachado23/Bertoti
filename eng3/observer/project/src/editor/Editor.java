package editor;

import java.io.File;

import publisher.EventManager;

public class Editor {
    public EventManager manager;
    private File arquivo;

    public Editor() {
        this.manager = new EventManager("abriu", "editou", "salvou");
    }

    public void abrirArquivo(String caminhoArquivo){
        this.arquivo = new File(caminhoArquivo);
        manager.notificar("abriu", arquivo);
    }

    public void editarArquivo() throws Exception{
        if (this.arquivo != null){
            manager.notificar("editou", arquivo);
        } else {
            throw new Exception("Arquivo não aberto.");
        }
    }

    public void salvarArquivo() throws Exception{
        if (this.arquivo != null){
            manager.notificar("salvou", arquivo);
        } else {
            throw new Exception("Arquivo não aberto.");
        }
    }
}
