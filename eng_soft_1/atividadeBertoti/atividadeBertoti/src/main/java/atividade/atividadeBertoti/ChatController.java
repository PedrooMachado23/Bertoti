package atividade.atividadeBertoti;

import java.time.Duration;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class  ChatController{
private final ChatLanguageModel modelo;
	
	public ChatController() {
		this.modelo = OpenAiChatModel.builder()
				.apiKey("lm-studio")
				.baseUrl("http://localhost:2000/v1")
				.modelName("TheBloke/nsql-llama-2-7B-GGUF/nsql-llama-2-7b.Q8_0.gguf")
				.temperature(0.7)
				.timeout(Duration.ofSeconds(60))
				.logRequests(true)
				.logResponses(true)
				.build();
	}
	
	public String resposta(String comando) {
		return modelo.generate(comando);
	}
}
