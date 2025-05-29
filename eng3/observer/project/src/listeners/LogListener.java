package listeners;

import java.io.File;

public class LogListener implements EventListener{
    private File arquivo;

    public LogListener(String nomeArquivo){
        this.arquivo = new File(nomeArquivo);
    }

    public void atualizar(String acao, File arquivo) {
        System.out.println("Registro do arquivo: " + arquivo + ": Alguem " + acao + " o seguinte arquivo: " + arquivo.getName());
    }
}
