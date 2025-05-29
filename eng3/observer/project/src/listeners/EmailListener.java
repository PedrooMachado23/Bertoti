package listeners;

import java.io.File;

public class EmailListener implements EventListener{
    private String email;

    public EmailListener(String email){
        this.email = email;
    }

    public void atualizar(String acao, File arquivo){
        System.out.println("Email para " + email + ": Alguem " + acao + " o seguinte arquivo: " + arquivo.getName());
    }
}
