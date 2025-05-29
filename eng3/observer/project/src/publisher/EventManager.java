package publisher;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import listeners.EventListener;

public class EventManager {
    Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventManager(String... acoes) {
        for (String acao: acoes) {
            this.listeners.put(acao, new ArrayList<>());
        }
    }

    public void inscrever(String acao, EventListener listener){
        List<EventListener> usuarios = listeners.get(acao);
        usuarios.add(listener);
    }

    public void desinscrever(String acao, EventListener listener){
        List<EventListener> usuarios = listeners.get(acao);
        usuarios.remove(listener);
    }

    public void notificar(String acao, File arquivo){
        List<EventListener> usuarios = listeners.get(acao);
        for (EventListener listener : usuarios) {
            listener.atualizar(acao, arquivo);
        }
    }
}
