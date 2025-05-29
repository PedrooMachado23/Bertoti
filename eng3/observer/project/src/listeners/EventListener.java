package listeners;

import java.io.File;

public interface EventListener {
    void atualizar(String acao, File arquivo);
}
